package kr.co.photostagram.controller;

import kr.co.photostagram.service.MainService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.utils.JSFunction;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
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
import java.util.*;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @Autowired
    private MainService mainService;

    @GetMapping(value = {"profile", "profile/index"})
    public String index(Principal principal, Model model, String username) {

        /*** 사용자 이름, 넘버 ***/
        String myName = principal.getName();

        // modal 팝업창 로그인정보 불러오기
        MemberVO user =  service.selectMember(username);
        log.info("user_no : "+user.getNo());

        MemberVO member = service.selectMember(myName);
        int myNo = member.getNo();      // 현재 로그인 된 사용자 번호
        int pageNo = user.getNo();      // 프로필 페이지 사용자 번호

        /*
        if (principal != null){
            System.out.println("name :"+ principal.getName());
        } else {
            System.out.println("noUser");
        }
         */
        
        /*** 사용자 게시물 ***/
        List<PostVO> posts = service.selectPosts(pageNo);

        Map<Integer, String> map = new HashMap<>();

        for (int i=0; i<posts.size(); i++){
            int postNo = posts.get(i).getNo();
            String thumb = service.selectThumb(postNo).getThumb();
            map.put(postNo, thumb);
        }

        /*** 게시물, 팔로워, 팔로잉 ***/
        int post = service.selectCountPost(pageNo);
        int myFollower = service.selectCountFollower(pageNo);
        int myFollowing = service.selectCountFollowing(pageNo);

        /*** 팔로워 팔로잉 버튼 ***/
        int following = service.selectMember(username).getNo();
        int follower = service.selectMember(myName).getNo();
        int result = service.searchFollowing(follower, following);  // 로그인 사용자가 현재 페이지 사용자를 팔로잉 했는지 여부 (0 = 하지않음, 1 = 함)

        /*** 게시물 최신 순으로 정렬 ***/
        Map<Integer, String> sortMap = new TreeMap<>(Comparator.reverseOrder());
        sortMap.putAll(map);


        // 검색기록 요청
        List<SearchListVO> searchList = mainService.selectSearchItemRecent(user.getNo());

        log.info("user_no : "+user.getNo());

        model.addAttribute("user", user);
        model.addAttribute("member", member);
        model.addAttribute("searchList", searchList);
        model.addAttribute("username", username);
        model.addAttribute("myName", myName);
        model.addAttribute("post", post);
        model.addAttribute("myFollower", myFollower);
        model.addAttribute("myFollowing", myFollowing);
        model.addAttribute("result", result);
        model.addAttribute("sortMap", sortMap);
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
