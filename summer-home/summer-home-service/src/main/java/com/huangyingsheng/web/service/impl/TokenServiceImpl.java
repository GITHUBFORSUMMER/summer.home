package com.huangyingsheng.web.service.impl;

import com.huangyingsheng.web.commom.encryption.SHA1Encryption;
import com.huangyingsheng.web.commom.map.SortMap;
import com.huangyingsheng.web.dao.TokenMapper;
import com.huangyingsheng.web.entity.AccessToken;
import com.huangyingsheng.web.model.response.TokenVo;
import com.huangyingsheng.web.service.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {


    @Resource
    private TokenMapper tokenMapper;

    @Override
    public TokenVo getWxJsConfig(String url, String appId) {
        AccessToken accessToken = tokenMapper.getTokenByAppId(appId);
        TokenVo tokenVo = new TokenVo();
        tokenVo.setJsapi_ticket(accessToken.getTicket());
        tokenVo.setAppId(appId);
        tokenVo.setNonceStr(UUID.randomUUID().toString().replace("-", "").substring(1, 10));
        tokenVo.setTimestamp(Long.toString(System.currentTimeMillis() / 1000));
        if (url.indexOf("#") > 0) {
            url = url.substring(0, url.indexOf("#"));
        }
        url = url.replace("http", "https");
        tokenVo.setUrl(url);
        tokenVo.setSignature(createSignature(tokenVo));
        return tokenVo;
    }


    private String createSignature(TokenVo tokenVo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("noncestr", tokenVo.getNonceStr());
        map.put("url", tokenVo.getUrl());
        map.put("timestamp", tokenVo.getTimestamp());
        map.put("jsapi_ticket", tokenVo.getJsapi_ticket());

        Map<String, String> sortMap = SortMap.sortMapByKey(map);
        String str = "";

        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            str += entry.getKey() + "=" + entry.getValue() + "&";
        }

        str = str.toString().substring(0, str.length() - 1);
        return SHA1Encryption.toSHA1(str).toLowerCase();
    }
}
