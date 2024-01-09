package com.farplace.farpush.Push;

import com.farplace.farpush.Array.HMAccessArray;
import com.farplace.farpush.MyComponent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.farplace.farpush.MyComponent.hmAccess;

public class HuaWeiPush {
    RestTemplate restTemplate;

    public void PushMsg(String regID, String title, String content) {
        restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.set("Authorization", "Bearer " + hmAccess);
        Map<String, Object> body = new HashMap<>();
        Map<String, String> notification = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> android_notification = new HashMap<>();
        android_notification.put("title", title);
        android_notification.put("body", content);
        Map<String, Integer> click_action = new HashMap<>();
        click_action.put("type", 3);
        android_notification.put("click_action", click_action);
        Map<String, Object> android = new HashMap<>();
        android.put("notification", android_notification);
        notification.put("title", title);
        notification.put("body", content);
        List<String> tokens = new ArrayList<>();
        tokens.add(regID);
        message.put("notification", notification);
        message.put("token", tokens);
        message.put("android", android);
        body.put("validate_only", false);
        body.put("message", message);
        HttpEntity<String> httpEntity = new HttpEntity<>(new Gson().toJson(body), httpHeaders);
        ResponseEntity<JsonObject> responseEntity = restTemplate.
                exchange("https://push-api.cloud.huawei.com/v1/104977953/messages:send", HttpMethod.POST, httpEntity, JsonObject.class);
    }

    public void getAccessToken() {
        restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", "104977953");
        body.add("client_secret", "687aeaf8a15e17b2740c9547dcfe5ca3742d7f7d062774dcc7cbb711b8b836bd");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<HMAccessArray> responseEntity = restTemplate.exchange("https://oauth-login.cloud.huawei.com/oauth2/v3/token", HttpMethod.POST, httpEntity, HMAccessArray.class);
        if (responseEntity.hasBody()) {
            HMAccessArray hmAccessArray = responseEntity.getBody();
            if (hmAccessArray == null) return;
            MyComponent.setHmExpires(hmAccessArray.expires_in + System.currentTimeMillis());
            MyComponent.setHmAccess(hmAccessArray.access_token);
        }

    }
}
