package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.repositories.RoleRepository;
import ToDoList.repositories.UserRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping
    public String getRegPage() {
        return "registration";
    }

    @PostMapping
    public String registrateUser(@ModelAttribute User newUser) {

        if (Strings.isNullOrEmpty(newUser.getUsername().trim())) {
            return "registration";
        }

        if (!newUser.getPassword().equals(newUser.getConfirmNewPassword())) {
            return "registration";
        }

        if (!userRepository.findUserByUsername(newUser.getUsername()).isPresent()) {
            newUser.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
            userRepository.save(newUser);
        }

        return "redirect:/";
    }

}
