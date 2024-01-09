package com.farplace.farpush.Controller;

import com.farplace.farpush.MyComponent;
import com.farplace.farpush.Push.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
public class PushController {

    FirebaseMessaging firebaseMessaging;

    @ResponseBody
    @PostMapping(value = "/PushWeChatMes")
    /**
     * OSType 1=>Miui
     *                  2=>Oppo
     *                  ....
     */
    public void PushWeChatMes(@RequestParam("regID") String regID, @RequestParam("title") String title,
                              @RequestParam("content") String content, @RequestParam("phone") int OSType,
                              @RequestParam(value = "through", required = false) Integer through, @RequestParam(value = "resource", required = false) String resource) {
        if (through == null) through = 0;
        if (resource == null) resource = "";
        if (OSType == 0) {
            new MiPush().PushMes(regID, title, content, through, resource);
        } else if (OSType == 1) {
            new OppoPush().PushMes(regID, title, content);
        } else if (OSType == 2) {
            if (System.currentTimeMillis() > MyComponent.hmExpires) {
                new HuaWeiPush().getAccessToken();
            }
            new HuaWeiPush().PushMsg(regID, title, content);
        } else if (OSType == 4) {
            new TencentPush().PushMes(regID, title, content, through, resource);
        } else if (OSType == 3) {
//            try {
//                if (firebaseMessaging == null) firebaseMessaging = firebaseMessaging();
//                String result=new FirebasePush(firebaseMessaging).sendNotification(regID, title, content);
//            } catch (FirebaseMessagingException | IOException e) {
//                e.printStackTrace();
//            }
        }else if(OSType==5){
            new BarkPush().PushMes(regID,title,content);
        }


    }

    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("farpush-firebase-service-account.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "com.farplace.farpush");
        return FirebaseMessaging.getInstance(app);
    }


}
