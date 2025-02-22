package com.myblog.controller;


import com.myblog.dto.AddUserRequest;
import com.myblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);  //회원 가입 메서드 호출
        return "redirect:/login";  //회원가입 후 로그인 페이지로 이동
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        /* (-) 오.. Servlet이 사용되네.. 집가서 찾아봐야지 */

        //로그아웃 담당하는 핸들러의 logout()메서드 호출 -> 로그아웃
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        /* (-) SecuriryContextHolder는 뭘까!! 궁금하군!!*/

        return "redirect:/login";
    }

}
