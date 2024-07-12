package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        logger.info("Received request for product with id: " + id);

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-detail";
        } else {
            model.addAttribute("errorMessage", "Product not found.");
            return "error";
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
        logger.error("Type mismatch error: " + ex.getMessage());
        model.addAttribute("errorMessage", "Invalid product ID. Please ensure the product ID is a number.");
        return "error";
    }
}