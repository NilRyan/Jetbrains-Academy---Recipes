package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
