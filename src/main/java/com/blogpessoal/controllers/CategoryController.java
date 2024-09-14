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

    @PostMapping("/{id}")
    public ResponseEntity<Categories> createCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long id) throws Exception {
        Categories newCategory = this.service.newCategory(categoryDTO, id);
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

}
