package com.huangyingsheng.web.controller;

import com.huangyingsheng.web.model.response.BaseResponse;
import com.huangyingsheng.web.model.response.TokenVo;
import com.huangyingsheng.web.model.response.WxJsConfigVO;
import com.huangyingsheng.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/wechat")
@RestController
public class WeChatController {

    @Autowired
    private TokenService tokenService;

    private String appId = "wx80a7631fb6f3e4d2";

    @CrossOrigin(origins = "http://localhost:4000")
    @RequestMapping("get_share_token")
    public BaseResponse<WxJsConfigVO> getShareToken(@RequestBody @RequestParam("url") String url) {
        try {
            if (StringUtils.isEmpty(url)) {
                return null;
            }
            TokenVo wxJsConfig = tokenService.getWxJsConfig(url, appId);

            WxJsConfigVO response = new WxJsConfigVO();
            response.setDebug(false);
            response.setAppId(wxJsConfig.getAppId());
            response.setTimestamp(wxJsConfig.getTimestamp());
            response.setNonceStr(wxJsConfig.getNonceStr());
            response.setSignature(wxJsConfig.getSignature());
            response.setUrl(wxJsConfig.getUrl());
            response.setJsApiList(Arrays.asList("onMenuShareTimeline", "onMenuShareAppMessage"));

            return BaseResponse.ofSuccess(response);
        } catch (Exception ex) {
            return BaseResponse.ofBizError("error please read log");
        }
    }


}
