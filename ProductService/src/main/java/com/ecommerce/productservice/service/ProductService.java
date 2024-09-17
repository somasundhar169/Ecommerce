package com.ecommerce.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.productservice.dto.ProductRequestDto;
import com.ecommerce.productservice.dto.ProductResponseDto;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setCategory(productDto.category());
        product.setStock(productDto.stock());
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    public ProductResponseDto getProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    public ProductResponseDto updateProduct(String productId, ProductRequestDto productDto) {
        Product product = getProductEntity(productId);
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setCategory(productDto.category());
        product.setStock(productDto.stock());
        Product updatedProduct = productRepository.save(product);
        return convertToDto(updatedProduct);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductResponseDto> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContaining(query);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Product getProductEntity(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    private ProductResponseDto convertToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto(product.getId(),product.getName(),
        		product.getDescription(),product.getPrice(),product.getCategory(),product.getStock());
        return dto;
    }
}
