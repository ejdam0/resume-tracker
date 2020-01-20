package pl.strzelecki.resumetracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.repository.EmployerRepository;
import pl.strzelecki.resumetracker.service.EmployerService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public List<Employer> findAll() {
        return employerRepository.findAll();
    }

    @Override
    public Employer findById(long theId) {
        Optional<Employer> result = employerRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        return result.get();
    }

    @Override
    public void save(Employer employer) {
        employerRepository.save(employer);
    }

    @Override
    public void deleteById(long theId) {
        Optional<Employer> result = employerRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        employerRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        employerRepository.deleteAll();
    }
}
