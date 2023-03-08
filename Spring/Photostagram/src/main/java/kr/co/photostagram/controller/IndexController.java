package kr.co.photostagram.controller;

import kr.co.photostagram.service.IndexService;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private IndexService service;

    @GetMapping(value = {"/", "index"})
    public String index(Model model){

        List<PostVO> articles = service.selectArticles();

//        log.info("articles : " + articles);


        model.addAttribute("articles", articles);
        return "index";
    }

    // 댓글 작성
    @PostMapping
    public void replyRegister(){}
}
