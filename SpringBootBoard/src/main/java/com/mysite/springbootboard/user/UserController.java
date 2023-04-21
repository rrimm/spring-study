package com.mysite.springbootboard.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")  // get 방식으로 요청이 오면 회원가입을 위한 템플릿을 렌더링한다.(signup_form.html로 이동)
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup") // post 방식으로 요청이 오면 회원가입 진행
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            // bindingResult.rejectValue(필드명, 오류코드, 에러메세지) : 값이 일치하지 않는 경우 오류가 발생하도록 함. 특정 필드에 오류가 발생할 때 사용
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            // bindingResult.reject(오류코드, 에러메세지) : 일반적인 오류를 등록할 때 사용
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/question/list";
    }

    // @PostMapping 방식의 메서드는 스프링 시큐리티가 대신 처리
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
