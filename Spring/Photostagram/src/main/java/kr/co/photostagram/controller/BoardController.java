package kr.co.photostagram.controller;

import kr.co.photostagram.service.BoardService;
import kr.co.photostagram.service.ProfileService;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;
    @GetMapping("board/post")
    public String post(Principal principal, Model model,String username) {
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


        model.addAttribute("member", member);
        model.addAttribute("user", user);
        return "board/post";
    }


}
