package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.ActiveTransactionDTO;
import com.example.investanalizer.api.dto.mapper.ActiveTransactionDtoMapper;
import com.example.investanalizer.domain.buisness.service.ActiveTransactionsService;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static com.example.investanalizer.someTestData.ActiveTransactionsTestData.getSomeActiveTransactionDTOS;
import static com.example.investanalizer.someTestData.ActiveTransactionsTestData.getSomeActiveTransactions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(controllers = ActiveTransactionsApi.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ActiveTransactionsApiTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private ActiveTransactionDtoMapper mapper;
    @MockBean
    private ActiveTransactionsService service;

    @Test
    @DisplayName("When active transaction exist should return 200")
    void shouldPassWhenActiveTransactionExist() throws Exception {
        // given
        Long someId = 1L;
        ActiveTransaction example = getSomeActiveTransactions().get(0);
        String responseBody = objectMapper.writeValueAsString(example);

        when(service.deleteActiveTransactionById(someId)).thenReturn(Optional.of(example));

        // when, then
        MvcResult mvcResult = mockMvc.perform(delete(
                        ActiveTransactionsApi.ACTIVE_TRANSACTION_API + ActiveTransactionsApi.DELETE, someId
                ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assertions.assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo(responseBody);
    }

    @Test
    @DisplayName("When active transaction not exist should return 404")
    void shouldThrowWhenActiveTransactionNotExist() throws Exception {
        // given
        Long someId = 1L;
        Optional<ActiveTransaction> example = Optional.empty();

        when(service.deleteActiveTransactionById(someId)).thenReturn(example);

        // when, then
        mockMvc.perform(delete(
                        ActiveTransactionsApi.ACTIVE_TRANSACTION_API + ActiveTransactionsApi.DELETE, someId
                ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getActiveTransactions() throws Exception {
        //given
        List<ActiveTransaction> activeTransactions = getSomeActiveTransactions();
        List<ActiveTransactionDTO> activeTransactionDTOS = getSomeActiveTransactionDTOS();
        String responseBody = objectMapper.writeValueAsString(activeTransactionDTOS);

        int size = activeTransactions.size();
        for (int i = 0; i < size; i++) {
            when(mapper.mapToDTO(activeTransactions.get(i))).thenReturn(activeTransactionDTOS.get(i));
        }
        when(service.getActiveTransactions()).thenReturn(activeTransactions);

        //when, then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ActiveTransactionsApi.ACTIVE_TRANSACTION_API)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString())
                .isEqualTo(responseBody);
    }

}