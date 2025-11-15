package lt.lietuvosGamta.blog.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.lietuvosGamta.blog.dto.User;
import lt.lietuvosGamta.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/public/user/create")
    public String openUserForm(Model model) {
        System.out.println(">>> OPEN USER FORM KVIEČIAMAS <<<");
        model.addAttribute("user", new User());
        return "form/user";
    }

    @PostMapping("/public/user/create")
    public String createUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/user";
        }
        List<String>userNames = userService.getAllUserNames();
        for (String userName : userNames){
            if(userName.equals(user.getUsername())){
                user.setUsername("the user name you chose was already in use!!!");
                return "form/user";
            }
        }

        userService.createUser(user);
        return "redirect:/public/blog";
    }
}
