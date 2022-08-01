package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    /**
     * 生成token
     * @return
     */
    public static String genToken(String userId, String sign){
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) //两小时后token过期
                .sign(Algorithm.HMAC256(sign));
    }
}
