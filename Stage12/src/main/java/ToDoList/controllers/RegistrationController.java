package ToDoList.controllers;

import ToDoList.config.security.PasswordConfig;
import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegPage(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping
    public String registrateUser(@ModelAttribute User newUser) {

        if (Strings.isNullOrEmpty(newUser.getUsername().trim())) {
            return "registration";
        }

        if (!newUser.getNewPassword().equals(newUser.getConfirmNewPassword())) {
            return "registration";
        }

        if (!userRepository.findUserByUsername(newUser.getUsername()).isPresent()) {
            newUser.setPassword(passwordEncoder.encode(newUser.getNewPassword()));
            newUser.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
            userRepository.save(newUser);
        }

        return "redirect:/";
    }

}
