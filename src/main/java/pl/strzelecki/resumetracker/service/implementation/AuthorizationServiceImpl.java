package pl.strzelecki.resumetracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.Authorization;
import pl.strzelecki.resumetracker.repository.AuthorizationRepository;
import pl.strzelecki.resumetracker.service.AuthorizationService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private AuthorizationRepository authorizationRepository;

    @Autowired
    public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public List<Authorization> findAll() {
        return authorizationRepository.findAll();
    }

    @Override
    public Authorization findById(long theId) {
        Optional<Authorization> result = authorizationRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        return result.get();
    }

    @Override
    public void save(Authorization authorization) {
        authorizationRepository.save(authorization);
    }

    @Override
    public void deleteById(long theId) {
        Optional<Authorization> result = authorizationRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        authorizationRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        authorizationRepository.deleteAll();
    }
}
