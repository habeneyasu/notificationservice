package com.ecommerce.notificationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.notificationservice.model.Notification;
import com.ecommerce.notificationservice.modeldto.NotificationDTO;
import com.ecommerce.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {

@Autowired
private NotificationRepository notificationRepository;

@Value("${spring.service.notification.typeOrder}")
String order;

@Value("${spring.notification.service.typePayment}")
String payment;

    /**
     * Get all Notification
     */
    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    /**
     * Get notification by Id
     */
    public Notification getNotificationById(Long id){
        return notificationRepository.findById(id).orElse(null);
    }

    /**
     * Create Notification
     */
    public Notification createNotification(NotificationDTO notificationdto){
        Notification newNotification=new Notification();

        newNotification.setUserId(notificationdto.getUserId());
        newNotification.setMessage(notificationdto.getMessage());
        newNotification.setAmount(notificationdto.getAmount());
        newNotification.setReceiverPhoneNumber(notificationdto.getReceiverPhoneNumber());
      //   if(notificationdto.getNotificationType().equals(order)){
             newNotification.setOrderCode(notificationdto.getOrderCode());
     //    }
      //  else{
            newNotification.setTransactionRef(notificationdto.getTransactionRef());
    //    }

        newNotification.setSubject(notificationdto.getSubject());

        return notificationRepository.save(newNotification);
    }

    /**
     * Update notification
     */
    public Notification updateNotification(Long id, Notification notification){
        Notification findNotification=getNotificationById(id);
        if(findNotification!=null){
            findNotification.setOrderCode(notification.getOrderCode());
            findNotification.setUserId(notification.getUserId());
            findNotification.setTransactionRef(notification.getTransactionRef());
            findNotification.setMessage(notification.getMessage());
            findNotification.setAmount(notification.getAmount());
            return notificationRepository.save(findNotification);
        }
        return null;
    }

    /**
     * Delete notification
     */
    public void deleteNotification(@RequestParam("id") Long id) {
        Notification findNotification = getNotificationById(id);
        if (findNotification != null) {
            notificationRepository.delete(findNotification);
        }
    }
}
