package pl.strzelecki.resumetracker.service;

import pl.strzelecki.resumetracker.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(long theId);

    void save(User user);

    void deleteById(long theId);

    void deleteAll();
}
