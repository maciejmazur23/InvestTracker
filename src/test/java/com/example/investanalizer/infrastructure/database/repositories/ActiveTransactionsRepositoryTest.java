package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import com.example.investanalizer.infrastructure.database.repositories.jpa.ActiveTransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.ActiveTransactionEntityMapper;
import com.example.investanalizer.integration.configuration.PersistenceContainerTestConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static com.example.investanalizer.someTestData.ActiveTransactionsTestData.getSomeActiveTransactionEntities;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ActiveTransactionsRepositoryTest {

    private ActiveTransactionsDao activeTransactionsRepository;
    private ActiveTransactionsJpaRepo activeTransactionsJpaRepo;
    private ActiveTransactionEntityMapper mapper;

    @BeforeEach
    void beforeEach() {
        List<ActiveTransactionEntity> someActiveTransactions = getSomeActiveTransactionEntities();
        activeTransactionsJpaRepo.saveAllAndFlush(someActiveTransactions);
    }

    @AfterEach
    void afterEach() {
        activeTransactionsJpaRepo.deleteAll();
    }

    @Test
    @DisplayName("findByTicker should pass when size is 1")
    void findByTickerShouldPassWhenSizeIs1() {
        //give
        String ticker = "BTC";

        //when
        List<ActiveTransaction> byTicker = activeTransactionsRepository.findActiveTransactionByTicker(ticker);

        //then
        Assertions.assertEquals(1, byTicker.size());
    }

    @Test
    @DisplayName("findByTicker should pass when size is 0")
    void findByTickerShouldPassWhenSizeIs0() {
        //give
        String ticker = "BTC_123";

        //when
        List<ActiveTransaction> byTicker = activeTransactionsRepository.findActiveTransactionByTicker(ticker);

        //then
        Assertions.assertEquals(0, byTicker.size());
    }

    @Test
    @DisplayName("deleteByActiveTransactionId() should pass if provided record was deleted")
    void deleteById() {
        //give
        List<ActiveTransactionEntity> all = activeTransactionsJpaRepo.findAll();
        Long id = all.get(0).getActiveTransactionId();
        int expectedSize = all.size() - 1;

        //when
        activeTransactionsRepository.deleteActiveTransactionById(id);
        activeTransactionsJpaRepo.flush();
        int resultSize = activeTransactionsJpaRepo.findAll().size();

        //then
        Assertions.assertEquals(expectedSize, resultSize);
    }

    @Test
    @DisplayName("saveActiveTransaction() should pass if provided record was saved")
    void saveActiveTransactionPassIfSaved() {
        //give
        List<ActiveTransactionEntity> all = activeTransactionsJpaRepo.findAll();

        Long id = all.get(0).getActiveTransactionId();

        activeTransactionsJpaRepo.deleteById(id);

        int expectedSize = all.size();
        ActiveTransactionEntity expected = all.get(0);

        //when
        activeTransactionsRepository.saveActiveTransaction(mapper.mapFromEntity(expected));
        activeTransactionsJpaRepo.flush();
        int resultSize = activeTransactionsJpaRepo.findAll().size();

        //then
        Assertions.assertEquals(expectedSize, resultSize);
    }

    @Test
    @DisplayName("saveActiveTransaction() should pass if provided record wasn't saved")
    void saveActiveTransactionPassIfNotSaved() {
        //give
        ActiveTransactionEntity expected = activeTransactionsJpaRepo.findAll().get(0);
        int expectedSize = activeTransactionsJpaRepo.findAll().size();

        //when
        activeTransactionsRepository.saveActiveTransaction(mapper.mapFromEntity(expected));
        activeTransactionsJpaRepo.flush();
        int resultSize = activeTransactionsJpaRepo.findAll().size();

        //then
        Assertions.assertEquals(expectedSize, resultSize);
    }

    @Test
    @DisplayName("findById() should pass if ActiveTransaction exist")
    void findByIdShouldPassIfRecordExist() {
        //give
        Long id = 1L;
        Optional<ActiveTransaction> expected = activeTransactionsJpaRepo.findById(id)
                .map(mapper::mapFromEntity);

        //when
        Optional<ActiveTransaction> result = activeTransactionsRepository.findActiveTransactionById(id);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("findById() should pass if ActiveTransaction not exist")
    void findByIdShouldPassIfRecordNotExist() {
        //give
        Long id = 9L;
        Optional<Object> expected = Optional.empty();

        //when
        Optional<ActiveTransaction> result = activeTransactionsRepository.findActiveTransactionById(id);

        //then
        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName("Should find all transactions")
    void findAllActiveTransactions() {
        //give
        List<ActiveTransaction> expected = activeTransactionsJpaRepo.findAll().stream()
                .map(mapper::mapFromEntity)
                .toList();

        //when
        List<ActiveTransaction> result = activeTransactionsRepository.findAllActiveTransactions();

        //then
        Assertions.assertEquals(expected, result);
    }
}