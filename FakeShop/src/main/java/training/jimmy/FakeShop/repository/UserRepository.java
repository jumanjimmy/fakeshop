package training.jimmy.FakeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.jimmy.FakeShop.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
}
