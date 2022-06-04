package com.zotkin.lab1.repository;

import com.zotkin.lab1.entity.PairEntity;
import org.springframework.data.repository.CrudRepository;

public interface PairRepo extends CrudRepository<PairEntity, Long> {
}
