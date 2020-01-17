package pl.strzelecki.resumetracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.strzelecki.resumetracker.entity.User;
import pl.strzelecki.resumetracker.repository.UserRepository;
import pl.strzelecki.resumetracker.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long theId) {
        Optional<User> result = userRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        return result.get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long theId) {
        Optional<User> result = userRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Not found.");
        }
        userRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
