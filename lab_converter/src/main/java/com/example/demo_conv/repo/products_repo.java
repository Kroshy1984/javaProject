package com.example.demo_conv.repo;


import com.example.demo_conv.models.products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface products_repo extends CrudRepository<products, Long>{

}
