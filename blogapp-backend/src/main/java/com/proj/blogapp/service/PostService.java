package com.proj.blogapp.service;

import com.proj.blogapp.dto.PostDto;
import com.proj.blogapp.entities.Post;
import com.proj.blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto ,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePostById(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByUsers(Integer userId);

    List<PostDto> getPostByCategories(Integer categoryId);

    List<PostDto> searchPosts(String title);




}
