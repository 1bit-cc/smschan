package com.example.smschan.chanapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.example.smschan.HttpURLConnectionWX;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeCom_API {
    String corpid = "";
    String corpsecret = "";
    String accessToken = "";

    public WeCom_API(String corpid, String corpsecret) {
        this.corpid = corpid;
        this.corpsecret = corpsecret;
    }

    public WeCom_API() {
    }

    // 通过企业微信API凭证获取企业微信API的AccessToken
    private String GetAccessToken() {
        String api_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + this.corpid + "&corpsecret="
                + this.corpsecret;
        HttpURLConnectionWX AccessTokenHttp = new HttpURLConnectionWX();
        String rAccessTokenHttp = AccessTokenHttp.doGet(api_url);
        JSONObject JSONrAccessTokenHttp = JSONObject.parseObject(rAccessTokenHttp);
        if (JSONrAccessTokenHttp.getString("errmsg").equals("ok")) {
            this.accessToken = JSONrAccessTokenHttp.getString("access_token");
            return "ok";
        } else {
            return JSONrAccessTokenHttp.getString("errcode");
        }
    }

    // 读取企业微信API的AccessToken的本地缓存
    private void AccessTokenReadLocal() throws IOException {
        File accessTokenFile = new File("./config/accessTokenFile.txt");
        byte[] bytesAccessToken = new byte[1024];
        StringBuffer sb = new StringBuffer();
        FileInputStream accessTokenFileIn = new FileInputStream(accessTokenFile);
        int len;
        while ((len = accessTokenFileIn.read(bytesAccessToken)) != -1) {
            sb.append(new String(bytesAccessToken, 0, len));
        }
        accessTokenFileIn.close();

        this.accessToken = sb.toString();
    }

    // 覆盖写入企业微信API的AccessToken的本地缓存
    private void AccessTokenWriteLocal() throws IOException {
        FileWriter accessTokenFile = new FileWriter("./config/accessTokenFile.txt");
        accessTokenFile.write(this.accessToken);
        accessTokenFile.close();
    }

    // 内部调用的消息文本发送函数
    private String SendTextToUserInside(String user, String agentid, String message) {
        String api_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + this.accessToken;
        String reqJSONStr = "{\"touser\":\"" + user + "\",\"msgtype\":\"text\",\"agentid\":\"" + agentid
                + "\",\"text\":{\"content\":\"" + message
                + "\"},\"safe\":0,\"enable_id_trans\":0,\"enable_duplicate_check\":0,\"duplicate_check_interval\":1800}";
        HttpURLConnectionWX SendTextHttp = new HttpURLConnectionWX();
        String rSendTextHttp = SendTextHttp.doPost(api_url, reqJSONStr);
        JSONObject JSONSendTextHttp = JSONObject.parseObject(rSendTextHttp);
        System.out.println(reqJSONStr);
        if (JSONSendTextHttp.getString("errmsg").equals("ok")) {
            return "ok";
        } else {
            return JSONSendTextHttp.getString("errcode");
        }
    }

    // 内部调用的消息卡片发送函数
    private String SendTextCardToUserInside(String user, String agentid, String message, String title,
            String description, String url, String btntxt) {
        String api_url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + this.accessToken;
        String reqJSONStr = "{\"touser\":\"" + user + "\",\"msgtype\":\"textcard\",\"agentid\":\"" + agentid
                + "\",\"textcard\":{\"title\":\"" + title + "\",\"description\":\"" + description + "\",\"btntxt\":\""
                + btntxt + "\",\"url\":\"" + url
                + "\"},\"safe\":0,\"enable_id_trans\":0,\"enable_duplicate_check\":0,\"duplicate_check_interval\":1800}";
        HttpURLConnectionWX SendTextCardHttp = new HttpURLConnectionWX();
        String rSendTextCardHttp = SendTextCardHttp.doPost(api_url, reqJSONStr);
        JSONObject JSONSendTextCardHttp = JSONObject.parseObject(rSendTextCardHttp);
        if (JSONSendTextCardHttp.getString("errmsg").equals("ok")) {
            return "ok";
        } else {
            return JSONSendTextCardHttp.getString("errcode");
        }
    }

    // 外部调用的消息文本发送函数
    // 集成了AccessToken失效时重试功能
    public String SendTextToUserExternal(String user, String agentid, String message) throws IOException {
        this.AccessTokenReadLocal();
        for (Integer n = 0; n < 3; n++) {
            String rdat = this.SendTextToUserInside(user, agentid, message);
            if (rdat.equals("ok")) {
                return "ok";
            } else if (rdat.equals("40014") || rdat.equals("42001")) {
                this.GetAccessToken();
                this.AccessTokenWriteLocal();
            }
        }
        return "err";
    }

    // 外部调用的消息卡片发送函数
    // 集成了AccessToken失效时重试功能
    public String SendTextCardToUserExternal(String user, String agentid, String message, String title,
            String description, String url, String btntxt) throws IOException {
        this.AccessTokenReadLocal();
        for (Integer n = 0; n < 3; n++) {
            String rdat = this.SendTextCardToUserInside(user, agentid, message, title, description, url, btntxt);
            if (rdat.equals("ok")) {
                return "ok";
            } else if (rdat.equals("40014") || rdat.equals("42001")) {
                this.GetAccessToken();
                this.AccessTokenWriteLocal();
            }
        }
        return "err";
    }
}
