package com.sk.demo.income.dto;

import com.sk.demo.income.model.Income;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequest {
    Integer growthYears;
    List<Income> incomes;
}
