package com.bookstore.service;

import com.bookstore.dto.OrderDTO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderInfo;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Override
    public Object createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        if(orderDTO.getCustomerName() == null || orderDTO.getShippingAddress() == null) {
            throw new IllegalArgumentException("Customer name and shipping address are required");
        }
        order.setCustomerName(orderDTO.getCustomerName());
        order.setShippingAddress(orderDTO.getShippingAddress());

        Double totalPrice = 0.0;

        for(OrderInfo orderInfo : orderDTO.getOrderInfoList()) {
            OrderInfo newOrderInfo = new OrderInfo();
            Book book = bookRepository.findById(orderInfo.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
            if (book.getRemainingQuantity() < orderInfo.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity for book " + book.getTitle());
            }
            book.setRemainingQuantity(book.getRemainingQuantity() - orderInfo.getQuantity());
            bookRepository.save(book);
            totalPrice += book.getPrice() * orderInfo.getQuantity();
            newOrderInfo.setBookId(orderInfo.getBookId());
            newOrderInfo.setQuantity(orderInfo.getQuantity());
            newOrderInfo.setOrderId(order.getId());
        }
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }
}
