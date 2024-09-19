package com.ecommerce.cartservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cartservice.dto.CartItemDto;
import com.ecommerce.cartservice.service.CartsService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
    @Autowired
    private CartsService cartService;

    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable String userId, @RequestBody CartItemDto itemDto) {
        cartService.addItemToCart(userId, itemDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> updateCartItem(@PathVariable String userId, @PathVariable String itemId, @RequestBody Map<String, Integer> update) {
        int quantity = update.get("quantity");
        cartService.updateCartItem(userId, itemId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable String userId, @PathVariable String itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok().build();
    }
}
