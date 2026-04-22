package com.example.expenses_tracker.controller;

import com.example.expenses_tracker.dto.request.ExpenseRequest;
import com.example.expenses_tracker.dto.response.ExpenseResponse;
import com.example.expenses_tracker.entity.Expense;
import com.example.expenses_tracker.enums.SortingType;
import com.example.expenses_tracker.service.ExpenseServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@Slf4j
public class ExpenseApiController {

    @Autowired
    ExpenseServices expenseServices;
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Idempotency-Key", required = false) String key,
            @RequestBody ExpenseRequest request) {

        try {
            ExpenseResponse response = expenseServices.createExpense(key, request);
            log.info("POST /expenses/create - expense created successfully");
            return ResponseEntity.ok(response);
        }  catch (IllegalArgumentException ex) {
            log.warn("Invalid request for POST /expenses/create - reason={}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());

        } catch (Exception ex) {
            log.error("Unexpected error in POST /expenses/create - idempotencyKey={}, error={}",
                    key, ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping("/getAll")
    public List<ExpenseResponse> getAllExpenses(@RequestParam(required = false) String category,
                                                @RequestParam(required = false) String sort){

        boolean desc = SortingType.DATE_DESC.name().equalsIgnoreCase(sort);
        return  expenseServices.getExpenses(category,desc);
    }
}
