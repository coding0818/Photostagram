package kr.co.photostagram.controller;

import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.utils.JSFunction;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping(value = {"profile", "profile/index"})
    public String index(Principal principal, Model model, String username) {

        // modal 팝업창 로그인정보 불러오기
        MemberVO user =  service.selectMember(username);
        log.info("user_no : "+user.getNo());

        model.addAttribute("user", user);

        String myName = principal.getName();

        if (principal != null){
            System.out.println("name :"+ principal.getName());
        } else {
            System.out.println("noUser");
        }

        int post = service.selectCountPost(user.getNo());
        int follower = service.selectCountFollower(user.getNo());
        int following = service.selectCountFollowing(user.getNo());

        model.addAttribute("username", username);
        model.addAttribute("myName", myName);
        model.addAttribute("post", post);
        model.addAttribute("follower", follower);
        model.addAttribute("following", following);
        return "profile/index";
    }

    @GetMapping("profile/modify")
    public String modify(Principal principal, Model model){

        MemberVO user =  service.selectMember(principal.getName());
        log.info("user_no : "+user.getNo());

        model.addAttribute("user", user);
        return "profile/modify";
    }

    @ResponseBody
    @PostMapping("profile/modify")
    public void modify(HttpServletResponse resp, MemberVO vo){
        log.info("user_no : "+vo.getNo());
        service.updateMember(vo);
        JSFunction.alertLocation(resp, "수정이 완료되었습니다.", "/Photostagram/profile/modify");
    }

    @ResponseBody
    @PostMapping("profile/upload")
    public Map<String, Integer> upload(@RequestParam("file") MultipartFile file, Principal principal){

        MemberVO member = service.selectMember(principal.getName());
        int no = member.getNo();

        if (member.getProfileImg() != null){
            service.deleteProfilePhoto(member.getProfileImg());
        }

        int result = service.uploadProfilePhoto(file, no);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;

    }

}
