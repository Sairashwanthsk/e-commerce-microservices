package com.ecommerce.cartservice.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String userId;
    private Long productId;
    private int quantity;
}