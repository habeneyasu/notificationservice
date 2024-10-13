package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.integration.NotificationServiceIntegration;
import com.ecommerce.notificationservice.model.Notification;
import com.ecommerce.notificationservice.modeldto.NotificationDTO;
import com.ecommerce.notificationservice.modeldto.Login;
import com.ecommerce.notificationservice.service.NotificationService;
import com.ecommerce.notificationservice.service.NotificationServiceImp;
import com.ecommerce.notificationservice.service.SmsService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;
import org.springframework.http.HttpStatus;

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

      private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImp.class);


      @GetMapping("/testNotification")
    public ResponseEntity<String> helloWorld() {

        logger.info("Hello World test message.");

        String message = "Welcome to the notification service.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    /**
     * Get all notifications
     */
    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        logger.info("Fetching all notifications");
        try {
            List<Notification> notifications = notificationService.getAllNotifications();
            if (notifications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
            }
            return new ResponseEntity<>(notifications, HttpStatus.OK); 
        } catch (Exception e) {
            logger.error("Error fetching notifications", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    /**
     * Get notification by Id
     */
    @GetMapping("/getNotificationById")
    public ResponseEntity<Notification> getNotificationById(@RequestParam("id") Long id) {
        logger.info("Fetching notification with ID: {}", id);
        try {
            Notification notification = notificationService.getNotificationById(id);
            if (notification == null) {
                logger.warn("Notification with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
            return new ResponseEntity<>(notification, HttpStatus.OK); 
        } catch (Exception e) {
            logger.error("Error fetching notification with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    /**
     * Create notification
     */
    @PostMapping("/createNotification")
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody NotificationDTO notificationdto){
       

      logger.info("Creating notification for user ID: {}", notificationdto.getUserId());
      try {
          // Optionally send an SMS notification
         // smsService.sendSms(notificationdto.getReceiverPhoneNumber(), notificationdto.getMessage());

          Notification createdNotification = notificationService.createNotification(notificationdto);
          return new ResponseEntity<>(createdNotification, HttpStatus.CREATED); 
      } catch (Exception e) {
          logger.error("Error creating notification for user ID: {}", notificationdto.getUserId(), e);
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    /**
     * Update notification
     */
    @PutMapping("/updateNotification")
    public ResponseEntity<Notification> updateNotification(@RequestParam("id") Long id, @RequestBody Notification notification) {
     
        logger.info("Updating notification with ID: {}", id);
        try {
            Notification updatedNotification = notificationService.updateNotification(id, notification);
            if (updatedNotification != null) {
                return new ResponseEntity<>(updatedNotification, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error updating notification with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    /**
     * Delete notification
     */
    // @DeleteMapping("/deleteNotification")
    // public void deletePayment(@RequestParam("id") Long id){
    //     notificationService.deleteNotification(id);
    // }

    @DeleteMapping("/deleteNotification")
    public ResponseEntity<Void> deleteNotification(@RequestParam("id") Long id) {
        logger.info("Request to delete notification with ID: {}", id);
        try {
            boolean isDeleted = notificationService.deleteNotification(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        } catch (Exception e) {
            logger.error("Error deleting notification with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@Valid @RequestBody Login login) {

        log.info("Attempting to log in user with username: {}", login.getUsername());

        return orderServiceIntegration.login(login)
            .map(token -> ResponseEntity.ok(token)) 
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
                .body("Invalid credentials"))
            .onErrorResume(e -> {
                log.error("Error occurred during login: {}", e.getMessage());
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
                    .body("An error occurred while processing the login request"));
            });
    }

}
