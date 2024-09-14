package com.blogpessoal.models;

import com.blogpessoal.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50,unique = true)
    private String category;

    public Categories(CategoryDTO categoryDTO) {
        this.category = categoryDTO.category();
    }
}
