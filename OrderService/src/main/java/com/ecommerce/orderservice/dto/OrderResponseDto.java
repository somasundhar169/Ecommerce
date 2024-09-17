package com.ecommerce.orderservice.dto;

import java.math.BigInteger;
import java.util.List;

public record OrderResponseDto(Long id,Long userId, List<OrderItemDto> products,BigInteger totalPrice,
String status) {

}
