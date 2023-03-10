package kr.co.photostagram.controller;

import kr.co.photostagram.service.MemberService;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Member;
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

    @ResponseBody
    @PostMapping("/chkUserName")
    public Map<String, Integer> chkUserName(String userName){
        log.info("chkUsername...");
        System.out.println("uid = " + userName);

        int result = service.chkUserName(userName);

        System.out.println("result = " + result);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        
        return resultMap;
    }

    @ResponseBody
    @PostMapping("/chkEmail")
    public Map<String, Integer> chkEmail(String email) {
        log.info("chkEmail...");
        System.out.println("email = " + email);

        int result = service.chkEmail(email);
        System.out.println("result = " + result);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
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
    public Map<String, Integer> terms(@Valid @RequestBody MemberVO vo, BindingResult bindingResult){
        log.info("TermsController...");
        System.out.println("vo = " + vo);

        int result = service.insertMember(vo);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
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
