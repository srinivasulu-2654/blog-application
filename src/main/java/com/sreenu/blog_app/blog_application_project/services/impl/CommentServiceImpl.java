package com.sreenu.blog_app.blog_application_project.services.impl;

import com.sreenu.blog_app.blog_application_project.entities.Comments;
import com.sreenu.blog_app.blog_application_project.entities.Post;
import com.sreenu.blog_app.blog_application_project.exceptions.ResourceNotFoundException;
import com.sreenu.blog_app.blog_application_project.payloads.CommentDto;
import com.sreenu.blog_app.blog_application_project.repositories.CommentRepo;
import com.sreenu.blog_app.blog_application_project.repositories.PostRepo;
import com.sreenu.blog_app.blog_application_project.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","post id",postId));

        Comments comments = modelMapper.map(commentDto,Comments.class);

        comments.setPost(post);

        Comments savedComment = commentRepo.save(comments);

        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comments comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments","comment id",commentId));

        commentRepo.delete(comment);
    }
}
