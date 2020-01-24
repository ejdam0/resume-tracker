package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.entity.Resume;

import java.time.LocalDate;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findResumeByTitleAndEmployerIdAndPostDateAndResponded(String title, Employer employer, LocalDate postDate, Boolean responded);
}
