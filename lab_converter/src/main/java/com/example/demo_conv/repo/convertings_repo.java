package com.example.demo_conv.repo;

import com.example.demo_conv.models.convertings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface convertings_repo extends CrudRepository<convertings, Long>{

}
