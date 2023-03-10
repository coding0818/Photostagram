package kr.co.photostagram.controller;

import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.service.SearchService;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class SearchController {

    @Autowired
    private SearchService service;

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = {"search", "search/index"})
    public String index(Principal principal, Model model, int no){

        MemberVO user =  profileService.selectMember(principal.getName());

        log.info("user : "+user);
        
        // 해시태그 관련 게시물 조회
        List<PostVO> posts = service.selectPostGroupbyHashTag(no);

        // 해시태그 이름 조회
        String hashtagName = service.selectHashTagName(no);

        // 해시태그 게시물 개수 조회
        int countHashTag = service.selectCountHashTag(no);

        log.info("posts_hashtag : "+posts);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("countHashTag", countHashTag);
        model.addAttribute("hashtagName", hashtagName);
        return "search/index";
    }
}
