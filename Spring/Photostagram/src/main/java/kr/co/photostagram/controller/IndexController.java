package kr.co.photostagram.controller;

import kr.co.photostagram.service.IndexService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private IndexService service;

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = {"/", "index"})
    public String index(Model model, Principal principal){

        List<PostVO> articles = service.selectArticles();
        MemberVO user =  profileService.selectMember(principal.getName());
        log.info("user_no : "+user.getNo());

        model.addAttribute("user", user);

//        log.info("articles : " + articles);


        model.addAttribute("articles", articles);
        return "index";
    }

    // 댓글 작성
    @PostMapping
    public void replyRegister(){}
}
