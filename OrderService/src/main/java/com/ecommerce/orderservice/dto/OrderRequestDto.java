package com.ecommerce.orderservice.dto;

import java.math.BigInteger;
import java.util.List;

public record OrderRequestDto(Long userId, List<OrderItemDto> products,BigInteger totalPrice) {

}
