package kr.co.photostagram.controller;

import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        MultipartFile files = vo.getFiles();
        log.info("files : "+files);
      log.info("PostVO content: "+vo.getContent());
      log.info("PostVO urls: "+vo.getUrls());
      log.info("PostVO userno: "+vo.getUser_no());
    }
}
