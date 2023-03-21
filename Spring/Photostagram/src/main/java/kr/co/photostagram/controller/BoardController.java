package kr.co.photostagram.controller;

import kr.co.photostagram.service.BoardService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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

        /*** 게시물 작성자 ***/

        /*** 게시자 아이디 ***/
        MemberVO user = service.selectMember(myName);
        /*** 게시물 내용 ***/
        BoardVO post = service.selectPost(no);
        log.info("post : " + post);

        /*** 해쉬태그 ***/
        List<Board1VO> hashes = service.selectPostHashTag(no);
        log.info("hashes : " + hashes);

        /*** 댓글 ***/
        List<CommentVO> comments = service.selectcomments(no);
        log.info("comments : " + comments);

        /*** 게시물 사진 ***/
        List<ImageVO> images = service.selectimages(no);
        log.info("images : " + images);

        /*** 댓글 작성자 ***/

        /*** 게시물 댓글 ***/
        List<CommentVO> commentList = service.selectcommentlist(no);
        log.info("commentList : " + commentList);



        model.addAttribute("user", user);
        model.addAttribute("post", post);
        model.addAttribute("hashes", hashes);
        model.addAttribute("comments", comments);
        model.addAttribute("images", images);
        model.addAttribute("commentList", commentList);


        return "board/post";
    }


}
