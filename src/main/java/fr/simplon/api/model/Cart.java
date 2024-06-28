package fr.simplon.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<Line> lines = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
        this.creationDate = LocalDateTime.now();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void removeLine(Line line) {
        lines.remove(line);
    }
}
