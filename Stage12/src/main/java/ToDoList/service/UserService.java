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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

        editedUser.setId(authenticatedUser.getId());
        if (!Strings.isNullOrEmpty(newUserName) && !newUserName.equals(authenticatedUser.getUsername())) {
            userRepository.save(editedUser);
            log.info("Username of {} updated.", authenticatedUser.getUsername());
            return true;
        }
        log.warn("Update user {} properties failed.", authenticatedUser.getUsername());
        return false;
    }

    public boolean updateUserPassword(User authenticatedUser, User editedUser) {

        String oldPassword = editedUser.getPassword();
        String newPassword = editedUser.getNewPassword();
        String confirmNewPassword = editedUser.getConfirmNewPassword();

        editedUser.setId(authenticatedUser.getId());
        if (!Strings.isNullOrEmpty(newPassword) && newPassword.equals(confirmNewPassword) &&
                passwordEncoder.matches(authenticatedUser.getPassword(), oldPassword)) {
            editedUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(editedUser);
            log.info("User {} password updated.", authenticatedUser.getUsername());
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
