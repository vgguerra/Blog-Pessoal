package com.blogpessoal.controllers;

import com.blogpessoal.dtos.CategoryDTO;
import com.blogpessoal.models.Categories;
import com.blogpessoal.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        Categories newCategory = this.service.newCategory(categoryDTO);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories()  {
        List<Categories> categories = this.service.findAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) throws Exception {
        Categories categories = service.getCategory(id);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


    // USER: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nLWFwaSIsInN1YiI6ImVtYWlsdGVzdGUyQGdtYWlsLmNvbSIsImV4cCI6MTcyNjkzNDM4NH0.DgbH6jgs6P49O3F_UQL9Ke-U4GzQOzHCryXEgG3EnB8

    // ADMIN: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJibG9nLWFwaSIsInN1YiI6ImVtYWlsdGVzdGVAZ21haWwuY29tIiwiZXhwIjoxNzI2OTM0ODIxfQ.AZ4nyvidJzksRirsHAY-uuSIPI3WObx2xuM7TGYic1Q
}
