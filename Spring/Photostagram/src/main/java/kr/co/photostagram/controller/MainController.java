package kr.co.photostagram.controller;

import kr.co.photostagram.service.MainService;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private MainService service;

    @GetMapping(value = {"/", "index"})
    public String index(){
        return "index";
    }

    // 댓글 작성
    @PostMapping
    public void replyRegister(){}

    @GetMapping("main")
    public String main(){
        return "main";
    }

    @ResponseBody
    @PostMapping("postUpload")
    public Map<String, Integer> postUpload(PostVO vo){

        log.info("vo : "+vo);

        int result = service.uploadFiles(vo);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        //log.info("files : "+files.get(0).getOriginalFilename());
        log.info("PostVO content: "+vo.getContent());
        log.info("PostVO urls: "+vo.getUrls()[0]);
        log.info("PostVO userno: "+vo.getUser_no());

        return resultMap;
    }
}
