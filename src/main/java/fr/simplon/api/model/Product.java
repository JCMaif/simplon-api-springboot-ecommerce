package fr.simplon.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String name;

    private Float price;

    public Product(@NonNull String name, Float price) {
        this.name = name;
        this.price = price;
    }
}
