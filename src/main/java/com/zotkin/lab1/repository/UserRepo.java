package com.zotkin.lab1.repository;

import com.zotkin.lab1.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
