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

import javax.annotation.Resource;
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
        
        /*** 사용자, 프로필 페이지 사용자 ***/
        String myName = principal.getName();
        MemberVO member =  service.selectMember(username);
        MemberVO user = service.selectMember(myName);
        
        int myNo = user.getNo();          // 현재 로그인 된 사용자 번호
        int pageNo = member.getNo();      // 프로필 페이지 사용자 번호

        /*** 사용자 게시물 ***/
        List<PostVO> posts = service.selectPosts(pageNo);           // 게시물 목록
        Map<Integer, String> map = new HashMap<>();                 // 맵 생성

        for (int i=0; i<posts.size(); i++){                         // 게시물 갯수만큼 반복
            int postNo = posts.get(i).getNo();                      // 게시물 번호
            String thumb = service.selectThumb(postNo).getThumb();  // 게시물 당 첫번째 사진 불러오기 (`image` 내에서 같은 `post_no` 중 가장 작은 `no`값의 `thumb`)
            map.put(postNo, thumb);                                 // 게시물 번호(key) + 게시물 썸네일 (value)로 맵에 전달
        }

        /*** 게시물 최신 순으로 정렬 ***/
        Map<Integer, String> sortMap = new TreeMap<>(Comparator.reverseOrder());    // 게시물 번호(key) 기준으로 역순 정렬
        sortMap.putAll(map);                                                        // 새로운 맵에 put

        /*** 게시물, 팔로워, 팔로잉 ***/
        int post = service.selectCountPost(pageNo);                 // 게시물 갯수
        int myFollower = service.selectCountFollower(pageNo);       // 팔로워 수
        int myFollowing = service.selectCountFollowing(pageNo);     // 팔로잉 하는 수

        List<MemberVO> myFollowers = service.selectFollowers(pageNo);   // 팔로워 목록
        List<MemberVO> myFollowings = service.selectFollowings(pageNo); // 팔로잉 목록

        /*** 팔로잉 목록 유저들 현재 사용자가 팔로잉 중인지 검색 ***/
        int[] followingArray = new int [myFollowings.size()];
        Map<MemberVO, Integer> followingMap = new HashMap<>();

        for (int i=0; i<myFollowings.size(); i++){
            MemberVO currentUser = service.selectMember(myFollowings.get(i).getUsername());
            followingArray[i] = currentUser.getNo();
            int j = service.searchFollowing(myNo, followingArray[i]);

            followingMap.put(currentUser, j);
            //System.out.println("service1 : "+ j);
            //System.out.println("array1 : "+ followingArray[i]);
        }



        int result = 0;
        Map<MemberVO, Integer> followerMap = new HashMap<>();

        /*** 팔로워 목록 유저들 현재 사용자가 팔로잉 중인지 검색 ***/

        /*** 현재 페이지 사용자와 로그인 사용자가 다른 경우에만 수행 ***/
        if (!username.equals(myName)){
            int[] followerArray = new int[myFollowers.size()];
            for (int i=0; i<myFollowers.size(); i++){
                MemberVO currentUser = service.selectMember(myFollowers.get(i).getUsername());
                followerArray[i] = currentUser.getNo();
                int j = service.searchFollowing(myNo, followerArray[i]);

                followerMap.put(currentUser, j);
                //System.out.println("service2 : "+ j);
            }
            System.out.println("array2 : "+ followerArray);

            /*** 팔로워 팔로잉 버튼 ***/
            result = service.searchFollowing(myNo, pageNo);  // 로그인 사용자가 현재 페이지 사용자를 팔로잉 했는지 여부 (0 = 하지않음, 1 = 함)
            
        /*** 현재 페이지 사용자와 로그인 사용자가 같은 경우 팔로워 목록만 저장 ***/
        } else {
            int[] followerArray = new int[myFollowers.size()];
            for (int i=0; i<myFollowers.size(); i++){
                MemberVO currentUser = service.selectMember(myFollowers.get(i).getUsername());
                followerArray[i] = currentUser.getNo();
                followerMap.put(currentUser, 1);
            }
        }



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
        model.addAttribute("myFollowers", myFollowers);
        model.addAttribute("myFollowing", myFollowing);
        model.addAttribute("myFollowings", myFollowings);
        model.addAttribute("result", result);
        model.addAttribute("sortMap", sortMap);
        model.addAttribute("followingMap", followingMap);
        model.addAttribute("followerMap", followerMap);
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

    @ResponseBody
    @GetMapping("profile/upload")
    public Map<String, Integer> upload(Principal principal, String type){
        Map<String, Integer> resultMap = new HashMap<>();
        String userName = principal.getName();
        int no = service.selectMember(userName).getNo();
        String photo = service.selectMember(userName).getProfileImg();

        int result = service.uploadProfilePhoto(type, null, no);
        service.deleteProfilePhotoFile(photo);

        resultMap.put("result", result);
        return resultMap;
    }

    /*** 프로필 사진 업로드 ***/
    @ResponseBody
    @PostMapping("profile/upload")
    public Map<String, Integer> upload(@RequestParam("file") MultipartFile file, Principal principal){

        MemberVO member = service.selectMember(principal.getName());
        int no = member.getNo();

        /*** 이전 사진이 있는 경우 삭제 ***/
        if (member.getProfileImg() != null){
            service.deleteProfilePhotoFile(member.getProfileImg());
        }

        /*** 사진 업로드 ***/
        int result = service.uploadProfilePhoto("insert", file, no);
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

    @ResponseBody
    @PostMapping("profile/follow")
    public Map<String, Integer> follow(Principal principal, @RequestParam("type") String type, @RequestParam("userName") String userName){

        System.out.println("userName : "+userName);
        System.out.println("type : "+type);


        String myName = principal.getName();
        int following = service.selectMember(userName).getNo();
        int follower = service.selectMember(myName).getNo();
        int result = 0;

        if (("insert").equals(type)){
            result = service.insertFollowing(follower, following);
        } else if (("delete").equals(type)) {
            result = service.deleteFollowing(follower, following);
        } else if (("myDelete").equals(type)){
            result = service.deleteFollowing(following, follower);
        }

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        System.out.println("result : "+ result);

        return resultMap;
    }


}
