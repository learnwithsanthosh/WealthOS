package com.sk.demo.income;

import com.sk.demo.income.dto.IncomeRequest;
import com.sk.demo.income.dto.IncomeResponse;
import com.sk.demo.income.model.Income;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.sk.demo.common.WealthConstants.ACTIVE;
import static com.sk.demo.common.WealthConstants.PASSIVE;

@Service
public class IncomeService {
    public IncomeResponse calculateIncomeSummary(IncomeRequest request) {
        if (request.getIncomes() != null && !request.getIncomes().isEmpty()) {
            var totalIncomePerMonth = totalIncomePermonth(request.getIncomes());
            var totalIncomePerYear = Optional.ofNullable(totalIncomePerMonth).isPresent() ? totalIncomePerMonth.multiply(BigDecimal.valueOf(12)) : BigDecimal.ZERO;
            var passiveIncomeRatio = passiveIncomeRatio(request.getIncomes());
            var incomeConcentration = incomeConcentration(request.getIncomes(), totalIncomePerMonth);
            var projectedIncomeAfterNYears = projectedIncomeAfterNYears(request.getIncomes(), request.getGrowthYears());
            var respone = IncomeResponse.builder()
                    .totalMonthlyIncome(totalIncomePerMonth)
                    .totalAnnualIncome(totalIncomePerYear)
                    .passiveIncomeRatio(passiveIncomeRatio)
                    .incomeConcentration(incomeConcentration)
                    .totalProjectedMonthlyIncome(projectedIncomeAfterNYears)
                    .build();
            return respone;
        }
        return null;

    }

    private BigDecimal totalIncomePermonth(List<Income> incomeList) {
        BigDecimal total = BigDecimal.ZERO;
        for (Income income : incomeList) {
            BigDecimal earnings = income.getMonthlyEarnings();
            if (earnings != null) {
                total = total.add(earnings);
            }
        }
        return total;
    }

    private BigDecimal passiveIncomeRatio(List<Income> incomeList) {
        BigDecimal activeIncome = BigDecimal.ZERO;
        BigDecimal passiveIncome = BigDecimal.ZERO;
        for (Income income : incomeList) {
            if (Optional.ofNullable(income.getTypeOfIncome()).isPresent() && income.getTypeOfIncome().equalsIgnoreCase(ACTIVE)) {
                activeIncome = activeIncome.add(income.getMonthlyEarnings());
            } else if (Optional.ofNullable(income.getTypeOfIncome()).isPresent() && income.getTypeOfIncome().equalsIgnoreCase(PASSIVE)) {
                passiveIncome = passiveIncome.add(income.getMonthlyEarnings());
            }
        }
        if (activeIncome.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.valueOf(100);
        }
        return passiveIncome.divide(activeIncome);
    }

    private BigDecimal incomeConcentration(List<Income> incomeList, BigDecimal totalIncomePerMonth) {
        if (totalIncomePerMonth.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal maxIncome = incomeList.stream().map(income -> income.getMonthlyEarnings()).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        return maxIncome.divide(totalIncomePerMonth, 2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal projectedIncomeAfterNYears(List<Income> incomeList, Integer growthYears) {

        BigDecimal projectedIncome = incomeList.stream().map(income ->
        {
            BigDecimal monthlyEarnings = income.getMonthlyEarnings();
            BigDecimal growthRate =  Optional.ofNullable(income.getAnnualGrowthRate()).isPresent()?income.getAnnualGrowthRate().divide(BigDecimal.valueOf(100)):BigDecimal.ZERO;
            if (monthlyEarnings != null && growthRate != null) {
                BigDecimal growthFactor = BigDecimal.ONE.add(growthRate);
                return monthlyEarnings.multiply(growthFactor.pow(growthYears));
            }
            return BigDecimal.ZERO;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        return projectedIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
