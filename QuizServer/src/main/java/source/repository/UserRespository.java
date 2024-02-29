package source.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import source.models.User;

public interface UserRespository extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);
}
