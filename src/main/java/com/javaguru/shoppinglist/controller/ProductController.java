package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.Category;
import com.javaguru.shoppinglist.service.ProductService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ProductDTO findTaskById(@PathVariable Long id) {
        Product product = service.findProduct(id);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory().toString(),
                product.getDiscount(), product.getDescription());
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO request) {
        System.out.println(request);
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(Category.valueOf(request.getStringCategory()));
        product.setDiscount(request.getDiscount());
        product.setDescription(request.getDescription());

        Product createdProduct = service.findProduct(service.createProduct(product));

        return new ProductDTO(createdProduct.getId(), createdProduct.getName(), createdProduct.getPrice(),
                createdProduct.getCategory().toString(), createdProduct.getDiscount(), createdProduct.getDescription());
    }

}