package kr.co.photostagram.controller;

import kr.co.photostagram.service.MyService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.HistoryVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private MyService service;

    @Autowired
    private ProfileService profileService;

    @GetMapping("my/interaction/like")
    public String like(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());

        int[] postNo = service.selectLikePostNo(user.getNo());
        Map<Integer, PostVO> map = new HashMap<>();

        for (int i=0; i<postNo.length; i++){
            PostVO article = service.selectPost(postNo[i]);
            map.put(i, article);
        }

        //Map<Integer, PostVO> sortMap = new TreeMap<>(map);
        Map<Integer, PostVO> sortMap = new TreeMap<>(Comparator.reverseOrder());
        sortMap.putAll(map);
        System.out.println(sortMap);

        model.addAttribute("user", user);
        model.addAttribute("sortMap", sortMap);
        model.addAttribute("cate", "interaction");
        return "my/interaction/like";
    }

    @GetMapping("my/interaction/comment")
    public String comment(Principal principal, Model model) {

        MemberVO user = profileService.selectMember(principal.getName());

        List<PostVO> posts = service.selectMyCommentPosts(user.getNo());
        Map<Integer, PostVO> map = new HashMap<>();
        for (int i=0; i<posts.size(); i++) {
            List<CommentVO> comments = service.selectMyComments(posts.get(i).getNo(), user.getNo());
            posts.get(i).setComments(comments);
            map.put(i, posts.get(i));
        }
        Map<Integer, PostVO> sortMap = new TreeMap<>(Comparator.reverseOrder());
        sortMap.putAll(map);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("sortMap", sortMap);
        model.addAttribute("cate", "interaction");
        return "my/interaction/comment";
    }

    @GetMapping("my/photos/posts")
    public String posts(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());

        List<PostVO> articles = service.selectPosts(user.getNo());
        Map<Integer, PostVO> map = new HashMap<>();

        for (int i=0; i<articles.size(); i++){
            int artNo = articles.get(i).getNo();
            PostVO article = profileService.selectThumb(user.getNo(), artNo);
            map.put(i, article);
        }

        Map<Integer, PostVO> sortMap = new TreeMap<>(map);

        model.addAttribute("user", user);
        model.addAttribute("cate", "photos");
        model.addAttribute("sortMap", sortMap);
        return "my/photos/posts";
    }

    @GetMapping("my/history")
    public String history(Principal principal, Model model){
        MemberVO user = profileService.selectMember(principal.getName());
        List<HistoryVO> history = service.selectHistory(user.getNo());
        System.out.println(history);
        model.addAttribute("user", user);
        model.addAttribute("history", history);
        model.addAttribute("cate", "history");
        return "my/history";
    }

    @Transactional
    @ResponseBody
    @PostMapping("my/delete")
    public Map<String, Integer> delete(Principal principal,
                   @RequestParam("type") String type, @RequestParam("checkArray") int[] checkArray){
        log.info("here1");

        MemberVO user = profileService.selectMember(principal.getName());
        Map<String, Integer> map = new HashMap<>();
        int result = 0;

        System.out.println(checkArray);

        /*** 좋아요 삭제 ***/
        if ("like".equals(type)) {
            for (int i=0; i<checkArray.length; i++) {
                service.updateRemoveLike(checkArray[i]);
                result = service.deleteLike(checkArray[i], user.getNo());
            }
            
        /*** 업로드 게시물 삭제 ***/
        } else if ("photo".equals(type)){
            log.info("here2");
            for (int i=0; i<checkArray.length; i++) {
                int[] replyNumbers = service.selectCommentNo(checkArray[i]);
                for (int j = 0; j < replyNumbers.length; j++) {
                    System.out.println(replyNumbers[j]);
                    log.info("here3");
                    service.deleteCommentLike(replyNumbers[j]);
                }
                result = service.deletePost(checkArray[i]);
            }
            
        /*** 댓글 삭제 ***/
        } else if ("comment".equals(type)){
            for (int i=0; i<checkArray.length; i++){
                result = service.deleteComment(checkArray[i]);
            }

        }

        map.put("result", result);
        return map;

    }

}
