package com.tv.biz;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppEvent {
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Context REFRESH EVENT:" + event.toString());
    }
}
