package com.blogpessoal.services;

import com.blogpessoal.dtos.CategoryDTO;
import com.blogpessoal.models.Categories;
import com.blogpessoal.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Categories newCategory(CategoryDTO categoryDTO) throws Exception {
//        this.userService.validateAdmin(this.userService.findByID(userId));
        Categories newCategory = new Categories(categoryDTO);
        this.categoriesRepository.save(newCategory);
        return newCategory;
    }

    public List<Categories> findAllCategories(){
        return this.categoriesRepository.findAll();
    }

    public Categories getCategory(Long id) throws Exception {
        return this.categoriesRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
    }

}
