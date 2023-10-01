package com.proj.blogapp.service;

import com.proj.blogapp.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    void deleteComment(Integer commentId);
}
