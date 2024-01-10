package com.board.basic.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("아이디, 비밀번호를 다시 확인해주세요.");
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            throw new RuntimeException("비밀번호 2개가 일치하지 않습니다.");
        }
        this.userService.create(userCreateForm.getUsername(),userCreateForm.getPassword2());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
