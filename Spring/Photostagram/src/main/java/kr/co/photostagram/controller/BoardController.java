package kr.co.photostagram.controller;

import kr.co.photostagram.service.BoardService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService service;
    @GetMapping("board/post")
    public String post(Principal principal, Model model, int no) {
        /*** 사용자, 프로필 페이지 사용자 ***/
        String myName = principal.getName();
        log.info("myName : " + myName);


        MemberVO user = service.selectMember(myName);

        BoardVO post = service.selectPost(no);

        BoardVO content = service.selectContent(no);

        log.info("post : " + post);

        List<Board1VO> hashes = service.selectPostHashTag(no);

        log.info("hashes : " + hashes);

        List<CommentVO> comments = service.selectcomments(no);

        model.addAttribute("user", user);
        model.addAttribute("post", post);
        model.addAttribute("content", content);
        model.addAttribute("hashes", hashes);
        model.addAttribute("comments", comments);

        return "board/post";
    }


}
