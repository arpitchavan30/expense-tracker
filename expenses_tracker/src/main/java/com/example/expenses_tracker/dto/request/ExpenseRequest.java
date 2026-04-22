package com.example.expenses_tracker.dto.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseRequest {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String category;

    private String description;

    @NotNull
    private LocalDate date;
}
