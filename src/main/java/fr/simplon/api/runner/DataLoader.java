package fr.simplon.api.runner;

import fr.simplon.api.model.Cart;
import fr.simplon.api.model.Line;
import fr.simplon.api.model.Product;
import fr.simplon.api.model.User;
import fr.simplon.api.repository.CartRepository;
import fr.simplon.api.repository.ProductRepository;
import fr.simplon.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        User frodon = new User("Frodon", "frodon@milieu.io", "SauronEnSlip");
        User sauron = new User("Sauron", "Sauron@mordor.io", "MonOeil");
        User gandalf = new User("Gandalf", "gandalf@milieu.io", "GrisClair");

        userRepository.save(frodon);
        userRepository.save(sauron);
        userRepository.save(gandalf);

        Product epee = new Product("Epée", 100.0f);
        Product arc = new Product("Arc", 150.0f);
        Product bouclier = new Product("Bouclier", 120.0f);

        productRepository.save(epee);
        productRepository.save(arc);
        productRepository.save(bouclier);

        Cart newCart = new Cart(frodon);
        newCart.addLine(new Line(epee, 1));
        newCart.addLine(new Line(bouclier, 2));

        cartRepository.save(newCart);

    }
}
