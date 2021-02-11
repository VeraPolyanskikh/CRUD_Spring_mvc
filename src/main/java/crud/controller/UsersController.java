package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    public final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String list(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "users/index";
    }

    @GetMapping(value = "/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping(value = "/create")
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @ModelAttribute("header")
    public String populateHeader() {
        return "Welcome";
    }

    @GetMapping(value = "/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/show";
    }

    @PatchMapping(value = "/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String update(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}
