package pl.strzelecki.resumetracker.service;

import pl.strzelecki.resumetracker.entity.Authorization;

import java.util.List;

public interface AuthorizationService {
    List<Authorization> findAll();

    Authorization findById(long theId);

    void save(Authorization authorization);

    void deleteById(long theId);

    void deleteAll();
}
