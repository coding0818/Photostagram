package kr.co.photostagram.controller;

import kr.co.photostagram.service.MyService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        model.addAttribute("user", user);
        model.addAttribute("cate", "interaction");
        return "my/interaction/like";
    }

    @GetMapping("my/interaction/comment")
    public String comment(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("cate", "interaction");
        return "my/interaction/comment";
    }

    @GetMapping("my/photos/posts")
    public String posts(Principal principal, Model model) {
        MemberVO user = profileService.selectMember(principal.getName());

        List<PostVO> articles = service.selectPosts(user.getNo(), 0);
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
        model.addAttribute("user", user);
        model.addAttribute("cate", "history");
        return "my/history";
    }

    @ResponseBody
    @PostMapping("my/delete")
    public Map<String, Integer> delete(@RequestParam("type") String type, @RequestParam("checkArray") int[] checkArray){
        Map<String, Integer> map = new HashMap<>();
        log.info("type : " + type);
        log.info("array : " + checkArray[0]);
        map.put("result", 1);
        return map;
    }

}
