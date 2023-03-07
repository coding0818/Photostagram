package kr.co.photostagram.controller;

import kr.co.photostagram.service.MemberService;
import kr.co.photostagram.vo.BirthVO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    public MemberService service;

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @GetMapping("/register")
    public String register(){
        return "member/register";
    }

    @GetMapping("/birth")
    public String birth(){
        return "member/birth";
    }

    @GetMapping("/email")
    public String email(){
        return "member/email";
    }

    @GetMapping("/terms")
    public String terms(){
        return "member/terms";
    }

    @ResponseBody
    @PostMapping("/terms")
    public Map<String, Integer> terms(@RequestBody MemberVO vo){
        log.info("TermsController...");
        System.out.println("vo = " + vo);
//        int result = service.insertMember(vo);

        Map<String, Integer> resultMap = new HashMap<>();
//        resultMap.put("result", result);

        return null;
    }

    @GetMapping("/checkId")
    public String checkId(){
        return "member/checkId";
    }

    @GetMapping("/checkPass")
    public String checkPass(){
        return "member/checkPass";
    }

    @GetMapping("/resultId")
    public String resultId(){
        return "member/resultId";
    }

    @GetMapping("/resultPass")
    public String resultPass(){
        return "member/resultPass";
    }




/*
@PostMapping("/birth")
public String birth(@SessionAttribute("sessMember") MemberVO sessMember, BirthVO vo){

    String birth = String.format("%d-%d-%d", vo.getYear(), vo.getMonth(), vo.getDay());
    sessMember.setBirth(birth);

    System.out.println("sessMember = " + sessMember);

    return "redirect:/member/email";
}

 */

    /*
    @PostMapping("/register")
    public String register(MemberVO vo, HttpSession sess){
        log.info("register...");
        sess.setAttribute("sessMember", vo);

        return "redirect:/member/birth";
    }

     */

}
