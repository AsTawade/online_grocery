package com.example.online_grocery.mapper;

import com.example.online_grocery.dto.OrderDto;
import com.example.online_grocery.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order(
                orderDto.getId(),
                CustomerMapper.mapToCustomer(orderDto.getCustomer()),
                orderDto.getGroceryItems().stream()
                        .map(GroceryItemMapper::mapToGroceryItem)
                        .collect(Collectors.toList()),
                orderDto.getOrderDate(),
                orderDto.getTotalPrice()
        );

        return order;
    }

    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto(
                order.getId(),
                CustomerMapper.mapToCustomerDto(order.getCustomer()),
                order.getGroceryItems().stream()
                        .map(GroceryItemMapper::mapToGroceryItemDto)
                        .collect(Collectors.toList()),
                order.getOrderDate(),
                order.getTotalPrice()
        );

        return orderDto;
    }
}