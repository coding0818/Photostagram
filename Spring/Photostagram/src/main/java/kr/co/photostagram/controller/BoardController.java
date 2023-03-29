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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService service;
    @GetMapping("board/post")
    public String post(Principal principal, Model model, int no) {


        /*** 게시물 작성자 ***/

        //BoardVO user = service.selectMember(myName);
        //log.info("user : " + user);

        /*** 게시물 내용 ***/
        BoardVO post = service.selectPost(no);
        log.info("post : " + post);

        /*** 게시자 아이디, 프로필 ***/
        BoardVO user = BoardVO.builder()
                .user_no(post.getUser_no())
                .username(post.getUsername())
                .profileImg(post.getProfileImg())
                .build();



        /*** 해쉬태그 ***/
        List<Board1VO> hashes = service.selectPostHashTag(no);
        log.info("hashes : " + hashes);

        /*** 댓글 ***/
        List<CommentVO> comments = service.selectcomments(no);
        log.info("comments : " + comments);

        /*** 게시물 사진 ***/
        List<ImageVO> images = service.selectimages(no);
        log.info("images : " + images);

        /*** 게시물 작성 날짜 ***/
        PostVO content_like_time = service.selectContentLikeTime(no);


        /*** 게시물 댓글 ***/
        List<Board2VO> commentList = service.selectcommentlist(no);
        log.info("commentList : " + commentList);

        /*** 댓글 작성 시간 ***/
        List<NoticeVO> noticesTime = service.selectNoticesTime(no);

        model.addAttribute("user", user);
        model.addAttribute("post", post);
        model.addAttribute("hashes", hashes);
        model.addAttribute("comments", comments);
        model.addAttribute("images", images);
        model.addAttribute("commentList", commentList);
        model.addAttribute("noticesTime", noticesTime);
        model.addAttribute("content_like_time", content_like_time);


        return "board/post";
    }

    @ResponseBody
    @PostMapping("BoardComment")
    public Map<String, Object> boardComment(CommentVO vo){

        log.info("comment : " + vo.getComment());
        log.info("user_no : " + vo.getUser_no());
        log.info("post_no : " + vo.getPost_no());

        int result = service.insertComment(vo);

        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("comment", vo.getComment());
        map.put("user_no", vo.getUser_no());
        map.put("post_no", vo.getPost_no());

        return map;
    }


}
