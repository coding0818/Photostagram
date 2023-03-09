package kr.co.photostagram.controller;

import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.service.SearchService;
import kr.co.photostagram.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SearchController {

    @Autowired
    private SearchService service;
    @Autowired
    private ProfileService profileService;

    @GetMapping(value = {"search", "search/index"})
    public String index(Principal principal, Model model){

        MemberVO user =  profileService.selectMember(principal.getName());

        model.addAttribute("user", user);

        return "search/index";
    }
}
