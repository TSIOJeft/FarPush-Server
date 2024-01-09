package com.farplace.farpush.Push;

import com.google.gson.JsonObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BarkPush {
    public void PushMes(String token,String title,String content){
        JsonObject responseEntity = new RestTemplate().getForObject("https://api.day.app/"+token+"/"+title+"/"+content, JsonObject.class);
    }
}
