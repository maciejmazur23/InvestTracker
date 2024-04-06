package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.buisness.service.mapper.ActiveToTransactionMapper;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActiveTransactionsServiceImpl implements ActiveTransactionsService {
    private final ActiveTransactionsDao activeTransactionsDao;
    private final TransactionsService transactionsService;
    private final ActiveToTransactionMapper activeToTransactionMapper;

    @Override
    public Optional<ActiveTransaction> deleteActiveTransactionById(Long id) {
        Optional<ActiveTransaction> activeTransactionsDaoById = activeTransactionsDao.findActiveTransactionById(id);
        if (activeTransactionsDaoById.isEmpty()) throw new RuntimeException("Active transaction not exist!");

        try {
            activeTransactionsDao.deleteActiveTransactionById(id);
            Optional<ActiveTransaction> activeTransactionAfterDelete = activeTransactionsDao.findActiveTransactionById(id);
            if (activeTransactionAfterDelete.isPresent()) log.error("Active transaction not deleted!");
        } catch (Exception e) {
            throw new RuntimeException("Active transaction not exist!");
        }
        ActiveTransaction deleted = activeTransactionsDaoById.get();
        log.info("Deleted active transaction: {}", deleted);

        Transaction transaction = activeToTransactionMapper.mapToTransaction(deleted)
                .withTransactionType(TRANSACTION_TYPE.SALE)
                .withTotalValue(deleted.getQuantity().multiply(deleted.getCourse()))
                .withDateOfTransaction(LocalDate.now());

        log.info("transaction: {}", transaction);
        try {
            transactionsService.addTransaction(transaction);
        } catch (Exception e) {
            log.error("Cannot save transaction: {}", e.getMessage());
        }
        return Optional.of(deleted);
    }

    @Override
    public List<ActiveTransaction> getActiveTransactions() {
        return activeTransactionsDao.findAllActiveTransactions();
    }

    @Override
    public List<ActiveTransaction> findActiveTransactionByTicker(String ticker) {
        try {
            return activeTransactionsDao.findActiveTransactionByTicker(ticker);
        } catch (Exception e) {
            log.error("Cannot find active transaction by ticker: {}", e.getMessage());
            throw new RuntimeException("Cannot find active transaction by ticker!");
        }
    }

    @Override
    public List<ActiveTransaction> findAllActiveTransactions() {
        return activeTransactionsDao.findAllActiveTransactions();
    }

    @Override
    public Optional<ActiveTransaction> saveActiveTransaction(ActiveTransaction activeTransaction) {
        try {
            ActiveTransaction saved = activeTransactionsDao.saveActiveTransaction(activeTransaction);
            if (saved == null) throw new RuntimeException();
            return Optional.of(saved);
        } catch (Exception e) {
            log.error("Cannot save active transaction: {}", e.getMessage());
            throw new RuntimeException("Cannot save active transaction");
        }
    }
}
