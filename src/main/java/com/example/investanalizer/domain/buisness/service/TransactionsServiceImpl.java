package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.buisness.dao.TransactionsDao;
import com.example.investanalizer.domain.buisness.service.mapper.ActiveToTransactionMapper;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {
    private final HistoricalAssetsService historicalAssetsService;
    private final ActiveTransactionsDao activeTransactionsDao;
    private final TransactionsDao transactionsDao;
    private final AssetsService assetsService;
    private final ActiveToTransactionMapper activeToTransactionMapper;

    @Override
    @Transactional
    public List<Transaction> findAllTransactions() {
        return transactionsDao.findAllTransactions();
    }

    @Override
    @Transactional
    public Optional<Transaction> addTransaction(Transaction transaction) {
        try {
            Optional<Transaction> savedTransaction = addToTransactions(transaction);

            savedTransaction.ifPresent(assetsService::updateAssets);
            savedTransaction.ifPresent(historicalAssetsService::updateHistoricalAssets);

            return savedTransaction;
        } catch (Exception e) {
            log.error("Cannot save transaction: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Transactional
    private Optional<Transaction> addToTransactions(Transaction transaction) {
        if (transaction.getTransactionType().equals(TRANSACTION_TYPE.SALE)) return addIfSale(transaction);
        else if (transaction.getTransactionType().equals(TRANSACTION_TYPE.PURCHASE)) return addIfPurchase(transaction);
        else throw new RuntimeException("Bad transaction type!");
    }

    private Optional<Transaction> addIfPurchase(Transaction transaction) {
        BigDecimal totalValue = transaction.getQuantity().multiply(transaction.getCourse());

        saveActiveTransactionIfNotExist(transaction.withTotalValue(totalValue));

        return Optional.of(transactionsDao.saveTransaction(transaction.withTotalValue(totalValue)));
    }

    private Optional<Transaction> addIfSale(Transaction transaction) {
        List<ActiveTransaction> sortedActiveTransactionsByTicker = activeTransactionsDao
                .findActiveTransactionByTicker(transaction.getTicker())
                .stream()
                .sorted(Comparator.comparing(ActiveTransaction::getDateOfTransaction))
                .toList();

        if (sortedActiveTransactionsByTicker.isEmpty()) {
            throw new RuntimeException("Active transaction with provided id not exist!");
        }

        BigDecimal sum = sortedActiveTransactionsByTicker.stream()
                .map(ActiveTransaction::getQuantity)
                .reduce(BigDecimal::add).get();

        if (sum.compareTo(transaction.getQuantity()) < 0)
            throw new RuntimeException("You wanna sale more than you have!");

        BigDecimal quantity = transaction.getQuantity();
        for (ActiveTransaction activeTransaction : sortedActiveTransactionsByTicker) {
            BigDecimal activeTransactionQuantity = activeTransaction.getQuantity();

            if (quantity.compareTo(activeTransactionQuantity) > 0) {
                quantity = quantity.subtract(activeTransactionQuantity);
                activeTransactionsDao.deleteActiveTransactionById(activeTransaction.getActiveTransactionId());
            } else if (quantity.compareTo(activeTransactionQuantity) < 0) {
                activeTransactionsDao.saveActiveTransaction(activeTransaction
                        .withQuantity(quantity)
                        .withTotalValue(quantity.multiply(activeTransaction.getCourse()))
                );
            }
        }
        return Optional.ofNullable(transactionsDao.saveTransaction(transaction));
    }

    private void saveActiveTransactionIfNotExist(Transaction transaction) {
        activeTransactionsDao.saveActiveTransaction(
                activeToTransactionMapper.mapToActiveTransaction(transaction)
        );
    }

}
