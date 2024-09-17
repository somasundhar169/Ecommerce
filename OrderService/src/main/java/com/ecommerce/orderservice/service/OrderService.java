package com.ecommerce.orderservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.OrderItemDto;
import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.entity.Orders;
import com.ecommerce.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDto createOrder(OrderRequestDto orderDto) {
        Orders order = new Orders();
        order.setUserId(orderDto.userId());
        order.setProducts(orderDto.products().stream().map(this::convertToEntity).collect(Collectors.toList()));
        order.setTotalPrice(orderDto.totalPrice());
        order.setStatus("Created");
        Orders savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDto(order);
    }

    public OrderResponseDto updateOrderStatus(Long orderId, String status) {
        Orders order = getOrderEntity(orderId);
        order.setStatus(status);
        Orders updatedOrder = orderRepository.save(order);
        return convertToDto(updatedOrder);
    }

    public List<OrderResponseDto> getUserOrders(Long userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Orders getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private OrderResponseDto convertToDto(Orders order) {
        OrderResponseDto dto = new OrderResponseDto(order.getId(),order.getUserId(),
        		order.getProducts().stream().map(this::convertToDto).collect(Collectors.toList()),
        		order.getTotalPrice(),order.getStatus());
        return dto;
    }

    private OrderItemDto convertToDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto(item.getProductId(),item.getQuantity());
        return dto;
    }

    private OrderItem convertToEntity(OrderItemDto dto) {
        OrderItem item = new OrderItem(dto.productId(),dto.quantity());
        return item;
    }
}
