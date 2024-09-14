package com.blogpessoal.repositories;

import com.blogpessoal.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findCategoriesById(Long id);
}
