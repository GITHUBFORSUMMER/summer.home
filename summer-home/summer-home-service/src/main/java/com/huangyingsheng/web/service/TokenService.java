package com.huangyingsheng.web.service;

import com.huangyingsheng.web.model.vo.TokenVo;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    TokenVo getWxJsConfig(String url,String appId);

}
