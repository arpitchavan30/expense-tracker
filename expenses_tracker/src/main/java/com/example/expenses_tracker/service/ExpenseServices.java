package com.example.expenses_tracker.service;
import com.example.expenses_tracker.dto.request.ExpenseRequest;
import com.example.expenses_tracker.dto.response.ExpenseResponse;
import com.example.expenses_tracker.entity.Expense;
import java.util.List;
public interface ExpenseServices {

    ExpenseResponse createExpense(String idempotencyKey, ExpenseRequest expenseRequest);

    List<ExpenseResponse> getExpenses(String category, boolean desc);
}
