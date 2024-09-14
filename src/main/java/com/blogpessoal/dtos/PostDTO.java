package com.blogpessoal.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PostDTO(Long authorId, String title, List<Long> categoryIds, String description,
                      LocalDateTime dateOfCreation) {
}
