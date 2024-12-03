package com.bookstore.dto;

import com.bookstore.entity.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String customerName;
    private String shippingAddress;
    private Boolean isPaid;
    private List<OrderInfo> orderInfoList;
}
