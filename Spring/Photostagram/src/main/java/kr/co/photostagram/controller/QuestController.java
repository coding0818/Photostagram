package kr.co.photostagram.controller;

import kr.co.photostagram.service.QuestService;
import kr.co.photostagram.vo.ImageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class QuestController {
    @Autowired
    private QuestService service;

    @GetMapping("board/quest")
    public String quest(Model model){
        List<ImageVO> allImg = service.selectAllImg();

        model.addAttribute("allImg", allImg);
        return "board/quest";
    }
}
