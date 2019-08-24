package com.project.superfarm.service;

import com.project.superfarm.model.KaKaoPayReadyVO;
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

    public String kakaoPayReady(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","KakaoAk374981d6d639ec0a05fa450fbe39d126" );
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-type",MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String,String>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id","1001");
        params.add("partner_user_id","gorany");
        params.add("item_name","갤럭시S9");
        params.add("quantity","1");
        params.add("total_amount","2100");
        params.add("tax_free_amount","100");
        params.add("approval_url","http://localhost:3000/kakaoPaySuccess");
        params.add("cancel_url","http://localhost:300/kakaoPayCancel");
        params.add("fail_url","http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String,String>> body = new HttpEntity<MultiValueMap<String,String>>(params,headers);

        try{
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST +"/v1/payment/ready"),body, KaKaoPayReadyVO.class);

            log.info(""+kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        }catch(RestClientException e){
            //TODO AUTO-generated catch block
            e.printStackTrace();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }

        return "/pay";

    }


}
