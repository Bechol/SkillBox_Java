package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
@Api(value = "User API")
public class UserRestController {

    @Autowired
    UserService userService;

    public ResponseEntity<User> editProfile(@RequestBody User editedUser) {
        User authenticatedUser = getAuthenticatedUser();
        return (userService.updateUserProperties(authenticatedUser, editedUser)
                || userService.updateUserPassword(authenticatedUser, editedUser))
                ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
