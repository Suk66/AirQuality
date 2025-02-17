package com.example.goawwl66.airquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirController {
    @GetMapping("/air")
    niceToMeetYou(Model model) {
        model.addAttribute("air", "서울시 미세먼지");
        retrun "";
    }

}
