package kr.co.photostagram.controller;

import kr.co.photostagram.entity.UserEntity;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping(value = {"profile", "profile/index"})
    public String index(Principal principal, Model model, String username) {

        String myName = principal.getName();

        if (principal != null){
            System.out.println("name :"+ principal.getName());
        } else {
            System.out.println("noUser");
        }

        model.addAttribute("username", username);
        model.addAttribute("myName", myName);
        return "profile/index";
    }

    @GetMapping("profile/modify")
    public String modify(Principal principal, Model model){

        MemberVO user =  service.selectMember(principal.getName());

        model.addAttribute("user", user);
        return "profile/modify";
    }

    @PostMapping("profile/modify")
    public String modify(Principal principal, MemberVO vo, Model model){
        service.updateMember(vo);
        return "profile/modify";
    }

}
