package com.example.online_grocery.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.online_grocery.dto.GroceryItemDto;
import com.example.online_grocery.dto.OrderDto;
import com.example.online_grocery.entity.Customer;
import com.example.online_grocery.entity.GroceryItem;
import com.example.online_grocery.entity.Order;
import com.example.online_grocery.mapper.OrderMapper;
import com.example.online_grocery.repository.CustomerRepository;
import com.example.online_grocery.repository.GroceryItemRepository;
import com.example.online_grocery.repository.OrderRepository;
import com.example.online_grocery.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private GroceryItemRepository groceryItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
            GroceryItemRepository groceryItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Long> itemIds = orderDto.getGroceryItems().stream()
                .map(GroceryItemDto::getId)
                .collect(Collectors.toList());

        List<GroceryItem> groceryItems = groceryItemRepository.findAllById(itemIds);
        if (groceryItems.size() != itemIds.size()) {
            throw new RuntimeException("Some GroceryItems not found");
        }

        double totalPrice = groceryItems.stream()
                .mapToDouble(GroceryItem::getPrice)
                .sum();

        Order order = OrderMapper.mapToOrder(orderDto);

        order.setCustomer(customer);
        order.setGroceryItems(groceryItems);
        order.setTotalPrice(totalPrice);

        LocalDate currentDate = LocalDate.now();
        order.setOrderDate(currentDate);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.mapToOrderDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        existingOrder.setCustomer(customerRepository.findById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        List<Long> groceryItemIds = orderDto.getGroceryItems().stream()
                .map(GroceryItemDto::getId)
                .collect(Collectors.toList());
        List<GroceryItem> groceryItems = groceryItemRepository.findAllById(groceryItemIds);
        if (groceryItems.size() != groceryItemIds.size()) {
            throw new RuntimeException("Some GroceryItems not found");
        }

        existingOrder.setGroceryItems(groceryItems);
        existingOrder.setTotalPrice(groceryItems.stream()
                .mapToDouble(GroceryItem::getPrice)
                .sum());
        existingOrder.setOrderDate(orderDto.getOrderDate());

        Order updatedOrder = orderRepository.save(existingOrder);
        return OrderMapper.mapToOrderDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

}
