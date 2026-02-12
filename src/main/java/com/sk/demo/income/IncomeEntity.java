package com.sk.demo.income;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class IncomeEntity {
    UUID id;
    UUID userId;
    String nameStreamName;
    BigDecimal monthlyEarnings;
    BigDecimal annualGrowthRate;
    String typeOfIncome;
    Date createdAt;
    Date updatedAt;
}
