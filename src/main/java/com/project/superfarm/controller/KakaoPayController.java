package com.project.superfarm.controller;


import com.project.superfarm.model.payment.KaKaoPayReadyVO;
import com.project.superfarm.model.payment.KakaoPayApprovalVO;
import com.project.superfarm.model.payment.KakaopayResult;
import com.project.superfarm.model.payment.OrderSend;
import com.project.superfarm.service.KaKaoPay;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
public class KakaoPayController {

    @Autowired
    private KaKaoPay kaKaopay;


    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @PostMapping(path = "/kakaoPay")
    public KaKaoPayReadyVO kakaoPay(@RequestBody OrderSend orderSend) {
        log.info("kakaoPay post ...............................");
        System.out.println(orderSend.toString() + " 확인 ");

        return kaKaopay.kakaoPayReady(orderSend);
    }

    @PreAuthorize("hasAnyRole('GUEST','CUSTOMER','ADMIN')")
    @PostMapping("/kakaoPaySuccess")
    public KakaoPayApprovalVO kakaoPaySuccess(@RequestBody KakaopayResult kakaopayResult) {
        log.info("kakaoPaySuccess get....................................");

        return kaKaopay.kakaoPayInfo(kakaopayResult);
    }

}
