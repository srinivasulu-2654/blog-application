package com.sreenu.blog_app.blog_application_project.services;

import com.sreenu.blog_app.blog_application_project.entities.Post;
import com.sreenu.blog_app.blog_application_project.payloads.PostDto;
import com.sreenu.blog_app.blog_application_project.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update

    PostDto updatePost(PostDto postDto, Integer postId);

   // delete

    void deletePost(Integer postId);

//    // get all posts (normal method)
//
//    List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);

    // get all posts (by pagination and all)

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    // get single post

    PostDto getPostById(Integer postId);

    // get all posts by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by User

    List<PostDto> getPostsByUser(Integer userId);

    // search posts

    List<PostDto> searchPosts(String keyword);


}
