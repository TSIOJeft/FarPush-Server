package com.farplace.farpush.Push;

import com.google.gson.Gson;
import com.tencent.xinge.XingeApp;
import com.tencent.xinge.bean.AudienceType;
import com.tencent.xinge.bean.Message;
import com.tencent.xinge.bean.MessageAndroid;
import com.tencent.xinge.bean.MessageType;
import com.tencent.xinge.push.app.PushAppRequest;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TencentPush {
    public void PushMes(String token, String title, String content, int through, String resource) {
        XingeApp xingeApp = new XingeApp.Builder()
                .appId("1500032303")
                .secretKey("2451b4100cd2f04f51d92df3139b24be")
                .domainUrl("https://api.tpns.tencent.com/")
                .build();
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.token);
        if (through == 1) {
            pushAppRequest.setMessage_type(MessageType.message);
        } else {
            pushAppRequest.setMessage_type(MessageType.notify);
        }
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        pushAppRequest.setMessage(message);
        MessageAndroid messageAndroid = new MessageAndroid();
        if (resource != null) {
            messageAndroid.setCustom_content(resource);
        }
        message.setAndroid(messageAndroid);
        ArrayList<String> tokenList = new ArrayList();
        tokenList.add(token);
        pushAppRequest.setToken_list(tokenList);
        JSONObject ret = xingeApp.pushApp(pushAppRequest);
    }

}
