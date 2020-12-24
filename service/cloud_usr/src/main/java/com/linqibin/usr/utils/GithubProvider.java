package com.linqibin.usr.utils;

import com.alibaba.fastjson.JSON;
import com.linqibin.usr.entity.dto.AccessTokenDTO;
import com.linqibin.usr.entity.dto.GithubUser;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Github provider.
 *
 * @author hugh &you
 * @since 2020 /12/22 12:27
 */
@Component
public class GithubProvider {
    /**
     * 通过post方法提交授权码以交换令牌
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType Json = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String jsonString = JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(Json, jsonString);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()){
            String string = response.body().string();
            String s = string.split("&")[0].split("=")[1];
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过令牌去获得用户的个人信息
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient okHttpClient = new OkHttpClient();
        System.out.println(accessToken);
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
