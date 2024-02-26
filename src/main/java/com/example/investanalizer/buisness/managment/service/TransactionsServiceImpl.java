package com.example.investanalizer.buisness.managment.service;

import com.example.investanalizer.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.buisness.dao.TransactionsDao;
import com.example.investanalizer.domain.ActiveTransaction;
import com.example.investanalizer.domain.TRANSACTION_TYPE;
import com.example.investanalizer.domain.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsDao transactionsDao;
    private final ActiveTransactionsDao activeTransactionsDao;
    private final ActiveToTransactionMapper activeToTransactionMapper;

    @Transactional
    @Override
    public Optional<Transaction> addTransaction(Transaction transaction) {
        Optional<Transaction> savedTransaction = addToTransactions(transaction);
        savedTransaction.ifPresent(this::updateActiveTransactions);
        return savedTransaction;
    }

    @Transactional
    private void updateActiveTransactions(Transaction transaction) {
        if (transaction.getActiveTransactionId() == null) {
            activeTransactionsDao.saveActiveTransaction(
                    activeToTransactionMapper.mapTransactionToActiveTransaction(transaction));

        } else {
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

    @Transactional
    private Optional<Transaction> addToTransactions(Transaction transaction) {
        if (transaction.getTransactionType().equals(TRANSACTION_TYPE.SALE)) {

            List<ActiveTransaction> activeTransactionsByTicker = activeTransactionsDao
                    .findByTicker(transaction.getTicker());

            Optional<ActiveTransaction> min = activeTransactionsByTicker.stream()
                    .filter(activeTransaction -> activeTransaction.getTicker().equals(transaction.getTicker()))
                    .min(Comparator.comparing(ActiveTransaction::getDateOfTransaction));

            try {
                ActiveTransaction activeTransaction = min.orElseThrow();
                return Optional.of(transactionsDao.addTransaction(
                        transaction.withActiveTransactionId(activeTransaction.getTransactionId())));
            } catch (Exception e) {
                return Optional.empty();
            }
        } else return Optional.of(transactionsDao.addTransaction(transaction));
    }
}
