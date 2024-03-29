package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.buisness.managment.service.mapper.ActiveToTransactionMapper;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActiveTransactionsServiceImpl implements ActiveTransactionsService {
    private final ActiveTransactionsDao activeTransactionsDao;
    private final TransactionsService transactionsService;
    private final ActiveToTransactionMapper activeToTransactionMapper;

    @Override
    public Optional<ActiveTransaction> deleteById(Long id) {
        ActiveTransaction activeTransaction = activeTransactionsDao.deleteById(id);
        if (activeTransaction == null) {
            return Optional.empty();
        }
        Transaction transaction = activeToTransactionMapper.mapToTransaction(activeTransaction)
                .withTransactionType(TRANSACTION_TYPE.SALE)
                .withTotalValue(activeTransaction.getQuantity().multiply(activeTransaction.getCourse()))
                .withDateOfTransaction(LocalDate.now());

        transactionsService.addTransaction(transaction);

        return Optional.of(activeTransaction);
    }

    @Override
    public List<ActiveTransaction> getActiveTransactions() {
        return activeTransactionsDao.findActiveTransactions();
    }

    @Override
    public List<ActiveTransaction> findActiveTransactionByTicker(String ticker) {
        return activeTransactionsDao.findByTicker(ticker);
    }
}
