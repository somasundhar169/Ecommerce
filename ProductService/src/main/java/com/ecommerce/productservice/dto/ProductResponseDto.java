package com.ecommerce.productservice.dto;

import java.math.BigInteger;

public record ProductResponseDto(String id, String name, String description,
		BigInteger price,String category,Integer stock) {

}
