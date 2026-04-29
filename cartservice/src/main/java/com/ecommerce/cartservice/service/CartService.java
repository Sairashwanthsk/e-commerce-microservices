package com.ecommerce.cartservice.service;

import java.util.List;
import com.ecommerce.cartservice.dto.AddToCartRequest;
import lombok.RequiredArgsConstructor;
import com.ecommerce.cartservice.entity.CartItem;

public interface CartService {
    CartItem addToCart(AddToCartRequest request);

    List<CartItem> getCart(String userId);

    CartItem updateCartItem(String userId, Long productId, int quantity);

    void removeCartItem(String userId, Long productId);

    void clearCart(String userId);
}