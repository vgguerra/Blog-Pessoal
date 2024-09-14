package com.blogpessoal.models;

import com.blogpessoal.dtos.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;

    @Column(nullable = false,unique = true)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "post_category",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "post_id"),  // Coluna de junção para Post
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Coluna de junção para Category
    )
    private List<Categories> categories;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false,name = "date")
    private LocalDateTime dateOfCreation;

    public Post(PostDTO postDTO, Users author, List<Categories> categories) {
        this.author = author;
        this.title = postDTO.title();
        this.categories = categories;
        this.description = postDTO.description();
        this.dateOfCreation = postDTO.dateOfCreation();
    }
}
