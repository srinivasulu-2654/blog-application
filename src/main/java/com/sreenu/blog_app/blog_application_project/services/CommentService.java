package com.sreenu.blog_app.blog_application_project.services;

import com.sreenu.blog_app.blog_application_project.payloads.CommentDto;

public interface CommentService {

     CommentDto createComment(CommentDto commentDto,Integer postId);

     void deleteComment(Integer commentId);
}
