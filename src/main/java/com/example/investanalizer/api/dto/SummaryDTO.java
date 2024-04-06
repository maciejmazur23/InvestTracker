package com.example.investanalizer.api.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
public class SummaryDTO {
    BigDecimal totalValue;
    BigDecimal increaseOfValue;
    BigDecimal percentageIncrease;
}
