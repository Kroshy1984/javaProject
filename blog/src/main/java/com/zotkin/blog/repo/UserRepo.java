package com.zotkin.blog.repo;

import com.zotkin.blog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
