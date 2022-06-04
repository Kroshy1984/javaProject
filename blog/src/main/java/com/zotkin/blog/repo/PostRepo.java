package com.zotkin.blog.repo;

import com.zotkin.blog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    Iterable<Post> findAllByOrderByIdDesc();

    /*Iterable<Post> findAllByTitleContains(String title);*/

    @Query("SELECT p from Post p where p.title like concat('%', ?1, '%') or p.anons like concat('%', ?1, '%')")
    Iterable<Post> findAllByTitleAndAnonsLike(String data);
}
