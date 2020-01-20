package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strzelecki.resumetracker.entity.Authorization;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
}
