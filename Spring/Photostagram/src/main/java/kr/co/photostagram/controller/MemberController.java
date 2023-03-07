package kr.co.photostagram.controller;

import kr.co.photostagram.service.MemberService;
import kr.co.photostagram.vo.BirthVO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/register")
    public String register(MemberVO vo, HttpSession sess){
        log.info("register...");

        sess.setAttribute("sessMember", vo);

        return "redirect:/member/birth";
    }

    @GetMapping("/birth")
    public String birth(){
        return "member/birth";
    }

    @PostMapping("/birth")
    public String birth(@SessionAttribute("sessMember") MemberVO sessMember, BirthVO vo){

        String birth = String.format("%d-%d-%d", vo.getYear(), vo.getMonth(), vo.getDay());
        sessMember.setBirth(birth);

        System.out.println("sessMember = " + sessMember);

        return "redirect:/member/email";
    }

    @GetMapping("/email")
    public String email(){
        return "redirect:/member/terms";
    }

    @GetMapping("/terms")
    public String terms(@SessionAttribute ("sessMember") MemberVO sessMember){
        
        return "member/terms";
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
}
