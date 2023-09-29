package com.proj.blogapp.controllers;

import com.proj.blogapp.constants.AppConstants;
import com.proj.blogapp.dto.PostDto;
import com.proj.blogapp.entities.Post;
import com.proj.blogapp.payload.PostResponse;
import com.proj.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto post = postService.createPost(postDto,userId,categoryId);
        return  new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto post = postService.updatePost(postDto,postId);
        return  new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId)
    {
       List<PostDto> posts = postService.getPostsByUsers(userId);

       return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> posts = postService.getPostByCategories(categoryId);

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                         @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String  sortBy,
                                         @RequestParam(value = "sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir)
    {
        PostResponse  posts = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostsById(@PathVariable Integer postId)
    {
        PostDto posts = postService.getPostById(postId);

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePostsById(@PathVariable Integer postId)
    {
        postService.deletePostById(postId);

        return new ResponseEntity<>("post deleted successfully with postId "+postId,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<?> searchPosts(@PathVariable String keyword){
        List<PostDto> postDtos = postService.searchPosts(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }




}
