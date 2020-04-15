package ToDoList.controllers;

import ToDoList.models.User;
import ToDoList.repositories.UserRepository;
import ToDoList.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1")
@Api(value = "ToDo administrator API", description = "Api для работы с делами")
public class AdminRestController {

    private static final String STANDARD_PASSWORD = "123#";

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allusers")
    @ApiOperation(value = "Получение списка всех пользователей.", response = ResponseEntity.class)

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Пользователи не найдены")
    })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> resultUserList = userRepository.findAll();
        if (resultUserList.size() > 0) {
            return ResponseEntity.ok(resultUserList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Получение пользователя по Id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь найден"),
            @ApiResponse(code = 404, message = "Пользователь не найден")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addnewuser")
    @ApiOperation(value = "Создание нового пользователя.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь создан."),
            @ApiResponse(code = 400, message = "Некорректный запрос.")
    })
    public ResponseEntity<Boolean> createNewUser(@RequestBody User newUser) {
        return userService.saveUser(newUser) ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/user/resetpassword/{userId}")
    @ApiOperation(value = "Сброс пароля пользователя по id.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пароль сброшен."),
            @ApiResponse(code = 404, message = "Пользователь не найден.")
    })
    public ResponseEntity<Boolean> resetUserPassword(@PathVariable Long userId) {
        return userService.resetUserPassword(userId, STANDARD_PASSWORD) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{userId}")
    @ApiOperation(value = "Удаление пользователя по id.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь удален."),
            @ApiResponse(code = 404, message = "Пользователь не найден.")
    })
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
