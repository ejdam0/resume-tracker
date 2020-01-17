package pl.strzelecki.resumetracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Resume;
import pl.strzelecki.resumetracker.repository.ResumeRepository;
import pl.strzelecki.resumetracker.service.ResumeService;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private ResumeRepository resumeRepository;

    @Autowired
    public ResumeServiceImpl(@Qualifier("resumeRepository") ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    @Override
    public Resume findById(long theId) {
        Optional<Resume> result = resumeRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        return result.get();
    }

    @Override
    public void save(Resume resume) {
        resumeRepository.save(resume);
    }

    @Override
    public void deleteById(long theId) {
        Optional<Resume> result = resumeRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        resumeRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        resumeRepository.deleteAll();
    }
}
