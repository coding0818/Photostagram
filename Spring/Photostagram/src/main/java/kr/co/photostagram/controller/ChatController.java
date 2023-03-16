package kr.co.photostagram.controller;

import kr.co.photostagram.service.ChatService;
import kr.co.photostagram.service.MainService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.NoticeVO;
import kr.co.photostagram.vo.SearchListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ChatController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MainService mainService;

    @Autowired
    private ChatService service;

    @GetMapping(value={"chat", "chat/index"})
    public String chat(Principal principal, Model model){
        MemberVO user =  profileService.selectMember(principal.getName());
        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        log.info("user_no : "+user.getNo());
        log.info("searchList : "+searchList);

        model.addAttribute("user", user);
        model.addAttribute("searchList", searchList);

        // 알림
        List<NoticeVO> notices = mainService.selectNotices(user.getNo());

        log.info("notices : "+notices);

        model.addAttribute("notices", notices);
        return "chat/main";
    }

    @GetMapping("chat/content")
    public String content(Principal principal, Model model){
        MemberVO user =  profileService.selectMember(principal.getName());
        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        log.info("user_no : "+user.getNo());
        log.info("searchList : "+searchList);

        model.addAttribute("user", user);
        model.addAttribute("searchList", searchList);

        // 알림
        List<NoticeVO> notices = mainService.selectNotices(user.getNo());

        log.info("notices : "+notices);

        model.addAttribute("notices", notices);
        return "chat/content";
    }

    @ResponseBody
    @PostMapping("findAllUsers")
    public Map<String, List<MemberVO>> findAllUsers(String keyword){
        List<MemberVO> users = service.findAllUsers(keyword);

        Map<String, List<MemberVO>> resultMap = new HashMap<>();
        resultMap.put("users", users);

        return resultMap;
    }

    @ResponseBody
    @PostMapping("goChattingRoom")
    public Map<String, Integer> goChattingRoom(@RequestParam(value="user_no") ArrayList<Integer> user_no, int my_no){
        log.info("goChattingRoom...0"+user_no);
        log.info("goChattingRoom...1"+my_no);
        int result = 0;
        for(int user:user_no){
            log.info("goChattingRoom...2...for문");
            result = service.insertChatRoom(my_no, user);
        }
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        log.info("goChattingRoom...3...for끝");
        return resultMap;
    }
}
