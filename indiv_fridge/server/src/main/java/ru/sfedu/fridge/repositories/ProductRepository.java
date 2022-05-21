package ru.sfedu.fridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.fridge.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
