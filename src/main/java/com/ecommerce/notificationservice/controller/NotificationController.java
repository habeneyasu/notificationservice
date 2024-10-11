package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.integration.NotificationServiceIntegration;
import com.ecommerce.notificationservice.model.Notification;
import com.ecommerce.notificationservice.modeldto.NotificationDTO;
import com.ecommerce.notificationservice.modeldto.Login;
import com.ecommerce.notificationservice.service.NotificationService;
import com.ecommerce.notificationservice.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/notifications/")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);


    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationServiceIntegration orderServiceIntegration;

    @Autowired
    private SmsService smsService;

    @GetMapping("/testNotification")
    public String HelloWorld(){

        System.out.println("Hello World test message.");
        return "Welcome to notification service.";
    }


    /**
     * Get all notifications
     */
    @GetMapping("/getAllNotifications")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    /**
     * Get notification by Id
     */
    @GetMapping("/getNotificationById")
    public Notification getNotificationById(@RequestParam("id") Long id){
        return notificationService.getNotificationById(id);
    }

    /**
     * Create notification
     */
    @PostMapping("/createNotification")
    public Notification createNotification(@RequestBody NotificationDTO notificationdto){
       // smsService.sendSms(notificationdto.getReceiverPhoneNumber(),notificationdto.getMessage());
        return notificationService.createNotification(notificationdto);
    }



    /**
     * Update notification
     */
    @PutMapping("/updateNotification")
    public Notification updateNotification(@RequestParam("id") Long id,@RequestBody Notification notification){
        return notificationService.updateNotification(id,notification);
    }

    /**
     * Delete notification
     */
    @DeleteMapping("/deleteNotification")
    public void deletePayment(@RequestParam("id") Long id){
        notificationService.deleteNotification(id);
    }

    @PostMapping("/login")
    public Mono<String> loginUser(@RequestBody Login login) {

        log.info("This is an INFO log message");
      //  log.debug("This is a DEBUG log message");
      //  log.error("This is an ERROR log message");

        return orderServiceIntegration.login(login);
    }

}
