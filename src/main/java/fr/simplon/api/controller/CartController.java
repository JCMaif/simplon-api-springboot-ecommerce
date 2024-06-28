package fr.simplon.api.controller;

import fr.simplon.api.DTO.CartDTO;
import fr.simplon.api.DTO.LineDTO;
import fr.simplon.api.Exceptions.EntityNotFoundException;
import fr.simplon.api.model.Cart;
import fr.simplon.api.model.Line;
import fr.simplon.api.model.Product;
import fr.simplon.api.model.User;
import fr.simplon.api.repository.CartRepository;
import fr.simplon.api.repository.ProductRepository;
import fr.simplon.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id) throws EntityNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + id));
    }

    @PostMapping
    public Cart createCart(@RequestBody CartDTO cartDTO) {
        User user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User unknown"));

        Cart cart = new Cart(user);
        cart.setCreationDate(cartDTO.getCreationDate() != null ? cartDTO.getCreationDate() : LocalDateTime.now());

        List<Line> lines = new ArrayList<>();
        for (LineDTO lineDTO : cartDTO.getLines()) {
            Product product = productRepository.findById(lineDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product unknown"));
            Line line = new Line(product, lineDTO.getQuantity());
            lines.add(line);
        }
        cart.setLines(lines);

        return cartRepository.save(cart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Integer id, @RequestBody CartDTO cartDTO) throws EntityNotFoundException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + id));

        if (cartDTO.getUserId() != null) {
            User user = userRepository.findById(cartDTO.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User unknown"));
            cart.setUser(user);
        }

        if (cartDTO.getCreationDate() != null) {
            cart.setCreationDate(cartDTO.getCreationDate());
        }

        if (cartDTO.getLines() != null) {
            List<Line> lines = new ArrayList<>();
            for (LineDTO lineDTO : cartDTO.getLines()) {
                Product product = productRepository.findById(lineDTO.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product unknown"));
                Line line = new Line(product, lineDTO.getQuantity());
                lines.add(line);
            }
            cart.setLines(lines);
        }

        return cartRepository.save(cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Integer id) throws EntityNotFoundException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + id));

        cartRepository.delete(cart);
    }
}
