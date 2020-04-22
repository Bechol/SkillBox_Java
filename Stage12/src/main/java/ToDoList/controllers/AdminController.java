package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("newUser", new User());
        return "appusers";
    }

    @GetMapping("/user/{userId}")
    public String getUserEditView(@PathVariable("userId") Long userId, Model model) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User id:" + userId + "not found."));
        model.addAttribute("user", user);
        return "userprofile";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User newUser) {
        if (!userRepository.findUserByUsername(newUser.getUsername()).isPresent()) {
            newUser.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
            newUser.setRoles(Collections.singleton(roleRepository.findByName(newUser.getRoleByAdmin())));
            userRepository.save(newUser);
            log.info("New user {} registarated by admin.", newUser.getUsername());
            return "redirect:/admin/users";
        }
        log.warn("User {} already exists", newUser.getUsername());
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/admin/users";
    }
}
