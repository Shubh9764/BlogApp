package com.proj.blogapp.controllers;

import com.proj.blogapp.dto.CommentDto;
import com.proj.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<?> createComment(@PathVariable Integer userId, @PathVariable Integer postId
    , @RequestBody CommentDto commentDto)
    {
       CommentDto newComment = commentService.createComment(commentDto,postId,userId);
       return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId){
        return new ResponseEntity<>("comment delted successfully with comment Id"+commentId,HttpStatus.OK);
    }

}
