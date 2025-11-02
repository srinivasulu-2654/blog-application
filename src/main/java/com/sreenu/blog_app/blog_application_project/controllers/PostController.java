package com.sreenu.blog_app.blog_application_project.controllers;

import com.sreenu.blog_app.blog_application_project.config.AppConstants;
import com.sreenu.blog_app.blog_application_project.entities.Post;
import com.sreenu.blog_app.blog_application_project.payloads.ApiResponse;
import com.sreenu.blog_app.blog_application_project.payloads.PostDto;
import com.sreenu.blog_app.blog_application_project.payloads.PostResponse;
import com.sreenu.blog_app.blog_application_project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // create

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId)
    {
         PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
         return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    // get by user

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto> posts = postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get all posts

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
    )
    {
        PostResponse postResponse = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    // get post detail by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

   // delete a post by id

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post is deleted successfully",true),HttpStatus.OK);
    }

    // update a post

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
    {
         PostDto updatePost = postService.updatePost(postDto,postId);
         return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }

    // searching feature

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {

        List<PostDto> result = postService.searchPosts(keywords);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
