package com.ecommerce.productservice.dto;

import java.math.BigInteger;

public record ProductRequestDto(String name, String description,
		BigInteger price,String category,Integer stock) {

}
