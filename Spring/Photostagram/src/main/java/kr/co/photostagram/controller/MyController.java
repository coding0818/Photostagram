package kr.co.photostagram.controller;

import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("my/interaction/like")
    public String like(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());
        model.addAttribute("user", user);
        return "my/interaction/like";
    }

    @GetMapping("my/interaction/comment")
    public String comment(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());
        model.addAttribute("user", user);
        return "my/interaction/comment";
    }

}
