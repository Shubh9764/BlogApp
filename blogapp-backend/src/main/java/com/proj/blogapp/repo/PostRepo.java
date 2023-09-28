package com.proj.blogapp.repo;

import com.proj.blogapp.entities.Category;
import com.proj.blogapp.entities.Post;
import com.proj.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
