package pl.strzelecki.resumetracker.uploadData.duplicateFinderService.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.uploadData.duplicateFinderService.EmployerInDatabaseChecker;
import pl.strzelecki.resumetracker.entity.Employer;
import pl.strzelecki.resumetracker.repository.EmployerRepository;

import java.util.Optional;

@Service
public class EmployerInDatabaseCheckerImpl implements EmployerInDatabaseChecker {

    private EmployerRepository employerRepository;

    @Autowired
    public EmployerInDatabaseCheckerImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Employer searchForEmployer(String dataEmployerName) {
        Optional<Employer> employerByName = employerRepository.findEmployerByName(dataEmployerName);
        if (employerByName.isPresent()) {
            return employerByName.get();
        } else {
            Employer newEmployerInDb = new Employer(dataEmployerName);
            employerRepository.save(newEmployerInDb);
            return newEmployerInDb;
        }
    }
}
