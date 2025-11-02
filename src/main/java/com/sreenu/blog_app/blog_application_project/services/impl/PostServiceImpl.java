package com.sreenu.blog_app.blog_application_project.services.impl;

import com.sreenu.blog_app.blog_application_project.entities.Category;
import com.sreenu.blog_app.blog_application_project.entities.Post;
import com.sreenu.blog_app.blog_application_project.entities.User;
import com.sreenu.blog_app.blog_application_project.exceptions.ResourceNotFoundException;
import com.sreenu.blog_app.blog_application_project.payloads.PostDto;
import com.sreenu.blog_app.blog_application_project.payloads.PostResponse;
import com.sreenu.blog_app.blog_application_project.repositories.CategoryRepo;
import com.sreenu.blog_app.blog_application_project.repositories.PostRepo;
import com.sreenu.blog_app.blog_application_project.repositories.UserRepo;
import com.sreenu.blog_app.blog_application_project.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","post id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

       Post updatedPost =  postRepo.save(post);

        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","post id",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

//        Pageable p = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber,pageSize, sort); // like above line also can write this also can write

        Page<Post> pagePost = postRepo.findAll(p);

        List<Post> posts = pagePost.getContent();

//        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();

        for(Post post : posts)
        {
            postDtos.add(modelMapper.map(post,PostDto.class));
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts = postRepo.findByCategory(cat);

        List<PostDto> postDtos = new ArrayList<>();

        for(Post p : posts)
        {
            postDtos.add(modelMapper.map(p,PostDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
        List<Post> posts = postRepo.findByUser(user);

        List<PostDto> postDtos = new ArrayList<>();

        for(Post p : posts)
        {
            postDtos.add(modelMapper.map(p,PostDto.class));
        }

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post> posts = postRepo.findByTitleContaining(keyword);

        List<PostDto> postDtos = new ArrayList<>();

        for(Post p : posts)
        {
            postDtos.add(modelMapper.map(p,PostDto.class));
        }

        return postDtos;
    }
}
