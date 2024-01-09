package com.farplace.farpush.Push;

import com.oppo.push.server.Notification;
import com.oppo.push.server.Result;
import com.oppo.push.server.Sender;
import com.oppo.push.server.Target;

import java.util.Map;

public class OppoPush {
    public void PushMes(String regID, String title, String content) {
        try {
            Sender sender = new Sender("38591f42a8fc4713ad9e8879be70aa35", "3b1907d5c8184c68a37e3affd14c6a4e");
            Target target = Target.build(regID);
            Result result = sender.unicastNotification(getNotification(title, content), target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Notification getNotification(String title, String content) {
        int notifyID = (int) (System.currentTimeMillis() * Math.random());
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        notification.setStyle(1);
        notification.setNetworkType(0);
        notification.setAppMessageId(notifyID + "");
        notification.setShowTimeType(0);
        notification.setNotifyId(notifyID);
        return notification;
    }
}
