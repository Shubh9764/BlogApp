package com.proj.blogapp.service.impl;

import com.proj.blogapp.dto.PostDto;
import com.proj.blogapp.dto.UserDto;
import com.proj.blogapp.entities.Category;
import com.proj.blogapp.entities.Post;
import com.proj.blogapp.entities.User;
import com.proj.blogapp.exceptions.ResourceNotFoundException;
import com.proj.blogapp.payload.PostResponse;
import com.proj.blogapp.repo.CategoryRepo;
import com.proj.blogapp.repo.PostRepo;
import com.proj.blogapp.repo.UserRepo;
import com.proj.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId", userId)));
        post.setCategory(categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId)));
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId", postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post = postRepo.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePostById(Integer postId) {
        postRepo.deleteById(postId);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(Sort.Direction.fromString(sortDir),sortBy));
        Page<Post> page = postRepo.findAll(pageable);
        List<Post> posts = page.getContent();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts.stream().map(post -> modelMapper.map(post,PostDto.class)).toList());
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
       return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByUsers(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId", userId));
        return postRepo.findByUser(user).stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostByCategories(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));
        return postRepo.findByCategory(category).stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPosts(String title) {
        List<Post> posts = postRepo.findByTitleContaining(title);
        return posts.stream().map(post -> modelMapper.map(post,PostDto.class)).toList();
    }
}
