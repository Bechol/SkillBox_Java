package ToDoList.service;

import ToDoList.models.Role;
import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import ToDoList.service.exceptions.NoAnyUserException;
import ToDoList.service.exceptions.UserNotFoundException;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Класс UserService.
 * Сервисный слой для User. Реализация бизнес логики.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    private static final String STANDARD_PASSWORD = "12345";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Messages messages;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, Messages messages) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.messages = messages;
    }

    /**
     * Метод findAllUsers.
     * Возврат коллекции всех зарегистрированных пользователей.
     *
     * @return коллекция зарегистрированных пользователей.
     */
    public List<User> findAllUsers() {
        return Optional.of(userRepository.findAll()).orElseThrow(
                () -> new NoAnyUserException(messages.getMessage("users.empty")));
    }

    /**
     * Метод loadUserByUsername.
     * Поиск пользователя по username.
     *
     * @param username имя пользователя.
     * @return UserDetails user.
     * @throws UsernameNotFoundException исключение, когда пользователь не найден по имени пользователя.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    /**
     * Метод findUserById.
     * Поиск пользователя по Id.
     *
     * @param userId Id пользователя в базе данных.
     * @return объект User.
     * @throws UserNotFoundException исключение, когда пользователь не найден по Id.
     */
    public User findUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(messages.getMessage("user.exception.userNotFound")));
    }

    /**
     * Метод registrateUser.
     * Регистрация пользователя.
     *
     * @param newUser новый пользователь.
     * @return true если пользователь успешно зарегистрирован.
     */
    public boolean registrateUser(User newUser) {
        if (isFormInputCorrect(newUser)) {
            newUser.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
            newUser.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
            userRepository.save(newUser);
            log.info(messages.getMessage("user.created"));
            return true;
        }
        return false;
    }

    /**
     * Метод registrateUserByAdmin.
     * Регистрация пользователя администратором.
     *
     * @param newUser новый пользователь.
     * @return true если пользователь успешно зарегистрирован.
     */
    public boolean registrateUserByAdmin(User newUser) {
        if (isFormInputCorrect(newUser)) {
            Role role = roleRepository.findByName(newUser.getRoleByAdmin());
            newUser.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
            newUser.setRoles(Collections.singleton(role));
            userRepository.save(newUser);
            log.info(messages.getMessage("user.created.byadmin"));
            return true;
        }
        return false;
    }

    /**
     * Метод deleteUser.
     * Удаление пользователя.
     *
     * @param userId Id пользователя.
     * @return true если пользователь успешно удален.
     */
    public boolean deleteUser(Long userId) {
        try {
            userRepository.delete(findUserById(userId));
            log.info(messages.getMessage("user.delete.ok", userId));
            return true;
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    /**
     * Метод resetUserPassword.
     * Сброс пароля пользователя.
     *
     * @param userId Id пользователя.
     * @return true - если пароль сброшен на стандартный.
     */
    public boolean resetUserPassword(Long userId) {
        try {
            User user = findUserById(userId);
            user.setPassword(passwordEncoder.encode(STANDARD_PASSWORD));
            userRepository.save(user);
            return true;
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    /**
     * Метод updateUserProperties.
     * Обновление параметров пользователя.
     *
     * @param authenticatedUser автоизованный пользователь.
     * @param editedUser        пользователь с измененными параметрами.
     * @return true если параметры обновлены.
     */
    public boolean updateUserProperties(User authenticatedUser, User editedUser) {

        String newUserName = editedUser.getUsername().trim();

        if (!newUserName.equals(authenticatedUser.getUsername()) &&
                userRepository.findUserByUsername(newUserName).isPresent()) {
            log.warn(messages.getMessage("user.update.userexist"));
            return false;
        }

        authenticatedUser.setUsername(newUserName);
        authenticatedUser.setFirstName(editedUser.getFirstName());
        authenticatedUser.setLastName(editedUser.getLastName());
        authenticatedUser.setEmail(editedUser.getEmail());
        userRepository.save(authenticatedUser);
        log.info(messages.getMessage("user.update.updated"));
        return true;
    }

    /**
     * Метод changeUserPassword.
     * Изменение пароля пользователя.
     *
     * @param authenticatedUser авторизованный пользователь.
     * @param editedUser        пользователь с измененным паролем.
     * @return true если пароль пользователя изменен на новый.
     */
    public boolean changeUserPassword(User authenticatedUser, User editedUser) {
        String newPassword = editedUser.getNewPassword().trim();
        String confirmNewPassword = editedUser.getConfirmNewPassword().trim();

        if (!Strings.isNullOrEmpty(newPassword) && newPassword.equals(confirmNewPassword)) {
            authenticatedUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(authenticatedUser);
            log.info("User password updated.");
            return true;
        }
        log.warn(messages.getMessage("user.password.update.failed"));
        return false;
    }


    /**
     * Метод isFormInputCorrect.
     * Проверка ввода данных на форме регистрации нового пользователя.
     *
     * @param newUser новый пользователь.
     * @return true если переданные данные корректны.
     */
    private boolean isFormInputCorrect(User newUser) {
        if (Strings.isNullOrEmpty(newUser.getUsername().trim())) {
            log.warn(messages.getMessage("user.registration.failed.username"));
            return false;
        }
        if (userRepository.findUserByUsername(newUser.getUsername()).isPresent()) {
            log.warn(messages.getMessage("user.registration.failed.userexists"));
            return false;
        }
        if (!newUser.getNewPassword().equals(newUser.getConfirmNewPassword())) {
            log.warn(messages.getMessage("user.registration.failed.passwords"));
            return false;
        }
        return true;
    }
}
