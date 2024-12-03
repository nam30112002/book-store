package com.bookstore.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID orderId;
    private UUID bookId;
    private Integer quantity;
}
