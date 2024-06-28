package fr.simplon.api.repository;

import fr.simplon.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

    public Optional<User> findByUsernameAndPassword(String username, String password);
}
