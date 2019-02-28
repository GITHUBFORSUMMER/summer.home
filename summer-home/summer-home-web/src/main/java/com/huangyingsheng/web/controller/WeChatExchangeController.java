package com.huangyingsheng.web.controller;

import com.huangyingsheng.web.commom.encryption.SHA1Encryption;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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

        String reponseXML = "";

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
            String xmlBody = getBody(httpServletRequest);
            System.out.println(xmlBody);

            Document dom = DocumentHelper.parseText(xmlBody);
            Element root = dom.getRootElement();
            String toUserName = root.element("ToUserName").getText();
            String fromUserName = root.element("FromUserName").getText();
            String createTime = root.element("CreateTime").getText();
            String msgType = root.element("MsgType").getText();
            String event = "";
            if (root.element("Event") != null) {
                event = root.element("Event").getText();
            }
            String eventKey = "";
            if (root.element("EventKey") != null) {
                eventKey = root.element("EventKey").getText();
            }

            switch (msgType) {
                case "text":
                    reponseXML = createReplyTextMsgXml(fromUserName, toUserName, "SUMMER正在忙其他的事情，晚点再回复你了哦！(^v^)!");
                    break;
                default:
                    reponseXML = createReplyTextMsgXml(fromUserName, toUserName, "SUMMER正在忙其他的事情，晚点再回复你了哦！(^v^)!");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reponseXML;
    }

    private String createReplyTextMsgXml(String toUser, String fromUser, String msg) {
        String xml = "<xml>" +
                "<ToUserName><![CDATA[{ToUser}]]></ToUserName>" +
                "<FromUserName><![CDATA[{FromUser}]]></FromUserName>" +
                "<CreateTime>{Time}</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[{Content}]]></Content>" +
                "</xml>";
        return xml.replace("{ToUser}", toUser).replace("{FromUser}", fromUser).replace("{Time}", Long.toString(System.currentTimeMillis())).replace("{Content}", msg);
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
