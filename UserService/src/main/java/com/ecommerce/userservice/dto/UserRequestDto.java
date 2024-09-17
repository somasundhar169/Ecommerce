package com.ecommerce.userservice.dto;

public record UserRequestDto(String username,String password,
		String email,String phone,String address) {

}
