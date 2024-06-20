package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsersTable(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "new";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("error", "Пользователь с именем '" + user.getUsername() + "' уже существует.");
            return "new";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}