package com.huangyingsheng.web.dao;

import com.huangyingsheng.web.entity.AccessToken;

public interface TokenMapper {

    AccessToken getTokenByAppId(String appId);

}
