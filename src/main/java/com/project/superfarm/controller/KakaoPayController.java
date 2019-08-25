package com.project.superfarm.controller;


import com.project.superfarm.service.KaKaoPay;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class KakaoPayController {

    @Autowired
    private KaKaoPay kaKaopay;

    public void kakaoPayGet(){

    }

    @PostMapping(path="/kakaoPay")
    public String kakaoPay(){
         log.info("kakaoPay post ...............................");

         return "redirect:"+ kaKaopay.kakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model){
        log.info("kakaoPaySuccess get....................................");
        log.info("kakaoPaySuccess pg_token:"+ pg_token);

        model.addAttribute("info",kaKaopay.kakaoPayInfo(pg_token));
    }

}
