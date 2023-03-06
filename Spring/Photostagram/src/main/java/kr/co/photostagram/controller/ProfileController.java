package kr.co.photostagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping(value = {"profile", "profile/index"})
    public String index() {
        return "profile/index";
    }

}
