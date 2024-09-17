package com.ecommerce.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.UserRequestDto;
import com.ecommerce.userservice.dto.UserResponseDto;
import com.ecommerce.userservice.entity.Users;
import com.ecommerce.userservice.repository.UserRepository;

@Service
public class UsersService {
	
	@Autowired
    private UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userDto) {
        Users user = new Users();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());
        user.setAddress(userDto.address());
        user.setPhone(userDto.phone());
        Users savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserResponseDto loginUser(String username, String password) {
        Users user = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return convertToDto(user);
    }

    public UserResponseDto getUserProfile(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public UserResponseDto updateUserProfile(Long userId, UserRequestDto userDto) {
        Users user = getUserProfileEntity(userId);
        user.setEmail(userDto.email());
        user.setAddress(userDto.address());
        user.setPhone(userDto.phone());
        Users updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    private Users getUserProfileEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserResponseDto convertToDto(Users user) {
        UserResponseDto dto = new UserResponseDto(user.getId(),user.getUsername(),
        		user.getEmail(),user.getPhone(),user.getAddress());
  
        return dto;
    }

}
