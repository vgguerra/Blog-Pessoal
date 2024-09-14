package com.blogpessoal.services;

import com.blogpessoal.dtos.PostDTO;
import com.blogpessoal.models.Categories;
import com.blogpessoal.models.Post;
import com.blogpessoal.models.Users;
import com.blogpessoal.repositories.CategoriesRepository;
import com.blogpessoal.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Post newPost(PostDTO postDTO) throws Exception {
        Users author = userService.findByID(postDTO.authorId());
        List<Categories> categories = categoriesRepository.findAllById(postDTO.categoryIds());
        Post newPost = new Post(postDTO, author, categories);
        this.userService.validatePublication(newPost.getAuthor());
        this.postRepository.save(newPost);
        return newPost;
    }

    public List<Post> getAllPosts() throws Exception {
        return this.postRepository.findAll();
    }

}

