package com.farplace.farpush;

import com.farplace.farpush.Controller.PushController;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.farplace.farpush.MyComponent.hmExpires;

@Component
public class FarPushRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        MyComponent.setHmExpires(100);
    }
}
