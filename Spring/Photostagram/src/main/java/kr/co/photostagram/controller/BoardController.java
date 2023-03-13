package kr.co.photostagram.controller;

import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BoardController {

    @Autowired
    private ProfileService service;

    @GetMapping("board/post")
    public String post(Principal principal, Model model) {
        String username = principal.getName();
        MemberVO user = service.selectMember(username);

        model.addAttribute("user", user);
        return "board/post";
    }

}
