package com.project.superfarm.service;

import com.project.superfarm.model.payment.KaKaoPayReadyVO;
import com.project.superfarm.model.payment.KakaopayResult;
import com.project.superfarm.model.payment.KakaoPayApprovalVO;
import com.project.superfarm.model.payment.OrderSend;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Log
@Service
public class KaKaoPay {


    private static final String HOST = "https://kapi.kakao.com";

    private KaKaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public KaKaoPayReadyVO kakaoPayReady(OrderSend orderSend) {

        RestTemplate restTemplate = new RestTemplate();


        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "374981d6d639ec0a05fa450fbe39d126");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", orderSend.getPartner_order_id());
        params.add("partner_user_id", orderSend.getPartner_user_id());
        params.add("item_name", orderSend.getItem_name());
        params.add("quantity", orderSend.getQuantity());
        params.add("total_amount", orderSend.getTotal_amount());
        params.add("tax_free_amount", orderSend.getTax_free_amount());
        params.add("approval_url", "http://localhost:3000/orderSuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KaKaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);
            System.out.println(" 확인 URL "+kakaoPayReadyVO.getNext_redirect_pc_url());
            return kakaoPayReadyVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new KaKaoPayReadyVO();

    }

    public KakaoPayApprovalVO kakaoPayInfo(KakaopayResult kaKaoPayResult){
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "374981d6d639ec0a05fa450fbe39d126");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kaKaoPayResult.getTid());
        params.add("partner_order_id", kaKaoPayResult.getPartner_order_id());
        params.add("partner_user_id", kaKaoPayResult.getPartner_user_id());
        params.add("pg_token", kaKaoPayResult.getPg_token());
        params.add("total_amount", kaKaoPayResult.getTotal_amount());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


}
