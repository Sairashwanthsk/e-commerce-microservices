package com.ecommerce.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ecommerce.cartservice.dto.ProductDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "product-service", url = "${apigateway.url}")
public interface ProductClient {
    @GetMapping("/ecommerce/api/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    @PostMapping("/ecommerce/api/products/batch")
    public List<ProductDto> getProductsByIds(@RequestBody List<Long> ids);
}