package kr.co.photostagram.controller;

import kr.co.photostagram.service.BoardService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;
    @GetMapping("board/post")
    public String post(Principal principal, Model model, String username) {
        String myName = principal.getName();
        MemberVO user =  service.selectMember(myName);



        model.addAttribute("user", user);
        return "board/post";
    }


}
