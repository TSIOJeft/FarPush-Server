package com.farplace.farpush.Push;

import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MiPush {
    public void PushMes(String regID, String title, String content, int through, String resource) {
        Sender sender = new Sender("SrajQYxyc/VNZMmIEUQl5A==");
        Map<String, String> extra = new HashMap<>();
        if (resource != null && resource.length() > 0) {
            extra.put("extra", resource);
        }
        Message message = new Message.Builder()
                .title(title)
                .description(content)
                .payload(content)
                .passThrough(through)
                .restrictedPackageName("com.farplace.farpush")
                .notifyId((int) (10000 * Math.random()))
                .extra(extra)
                .notifyType(1)
                .build();
        try {
            Result result = sender.send(message, regID, 3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
