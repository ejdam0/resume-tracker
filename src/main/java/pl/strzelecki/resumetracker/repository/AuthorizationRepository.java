package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.strzelecki.resumetracker.entity.Authorization;

@Repository("authorizationRepository")
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
}
