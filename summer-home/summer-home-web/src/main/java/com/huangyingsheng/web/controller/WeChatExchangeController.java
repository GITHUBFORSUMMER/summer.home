package com.huangyingsheng.web.controller;

import com.huangyingsheng.web.commom.encryption.SHA1Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeChatExchangeController {

    private String WECHAT_TOKEN = "SUMMER";

    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping(value = "/processMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public String processMessage() {

        String signature = httpServletRequest.getParameter("signature");
        String timestamp = httpServletRequest.getParameter("timestamp");
        String nonce = httpServletRequest.getParameter("nonce");
        String echostr = httpServletRequest.getParameter("echostr");

        if (signature == null || timestamp == null || nonce == null) {
            return "";
        }
        if (signature.isEmpty() || timestamp.isEmpty() || nonce.isEmpty()) {
            return "";
        }

        List<String> parameList = new ArrayList<String>();
        parameList.add(timestamp);
        parameList.add(nonce);
        parameList.add(WECHAT_TOKEN);
        parameList.sort(String::compareTo);
        String p = String.join("", parameList);
        String sign = SHA1Encryption.toSHA1(p).toLowerCase();
        if (!sign.equals(signature)) {
            return "";
        }
        if (echostr != null && !echostr.isEmpty()) {
            return echostr;
        }

        try {
            String body = getBody(httpServletRequest);
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    private String getBody(HttpServletRequest request) throws IOException {
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        if (in != null) {
            in.close();
        }
        if (br != null) {
            br.close();
        }
        return sb.toString();
    }


}
