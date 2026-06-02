package com.swapnil.smartcommerce.entity;
import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@Entity
@Table(name = "products")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;
}