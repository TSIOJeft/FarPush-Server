package com.farplace.farpush;

import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class FarPushApplication {
    public static void main(String[] args) {
        SpringApplication.run(FarPushApplication.class, args);
    }
}

