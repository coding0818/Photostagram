package kr.co.photostagram.controller;

import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
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
    public void postUpload(PostVO vo){

        //log.info("files : "+files.get(0).getOriginalFilename());
        log.info("PostVO content: "+vo.getContent());
        log.info("PostVO urls: "+vo.getUrls()[0]);
        log.info("PostVO userno: "+vo.getUser_no());
    }
}
