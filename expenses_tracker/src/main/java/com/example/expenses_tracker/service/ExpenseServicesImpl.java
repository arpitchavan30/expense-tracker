package com.example.expenses_tracker.service;

import com.example.expenses_tracker.dto.request.ExpenseRequest;
import com.example.expenses_tracker.dto.response.ExpenseResponse;
import com.example.expenses_tracker.entity.Expense;
import com.example.expenses_tracker.entity.IdempotencyRecord;
import com.example.expenses_tracker.repository.ExpenseRepository;
import com.example.expenses_tracker.repository.IdempotencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.example.expenses_tracker.util.CommonUtil.*;

@Service
public class ExpenseServicesImpl implements ExpenseServices{

    @Autowired
    IdempotencyRepository idempotencyRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public ExpenseResponse createExpense(String key, ExpenseRequest request) {

        if (key == null || key.isBlank()) {
            throw new RuntimeException("Idempotency-Key is required");
        }

        Optional<IdempotencyRecord> existing = idempotencyRepository.findById(key);

        if (existing.isPresent()) {
            Expense expense = expenseRepository.findById(existing.get().getExpenseId())
                    .orElseThrow();
            return toExpenseResponse(expense);
        }

        Expense savedExpense = expenseRepository.save(
                createNewExpense(request));

        idempotencyRepository.save(
                createNewIdempotencyRecord(key, savedExpense.getId()));

        return toExpenseResponse(savedExpense);
    }
    @Override
    public List<ExpenseResponse> getExpenses(String category, boolean sortDesc){

        List<Expense> list = expenseRepository.findAll();

        if(category!=null && !category.isBlank()){
            list = list.stream().filter(exp -> exp.getCategory().equalsIgnoreCase(category))
                    .toList();
        }
        if(sortDesc){
            list = list.stream().sorted(Comparator.comparing(Expense::getExpenseDate).reversed())
                    .toList();
        }

        return toExpenseResponseList(list);
    }

}
