package com.farplace.farpush.Push;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class FirebasePush {
    String token = "BIqt7jIXPp73vUb2VnURVNpsNFqg4bfTCPzLx9bqldSSq3ey1OEav0ru87kVyaOf4Z9uVax2PJCLROrqHNzswxU";

    public String sendNotification(String token, String title, String content) {
        Map<String, String> note = new HashMap<>();
        note.put("title", title);
        note.put("content", content);
        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(content)
                .build();


        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(note)
                .build();

        try {
            return FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}