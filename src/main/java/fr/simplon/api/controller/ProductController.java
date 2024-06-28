package fr.simplon.api.controller;

import fr.simplon.api.Exceptions.EntityNotFoundException;
import fr.simplon.api.model.Product;
import fr.simplon.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) throws EntityNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) throws EntityNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        if (productDetails.getName() != null) {
            product.setName(productDetails.getName());
        }
        if (productDetails.getPrice() != null) {
            product.setPrice(productDetails.getPrice());
        }

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) throws EntityNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}
