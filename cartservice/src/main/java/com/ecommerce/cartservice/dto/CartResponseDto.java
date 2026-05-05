package com.ecommerce.cartservice.dto;

import lombok.Data;

@Data
public class CartResponseDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double price;
    private int quantity;
    private Double totalPrice;
}