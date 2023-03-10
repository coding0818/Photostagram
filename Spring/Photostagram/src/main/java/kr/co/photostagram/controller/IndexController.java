package kr.co.photostagram.controller;

import kr.co.photostagram.service.IndexService;
import kr.co.photostagram.service.MainService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.SearchListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private IndexService service;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MainService mainService;

    @GetMapping(value = {"/", "index"})
    public String index(Model model, Principal principal){

        List<PostVO> articles = service.selectArticles();
        List<CommentVO> comments = service.selectComment();
        MemberVO user =  profileService.selectMember(principal.getName());
        // 좋아요 확인용
        List<PostVO> likes = service.selectLikeConfirm();
        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        log.info("user_no : "+user.getNo());
        log.info("searchList : "+searchList);

        model.addAttribute("user", user);
        model.addAttribute("searchList", searchList);

        log.info("articles : " + articles);
        log.info("comments : " + comments);
        model.addAttribute("likes", likes);
        model.addAttribute("articles", articles);
        model.addAttribute("comments", comments);
        return "index";
    }

    // (조광호) 댓글 작성
    @PostMapping("CmtRegister")
    @ResponseBody
    public Map cmtRegister(@RequestBody CommentVO vo){
        int result = 0;
        result = service.insertComment(vo);

        log.info(" 댓글 result =====================> " + result);

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 좋아요 클릭 시
    @PostMapping("ArticleLikeAdd")
    @ResponseBody
    public Map ArticleLike(@RequestBody PostVO vo){
        int result = 0;
        result = service.insertArticleLikeAdd(vo);

        log.info(" =============================== ");
        log.info("     좋아요 result : " + result);
        log.info(" =============================== ");

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 좋아요 취소 시
    @PostMapping("DeleteArticleLike")
    @ResponseBody
    public Map deleteArticleLike(@RequestBody PostVO vo){
        int result = 0;
        result = service.deleteArticleLike(vo);

        log.info(" =============================== ");
        log.info("     좋아요 취소 : " + result);
        log.info(" =============================== ");
        Map map = new HashMap();
        map.put("result", result);

        return map;
    }
}
