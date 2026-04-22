package com.example.expenses_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Idempotency_record")
@Data
public class IdempotencyRecord {

    @Id
    @Column(name = "idempotency_key",nullable = false)
    private String idempotencyKey;
    @Column(name = "expense_id",nullable = false)
    private Long expenseId;
}
