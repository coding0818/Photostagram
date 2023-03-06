package kr.co.photostagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping(value = {"/", "index"})
    public String index(){
        return "index";
    }

    @GetMapping("main")
    public String main(){
        return "main";
    }

    @ResponseBody
    @PostMapping("postUpload")
    public void postUpload(){

    }
}
