package com.ecommerce.cartservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.cartservice.dto.CartItemDto;

@Service
public class CartsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CART_PREFIX = "cart:";

    public void addItemToCart(String userId, CartItemDto itemDto) {
        String key = CART_PREFIX + userId;
        List<CartItemDto> cart = (List<CartItemDto>) redisTemplate.opsForValue().get(key);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(itemDto);
        redisTemplate.opsForValue().set(key, cart);
    }

    public List<CartItemDto> getCartItems(String userId) {
        String key = CART_PREFIX + userId;
        List<CartItemDto> cart = (List<CartItemDto>) redisTemplate.opsForValue().get(key);
        return cart != null ? cart : new ArrayList<>();
    }

    public void updateCartItem(String userId, String itemId, Integer quantity) {
        String key = CART_PREFIX + userId;
        List<CartItemDto> cart = (List<CartItemDto>) redisTemplate.opsForValue().get(key);
        if (cart != null) {
            for (CartItemDto item : cart) {
                if (item.getProductId().equals(itemId)) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            redisTemplate.opsForValue().set(key, cart);
        }
    }

    public void removeItemFromCart(String userId, String itemId) {
        String key = CART_PREFIX + userId;
        List<CartItemDto> cart = (List<CartItemDto>) redisTemplate.opsForValue().get(key);
        if (cart != null) {
            cart.removeIf(item -> item.getProductId().equals(itemId));
            redisTemplate.opsForValue().set(key, cart);
        }
    }
}
