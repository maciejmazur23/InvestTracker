package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.buisness.dao.TransactionsDao;
import com.example.investanalizer.domain.buisness.managment.service.mapper.ActiveToTransactionMapper;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public List<Transaction> findAll() {
        return transactionsDao.findAll();
    }

    @Override
    @Transactional
    public Optional<Transaction> addTransaction(Transaction transaction) {
        Optional<Transaction> savedTransaction = addToTransactions(transaction);

        savedTransaction.ifPresent(this::updateActiveTransactions);
        savedTransaction.ifPresent(assetsService::updateAssets);
        savedTransaction.ifPresent(historicalAssetsService::updateHistoricalAssets);

        return savedTransaction;
    }

    @Transactional
    private Optional<Transaction> addToTransactions(Transaction transaction) {
        if (transaction.getTransactionType().equals(TRANSACTION_TYPE.SALE)) return addIfSale(transaction);
        else if (transaction.getTransactionType().equals(TRANSACTION_TYPE.PURCHASE)) return addIfPurchase(transaction);
        else throw new RuntimeException("Bad transaction type!");
    }

    private Optional<Transaction> addIfPurchase(Transaction transaction) {
        BigDecimal totalValue = transaction.getQuantity().multiply(transaction.getCourse());
        Transaction saved = transactionsDao.saveTransaction(transaction.withTotalValue(totalValue));
        return Optional.of(saved);
    }

    private Optional<Transaction> addIfSale(Transaction transaction) {
        List<ActiveTransaction> activeTransactionsByTicker = activeTransactionsDao
                .findByTicker(transaction.getTicker());

        Optional<ActiveTransaction> minActiveTransactionByDate = activeTransactionsByTicker.stream()
                .filter(activeTransaction -> activeTransaction.getTicker().equals(transaction.getTicker()))
                .min(Comparator.comparing(ActiveTransaction::getDateOfTransaction));

        if (minActiveTransactionByDate.isEmpty())
            return Optional.empty();

        return Optional.of(transactionsDao.saveTransaction(
                transaction.withActiveTransactionId(minActiveTransactionByDate.get().getActiveTransactionId())
        ));
    }

    @Transactional
    private void updateActiveTransactions(Transaction transaction) {
        if (Objects.isNull(transaction.getActiveTransactionId())) saveActiveTransactionIfNotExist(transaction);
        else saveActiveTransactionIfExist(transaction);
    }

    private void saveActiveTransactionIfNotExist(Transaction transaction) {
        activeTransactionsDao.saveActiveTransaction(activeToTransactionMapper.mapToActiveTransaction(transaction));
    }

    private void saveActiveTransactionIfExist(Transaction transaction) {
        try {
            ActiveTransaction activeTransaction = activeTransactionsDao.findById(
                    transaction.getActiveTransactionId()
            ).orElseThrow();

            BigDecimal newQuantity = activeTransaction.getQuantity().subtract(transaction.getQuantity());
            ActiveTransaction updatedActiveTransaction = activeTransaction
                    .withQuantity(newQuantity)
                    .withTotalValue(newQuantity.multiply(activeTransaction.getCourse()));

            activeTransactionsDao.saveActiveTransaction(updatedActiveTransaction);
        } catch (Exception e) {
            throw new RuntimeException("Not found active transaction by id");
        }
    }
}
