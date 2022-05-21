package ru.sfedu.fridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.fridge.exceptions.ProductNotFoundException;
import ru.sfedu.fridge.models.Product;
import ru.sfedu.fridge.services.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController @RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable("id") @Min(1) int id) {
        return this.productService
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping(value = "/products") @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {
        return this.productService.save(product);
    }

    @PutMapping(value="/products/{id}") @ResponseStatus(value = HttpStatus.CREATED)
    public Product updateProduct(@PathVariable("id") @Min(1) int id,
                                 @Valid @RequestBody Product updated) {
        Product initial = this.productService
                .findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));

        initial.setName(updated.getName());
        initial.setShelfLife(updated.getShelfLife());

        return this.productService.save(initial);
    }

    @DeleteMapping(value="/products/{id}") @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") @Min(1) int id) {
        Product product = this.productService.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
        this.productService.deleteById(product.getId());
    }
}
