package com.proj.blogapp.service.impl;

import com.proj.blogapp.dto.CommentDto;
import com.proj.blogapp.entities.Comment;
import com.proj.blogapp.entities.Post;
import com.proj.blogapp.entities.User;
import com.proj.blogapp.exceptions.ResourceNotFoundException;
import com.proj.blogapp.repo.CommentRepo;
import com.proj.blogapp.repo.PostRepo;
import com.proj.blogapp.repo.UserRepo;
import com.proj.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper mapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId",postId));
        Comment comment = mapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        comment = commentRepo.save(comment);
        return mapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepo.deleteById(commentId);
    }
}
