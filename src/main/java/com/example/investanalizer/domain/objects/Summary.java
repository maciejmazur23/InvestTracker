package com.example.investanalizer.domain.objects;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
public class Summary {
    BigDecimal totalValue;
    BigDecimal increaseOfValue;
    BigDecimal percentageIncrease;
}
