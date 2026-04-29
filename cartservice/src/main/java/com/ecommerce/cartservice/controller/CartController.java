package com.ecommerce.cartservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.ecommerce.cartservice.dto.AddToCartRequest;
import com.ecommerce.cartservice.dto.UpdateCartRequest;
import com.ecommerce.cartservice.entity.CartItem;
import com.ecommerce.cartservice.service.CartService;

@RestController
@RequestMapping("/ecommerce/api/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public CartItem addToCart(@RequestBody AddToCartRequest request) {
        // Logic to add item to cart
        return cartService.addToCart(request); // Return the added cart item (for demonstration)
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        // Logic to retrieve cart items for the specified user
        return cartService.getCart(userId); // Return cart items for the user
    }

    @PutMapping("/{userId}/{productId}")
    public CartItem updateCartItem(@PathVariable String userId, @PathVariable Long productId, @RequestBody UpdateCartRequest request) {
        // Logic to update cart item
        return cartService.updateCartItem(userId, productId, request.getQuantity()); // Return the updated cart item (for demonstration)
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String userId, @PathVariable Long productId) {
        // Logic to remove item from cart
        cartService.removeCartItem(userId, productId);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        // Logic to clear the cart for the specified user
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}