package fr.simplon.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    public User(@NonNull String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(@NonNull String username) {
        this.username = username;
    }
}
