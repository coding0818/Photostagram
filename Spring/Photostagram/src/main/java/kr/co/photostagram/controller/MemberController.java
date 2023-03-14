package kr.co.photostagram.controller;

import kr.co.photostagram.service.MailService;
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

    @Autowired
    public MailService mailService;

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @GetMapping("/register")
    public String register(){
        return "member/register";
        //return "member/registerLayout";
    }

    @ResponseBody
    @PostMapping("/chkUserName")
    public Map<String, Integer> chkUserName(String userName){
        log.info("chkUsername...");

        int result = service.chkUserName(userName);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        
        return resultMap;
    }

    @ResponseBody
    @PostMapping("/chkEmail")
    public Map<String, Integer> chkEmail(String email) {
        log.info("chkEmail...");

        int result = service.chkEmail(email);

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

    @ResponseBody
    @PostMapping("/sendEmail")
    public Map<String, Integer> sendEmail(@RequestParam String email) throws Exception{
        log.info("Email Auth...");
//        System.out.println("email = " + email);
        int result = 0;

        int confirm = mailService.sendEmail(email);
//        System.out.println("confirm = " + confirm);
        if(confirm != 0){
            result = 1;
        }
//        System.out.println("result = " + result);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        resultMap.put("confirm", confirm);

        return resultMap;
    }

    @GetMapping("/terms")
    public String terms(){
        return "member/terms";
    }


    @ResponseBody
    @PostMapping("/terms")
    public Map<String, Integer> terms(@Valid @RequestBody MemberVO vo, BindingResult error){
        log.info("TermsController...");
//        System.out.println("vo = " + vo);
//        System.out.println("error = " + error.hasErrors());

        int result = 0;

        if(!error.hasErrors()){ // false면 에러가 없다.
            log.info("No errors");
            result = service.insertMember(vo);
        }else{
            log.info("errors occurred");
        }
//        System.out.println("result = " + result);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }

    @GetMapping("/checkId")
    public String checkId(){
        return "member/checkId";
    }

//    @PostMapping
//    public String checkId() {
//        return null;
//    }

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
