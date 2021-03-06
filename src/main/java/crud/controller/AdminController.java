package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String list(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "admin/index";
    }

    @GetMapping(value = "/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/new";
    }

    @PostMapping(value = "/create")
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @ModelAttribute("header")
    public String populateHeader() {
        return "Welcome ADMIN";
    }



    @GetMapping(value = "/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("editUser", userService.getUser(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "/admin/edit";
    }

    @PutMapping(value = "/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("editUser") User user) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
