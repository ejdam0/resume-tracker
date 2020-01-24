package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strzelecki.resumetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
