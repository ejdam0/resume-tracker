package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.strzelecki.resumetracker.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
