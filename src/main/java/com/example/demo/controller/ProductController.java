package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        logger.info("Received request for product with id: " + id);

        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-detail";
        } else {
            model.addAttribute("errorMessage", "Product not found.");
            return "error";
        }
    }
}