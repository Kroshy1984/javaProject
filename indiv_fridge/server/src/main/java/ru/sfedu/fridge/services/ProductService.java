package ru.sfedu.fridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.fridge.models.Product;
import ru.sfedu.fridge.repositories.ProductRepository;
import ru.sfedu.fridge.services.base.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        this.productRepository.deleteById(id);
    }
}
