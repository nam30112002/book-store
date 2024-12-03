package com.bookstore.service;

import com.bookstore.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Object createOrder(OrderDTO orderDTO);
}
