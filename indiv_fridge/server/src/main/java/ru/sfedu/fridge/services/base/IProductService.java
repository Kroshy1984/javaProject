package ru.sfedu.fridge.services.base;

import ru.sfedu.fridge.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAllProducts();
    Optional<Product> findById(int id);
    Product save(Product product);
    void deleteById(int id);
}
