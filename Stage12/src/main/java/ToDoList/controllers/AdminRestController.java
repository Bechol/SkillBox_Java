package ToDoList.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1/todo")
public class AdminRestController {

    @GetMapping
    public String getTest() {
        return "You are admin.";
    }
}
