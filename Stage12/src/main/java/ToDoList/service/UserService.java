package ToDoList.service;

import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) throws NoSuchElementException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User id:" + userId + " not found."));
    }

    public boolean createUser(User user) {
        if (findUserByUsername(user.getUsername())) {
            log.warn("User {} exists. Select another user name.", user.getUsername());
            return false;
        }
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean saveUser(User newUser) {
        if (findUserByUsername(newUser.getUsername())) {
            log.warn("User {} exists. Select another user name.", newUser.getUsername());
            return false;
        }
        User user = new User();
        updateUserProperties(newUser, user);
        userRepository.save(user);
        log.info("User {} created.", newUser.getUsername());
        return true;
    }

    public boolean updateUser(Long userId, User newUser) {
        try {
            User user = findUserById(userId);
            updateUserProperties(newUser, findUserById(userId));
            user.setTodos(newUser.getTodos());
            userRepository.save(user);
            log.info("User {} updated.", user.getUsername());
            return true;
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(Long userId) {
        try {
            userRepository.delete(findUserById(userId));
            return true;
        } catch (NoSuchElementException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return false;
    }

    private boolean findUserByUsername(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    private void updateUserProperties(User newUser, User user) {
        user.setUsername(newUser.getUsername());
        //TODO: BCrypt
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .collect(Collectors.toSet()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
