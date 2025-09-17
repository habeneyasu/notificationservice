package com.ecommerce.paymentservice.service;

import com.ecommerce.paymentservice.model.Notification;
import com.ecommerce.paymentservice.modeldto.NotificationDTO;

import java.util.List;


public interface NotificationService {

    /**
     * Get all notifications
     */
     public List<Notification> getAllNotifications();

    /**
     * Get notification by Id
     */
    public Notification getNotificationById(Long id);

    /**
     * Create notification
     */
    public Notification createNotification(NotificationDTO Notificationdto);

    /**
     * Update notification
     */
    public Notification updateNotification(Long id, Notification notification);

    /**
     * Delete notification
     */
    public void deleteNotification(Long id);
}
