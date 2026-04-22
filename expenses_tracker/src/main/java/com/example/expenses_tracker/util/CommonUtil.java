package com.example.expenses_tracker.util;

import com.example.expenses_tracker.dto.request.ExpenseRequest;
import com.example.expenses_tracker.dto.response.ExpenseResponse;
import com.example.expenses_tracker.entity.Expense;
import com.example.expenses_tracker.entity.IdempotencyRecord;

import java.util.List;

public class CommonUtil {

    public static Expense createNewExpense(ExpenseRequest expenseRequest){

        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDescription(expenseRequest.getDescription());
        expense.setExpenseDate(expenseRequest.getDate());

        return expense;
    }

    public static IdempotencyRecord createNewIdempotencyRecord(String key, Long id) {

        IdempotencyRecord idempotencyRecord = new IdempotencyRecord();
        idempotencyRecord.setIdempotencyKey(key);
        idempotencyRecord.setExpenseId(id);

        return idempotencyRecord;
    }

    public static List<ExpenseResponse> toExpenseResponseList(List<Expense> expenses) {
        return expenses.stream()
                .map(CommonUtil::toExpenseResponse)
                .toList();
    }

    public static ExpenseResponse toExpenseResponse(Expense expense) {
        return ExpenseResponse.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .build();
    }
}
