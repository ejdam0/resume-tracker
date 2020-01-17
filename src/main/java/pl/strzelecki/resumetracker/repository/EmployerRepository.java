package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.strzelecki.resumetracker.entity.Employer;

@Repository("employerRepository")
public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
