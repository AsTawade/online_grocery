package com.example.online_grocery.service;

import java.util.List;

import com.example.online_grocery.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);
}
