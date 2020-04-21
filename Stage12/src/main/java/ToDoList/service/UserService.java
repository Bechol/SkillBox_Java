package ToDoList.service;

import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new NoSuchElementException(String.format("Username %s not found", username))
                );
    }

    public User findUserById(Long userId) throws NoSuchElementException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User id:" + userId + " not found."));
    }

    public boolean saveUser(User newUser) {
        if (findUserByUsername(newUser.getUsername())) {
            log.warn("User {} exists. Select another user name.", newUser.getUsername());
            return false;
        }
        User user = new User();
        saveUserProperties(newUser, user);
        userRepository.save(user);
        log.info("User {} created.", newUser.getUsername());
        return true;
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

    public boolean resetUserPassword(Long userId, String standardPassword) {
        try {
            User user = findUserById(userId);
            user.setPassword(passwordEncoder.encode(standardPassword));
            userRepository.save(user);
            return true;
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUserProperties(User authenticatedUser, User editedUser) {

        String newUserName = editedUser.getUsername().trim();

        if (!newUserName.equals(authenticatedUser.getUsername()) &&
                userRepository.findUserByUsername(newUserName).isPresent()) {
            log.warn("User {} exist. Change username.", authenticatedUser.getUsername());
            return false;
        }

        authenticatedUser.setUsername(newUserName);
        authenticatedUser.setFirstName(editedUser.getFirstName());
        authenticatedUser.setLastName(editedUser.getLastName());
        authenticatedUser.setEmail(editedUser.getEmail());
        userRepository.save(authenticatedUser);
        log.info("User {} updated.", authenticatedUser.getUsername());
        return true;
    }

    public boolean updateUserPassword(User authenticatedUser, User editedUser) {
        String newPassword = editedUser.getNewPassword().trim();
        String confirmNewPassword = editedUser.getConfirmNewPassword().trim();

        if (!Strings.isNullOrEmpty(newPassword) && newPassword.equals(confirmNewPassword)) {
            authenticatedUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(authenticatedUser);
            log.info("User password updated.");
            return true;
        }
        log.warn("User {} password update failed.", authenticatedUser.getUsername());
        return false;
    }


    private boolean findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    private void saveUserProperties(User newUser, User user) {
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRoles(newUser.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .collect(Collectors.toSet()));
    }
}
