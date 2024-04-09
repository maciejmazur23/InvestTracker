package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.example.investanalizer.someTestData.ActiveTransactionsTestData.getSomeActiveTransactions;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ActiveTransactionsServiceTest {

    @MockBean
    private ActiveTransactionsDao activeTransactionsDao;

    @MockBean
    private TransactionsService transactionsService;

    @InjectMocks
    private ActiveTransactionsService activeTransactionsService;

    @Test
    @DisplayName("Should return record if exist")
    void deleteActiveTransactionByIdShouldReturnRecordIfExist() {
        //given
        Optional<ActiveTransaction> activeTransaction = Optional.of(getSomeActiveTransactions().get(0));
        Mockito.when(activeTransactionsDao.findActiveTransactionById(any())).thenReturn(activeTransaction);
        when(transactionsService.addTransaction(any())).thenReturn(null);

        //when
        Optional<ActiveTransaction> result = activeTransactionsService.deleteActiveTransactionById(
                activeTransaction.get().getActiveTransactionId()
        );

        //then
        Assertions.assertEquals(activeTransaction, result);
    }

    @Test
    @DisplayName("Should throw exception if record not exist")
    void deleteActiveTransactionByIdShouldThrowExceptionIfRecordNotExist() {
        //given
        Optional<ActiveTransaction> activeTransaction = Optional.empty();
        Mockito.when(activeTransactionsDao.findActiveTransactionById(any())).thenReturn(activeTransaction);
        when(transactionsService.addTransaction(any())).thenReturn(null);

        //when, then
        try {
            Optional<ActiveTransaction> result = activeTransactionsService.deleteActiveTransactionById(1L);
        } catch (Exception e) {
            Assertions.assertEquals("Active transaction not exist!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should pass if return value after saved")
    void saveActiveTransactionShouldPassIfReturnValueAfterSaved() {
        //given
        ActiveTransaction expected = getSomeActiveTransactions().get(0);
        when(activeTransactionsDao.saveActiveTransaction(expected)).thenReturn(expected);

        //when
        Optional<ActiveTransaction> result = activeTransactionsService.saveActiveTransaction(expected);

        //then
        Assertions.assertEquals(expected, result.orElse(null));
    }

    @Test
    @DisplayName("Should pass if cannot save value")
    void saveActiveTransactionShouldPassIfCannotSaveValue() {
        //given
        ActiveTransaction toSave = getSomeActiveTransactions().get(0);
        when(activeTransactionsDao.saveActiveTransaction(toSave)).thenReturn(null);

        //when, then
        try {
            Optional<ActiveTransaction> result = activeTransactionsService.saveActiveTransaction(toSave);
        } catch (Exception e) {
            Assertions.assertEquals("Cannot save active transaction", e.getMessage());
        }
    }
}