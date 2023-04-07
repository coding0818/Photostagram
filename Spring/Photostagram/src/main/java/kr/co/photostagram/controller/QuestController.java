package kr.co.photostagram.controller;

import kr.co.photostagram.service.QuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class QuestController {
    @Autowired
    private QuestService service;

    @GetMapping("board/quest")
    public String quest(){

        return "board/quest";
    }
}
