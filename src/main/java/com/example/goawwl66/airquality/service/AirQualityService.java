package com.example.goawwl66.airquality.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
@Service
@Slf4j
public class AirQualityService {

    // API serviceKey 변수 선언 1-0
    private String serviceKey;

    // data.go.kr로 부터 미세먼지 정보를 가져옴
    public String getAirQualityDataBasic(String sidoName) throws IOException {
        // serviceKey 담기 1-1
        // window 환경변수 사용자에 +추가 app.serviceKey
        serviceKey = System.getenv("app.serviceKey");

        // API 요청을 위해 URL 구성
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey","UTF-8")).append("=").append(serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(sidoName, "UTF-8")); /*시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /*버전별 상세 결과 참고*/

        // new URL
        //
        URL url = new URL(urlBuilder.toString());

        //new HttpURLConnect
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // new StringBuilder
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        log.info("apiURL: {}", url);
        log.info("jsonResponse: {}", sb);

        // JSON 유호성 검사


        return sb.toString();
    }

    // getAirQualityDataBasic 개선 - RestTemplate
    public String getAirQualityDataRest(String sidoName) throws IOException {

        return null;

    }

    // getAirQualityDataRest 개선 - WebClient
    public String getAirQualityDataReactive(String sidoName) throws IOException {

        return null;
    }

    }
