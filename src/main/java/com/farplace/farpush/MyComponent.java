package com.farplace.farpush;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    @Value("${hm.expires}")
    public static long hmExpires = 0;
    @Value("${hm.access}")
    public static String hmAccess;

    public static void setHmAccess(String hmAccess) {
        MyComponent.hmAccess = hmAccess;
    }

    public static void setHmExpires(long hmExpires) {
        MyComponent.hmExpires = hmExpires;
    }
}
