package com.ecommerce.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.dto.ProductRequest;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<ProductDto> getProductsByIds(List<Long> ids) {
        List<Product> products = productRepository.findAllById(ids);
        if (products.size() != ids.size()) {
            throw new ResourceNotFoundException("One or more products not found");
        }
        return products.stream().map(p -> {
            ProductDto product = new ProductDto();
            product.setId(p.getId());
            product.setName(p.getName());
            product.setDescription(p.getDescription());
            product.setPrice(p.getPrice());
            return product;
        }).toList();

    }
}
