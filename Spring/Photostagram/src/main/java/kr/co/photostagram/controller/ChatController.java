package kr.co.photostagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping(value={"chat", "chat/index"})
    public String chat(){
        return "chat/index";
    }
}
