package com.sk.demo.income.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponse {
    BigDecimal totalMonthlyIncome;
    BigDecimal totalAnnualIncome;
    BigDecimal passiveIncomeRatio;
    BigDecimal incomeConcentration;
    BigDecimal totalProjectedMonthlyIncome;
}
