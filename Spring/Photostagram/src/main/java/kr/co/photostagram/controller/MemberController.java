package kr.co.photostagram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    날짜 : 2023/03/06
    이름 : 김진우
    내용 : member Controller
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

}
