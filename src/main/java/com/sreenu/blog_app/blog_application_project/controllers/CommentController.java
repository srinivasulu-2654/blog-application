package com.sreenu.blog_app.blog_application_project.controllers;

import com.sreenu.blog_app.blog_application_project.entities.Comments;
import com.sreenu.blog_app.blog_application_project.payloads.ApiResponse;
import com.sreenu.blog_app.blog_application_project.payloads.CommentDto;
import com.sreenu.blog_app.blog_application_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {

      CommentDto createdComment =   commentService.createComment(commentDto,postId);
      return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted Successfully!!",true), HttpStatus.OK);

    }

}
