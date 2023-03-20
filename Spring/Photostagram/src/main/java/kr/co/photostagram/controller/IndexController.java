package kr.co.photostagram.controller;

import kr.co.photostagram.service.IndexService;
import kr.co.photostagram.service.MainService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
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
    public String index(Model model, Principal principal) throws IndexOutOfBoundsException{
        // 전체 게시물 조회
        List<PostVO> articles = service.selectArticles();
        // 댓글 조회
        List<CommentVO> comments = service.selectComment();

        MemberVO user =  profileService.selectMember(principal.getName());
        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        List<MemberVO> members = service.selectUser();
        List<MemberVO> followings = service.selectFollowing(user.getNo());

        for(int i = 0; i < followings.size(); i++){
            MemberVO index = followings.get(i);

            members.remove(index);
        }
        for(int j = 0; j < members.size(); j++){
            MemberVO followfilter = members.get(j);
            if(followfilter.getNo() == user.getNo()){
                members.remove(followfilter);
            }

        }
//        log.info("articles : " + articles);
//        log.info("comments : " + comments);
        log.info("members : " + members);
//        log.info("user : " + user);
//        log.info("user_no : "+user.getNo());
//        log.info("searchList : "+searchList);
        model.addAttribute("members", members);
        model.addAttribute("user", user);
        model.addAttribute("searchList", searchList);

        // 알림
        List<NoticeVO> notices = mainService.selectNotices(user.getNo());
        log.info("notices : "+notices);

        model.addAttribute("notices", notices);
        model.addAttribute("articles", articles);
        model.addAttribute("comments", comments);

        return "index";
    }

    // 댓글 작성
    @PostMapping("CmtRegister")
    @ResponseBody
    public Map<String, Object> cmtRegister(@RequestBody CommentVO vo){

        service.insertComment(vo);
        Map<String, Object> map = new HashMap<>();

        map.put("result", 1);
        map.put("no",vo.getNo());
        map.put("user_no", vo.getUser_no());

        return map;
    }

    // 게시글 좋아요 클릭 시
    @PostMapping("ArticleLikeAdd")
    @ResponseBody
    @Transactional
    public Map ArticleLike(@RequestBody PostVO vo){
        int result = 0;
        // post_like : Insert
        result = service.insertArticleLikeAdd(vo);

        log.info(" =============================== ");
        log.info("     좋아요 result : " + result);
        log.info(" =============================== ");

        // Table : `post` , Column : like 업데이트
        if(result > 0){
            service.postLikeAddUpdate(vo);
        }

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 게시글 좋아요 취소 시
    @PostMapping("DeleteArticleLike")
    @ResponseBody
    public Map deleteArticleLike(@RequestBody PostVO vo){
        int result = 0;
        result = service.deleteArticleLike(vo);

        log.info(" =============================== ");
        log.info("     좋아요 취소 : " + result);
        log.info(" =============================== ");
        if(result > 0){
            service.postLikeDelUpdate(vo);
        }

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 댓글 좋아요 클릭 시
    @PostMapping("CommentLikeAdd")
    @ResponseBody
    @Transactional
    public Map CommentLike(@RequestBody Comment_likeVO vo){
        int result = 0;
        result = service.insertCommentLikeAdd(vo);

        log.info(" =============================== ");
        log.info("     댓글 좋아요 : " + result);
        log.info(" =============================== ");

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }

    // 댓글 좋아요 취소 시
    @PostMapping("CommentLikeDel")
    @ResponseBody
    @Transactional
    public Map CommentLikeDel(@RequestBody Comment_likeVO vo){
        int result = 0;
        result = service.deleteCommentLike(vo);

        log.info(" =============================== ");
        log.info("     댓글 좋아요취소 : " + result);
        log.info(" =============================== ");

        Map map = new HashMap();
        map.put("result", result);

        return map;
    }
}
