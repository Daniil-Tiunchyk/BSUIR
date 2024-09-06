package bsuir.labwork.Labwork.services;

import bsuir.labwork.Labwork.models.User;
import bsuir.labwork.Labwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public boolean authenticateUser(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
