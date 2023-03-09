package kr.co.photostagram.controller;

import kr.co.photostagram.service.MainService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.utils.JSFunction;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.SearchListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @Autowired
    private MainService mainService;

    @GetMapping(value = {"profile", "profile/index"})
    public String index(Principal principal, Model model, String username) {

        // modal 팝업창 로그인정보 불러오기
        MemberVO user =  service.selectMember(username);
        log.info("user_no : "+user.getNo());

        /*** 사용자 이름 (user.username) ***/
        String myName = principal.getName();

        /*
        if (principal != null){
            System.out.println("name :"+ principal.getName());
        } else {
            System.out.println("noUser");
        }
         */

        /*** 게시물, 팔로워, 팔로잉 ***/
        int post = service.selectCountPost(user.getNo());
        int myFollower = service.selectCountFollower(user.getNo());
        int myFollowing = service.selectCountFollowing(user.getNo());

        /*** 팔로워 팔로잉 버튼 ***/
        int following = service.selectMember(username).getNo();
        int follower = service.selectMember(myName).getNo();

        int result = service.searchFollowing(follower, following);

        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        log.info("user_no : "+user.getNo());

        model.addAttribute("user", user);
        model.addAttribute("searchList", searchList);
        model.addAttribute("username", username);
        model.addAttribute("myName", myName);
        model.addAttribute("post", post);
        model.addAttribute("myFollower", myFollower);
        model.addAttribute("myFollowing", myFollowing);
        model.addAttribute("result", result);
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
        //log.info("user_no : "+vo.getNo());

        if (vo.getGender().length() > 2){
            vo.setGender(null);
        }

        service.updateMember(vo);
        JSFunction.alertLocation(resp, "수정이 완료되었습니다.", "/Photostagram/profile/modify");
    }

    /*** 프로필 사진 업로드 ***/
    @ResponseBody
    @PostMapping("profile/upload")
    public Map<String, Integer> upload(@RequestParam("file") MultipartFile file, Principal principal){

        MemberVO member = service.selectMember(principal.getName());
        int no = member.getNo();

        /*** 이전 사진이 있는 경우 삭제 ***/
        if (member.getProfileImg() != null){
            service.deleteProfilePhoto(member.getProfileImg());
        }

        /*** 사진 업로드 ***/
        int result = service.uploadProfilePhoto(file, no);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    @GetMapping("profile/follow")
    public String follow(String type, String userName, String myName){

        int following = service.selectMember(userName).getNo();
        int follower = service.selectMember(myName).getNo();

        if (("insert").equals(type)) {
            service.insertFollowing(follower, following);
        } else {
            service.deleteFollowing(follower, following);
        }

        return "redirect:/profile?username="+userName;
    }


}
