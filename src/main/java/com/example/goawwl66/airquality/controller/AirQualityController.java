package com.example.goawwl66.airquality.controller;

import com.example.goawwl66.airquality.service.AirQualityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AirQualityController {

    // 생성자 주입방식으로 서비스 객체 생성
    private final AirQualityService airQualityService;
    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;

    }
    @GetMapping("/basic")
    public String basic(Model model) throws IOException {
        String result = airQualityService.getAirQualityDataBasic();
        System.out.println(result);
        model.addAttribute("airQualityData", result);

        return "airQuality";
    }


}
