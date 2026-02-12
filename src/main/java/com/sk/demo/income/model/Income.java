package com.sk.demo.income.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    String nameStreamName;
    BigDecimal monthlyEarnings;
    BigDecimal annualGrowthRate;
    String typeOfIncome;
}
