package ua.com.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ua.com.shop.service.MainService;

@Component
public class TestDataCreation {

    @Autowired
    private MainService mainService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        mainService.addTestData();
    }
}
