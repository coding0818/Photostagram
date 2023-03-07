package kr.co.photostagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("board/post")
    public String post() {
        return "board/post";
    }

}
