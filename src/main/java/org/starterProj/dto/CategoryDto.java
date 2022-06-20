package org.starterProj.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private Long products;

}
