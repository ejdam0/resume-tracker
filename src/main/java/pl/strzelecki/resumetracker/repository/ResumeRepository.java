package pl.strzelecki.resumetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.entity.Resume;

import java.time.LocalDate;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    boolean existsResumeByTitleAndEmployerIdAndPostDateAndResponded(String title, Employer employer, LocalDate postDate, Boolean responded);
}
