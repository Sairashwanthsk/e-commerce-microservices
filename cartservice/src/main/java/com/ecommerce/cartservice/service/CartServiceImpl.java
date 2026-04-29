package com.ecommerce.cartservice.service;

import org.springframework.stereotype.Service;

import com.ecommerce.cartservice.entity.CartItem;
import com.ecommerce.cartservice.exception.ResourceNotFoundException;
import com.ecommerce.cartservice.dto.AddToCartRequest;
import com.ecommerce.cartservice.repository.CartItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(AddToCartRequest request) {
        Optional<CartItem> existingItem = cartItemRepository.findByUserIdAndProductId(request.getUserId(),
                request.getProductId());
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            return cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(request.getUserId());
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            return cartItemRepository.save(cartItem);
        }
    }

    @Override
    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public CartItem updateCartItem(String userId, Long productId, int quantity) {
        CartItem item = cartItemRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public void removeCartItem(String userId, Long productId) {
        CartItem item = cartItemRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItemRepository.delete(item);
    }

    @Override
    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}