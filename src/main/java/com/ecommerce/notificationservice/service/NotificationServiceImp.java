package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.model.Notification;
import com.ecommerce.notificationservice.modeldto.NotificationDTO;
import com.ecommerce.notificationservice.exception.ResourceNotFoundException;
import com.ecommerce.notificationservice.respository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.util.StringUtils;
import com.ecommerce.notificationservice.exception.DatabaseException;
import com.ecommerce.notificationservice.exception.UpdateFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // @Value("${spring.service.notification.typeOrder}")
    // String order;

    // @Value("${spring.notification.service.typePayment}")
    // String payment;

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImp.class);

    /**
     * Get all Notification
     */
    public List<Notification> getAllNotifications() {

        try {
            logger.info("Fetching all notifications");

            List<Notification> notifications = notificationRepository.findAll();

            if (Objects.isNull(notifications) || notifications.isEmpty()) {
                logger.warn("No notifications found");
                throw new ResourceNotFoundException("No notifications found");
            }

            return notifications;

        } catch (Exception e) {
            logger.error("Error occurred while fetching notifications: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch notifications", e);
        }
    }

    /**
     * Get notification by Id
     */
    public Notification getNotificationById(Long id) {

        try {
            logger.info("Fetching notificaiton by notification Id: {} " + id);
            Notification notification = notificationRepository.findById(id).orElse(null);

            if (Objects.isNull(notification)) {
                logger.warn("No notifications found");
                throw new ResourceNotFoundException("No notifications found");
            }

            return notification;

        } catch (Exception e) {
            logger.error("Error occurred while fetching notifications: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch notifications", e);
        }

    }

    /**
     * Create Notification
     */
    public Notification createNotification(NotificationDTO notificationdto) {

        try {
            logger.info("Creating a new notification for user ID: {}", notificationdto.getUserId());

            // Validate essentail fields.
            validateNotificationData(notificationdto);

            Notification newNotification = new Notification();

            newNotification.setUserId(notificationdto.getUserId());
            newNotification.setMessage(notificationdto.getMessage());
            newNotification.setAmount(notificationdto.getAmount());
            newNotification.setReceiverPhoneNumber(notificationdto.getReceiverPhoneNumber());
            newNotification.setOrderCode(notificationdto.getOrderCode());
            newNotification.setTransactionRef(notificationdto.getTransactionRef());
            newNotification.setSubject(notificationdto.getSubject());

            return notificationRepository.save(newNotification);

        } catch (Exception e) {
            logger.error("Error occurred while creating notification: {}", e.getMessage());
            throw new RuntimeException("Failed to create notification", e);
        }

    }

    // Optional: Validate important fields before processing
    private void validateNotificationData(NotificationDTO notificationDTO) throws Exception {
        if (notificationDTO.getUserId() == null || notificationDTO.getUserId() <= 0) {
            throw new Exception("User ID is required and must be a positive number.");
        }
        if (!StringUtils.hasText(notificationDTO.getMessage())) {
            throw new Exception("Message cannot be empty.");
        }
        if (notificationDTO.getAmount() == null || notificationDTO.getAmount().signum() <= 0) {
            throw new Exception("Amount must be a positive value.");
        }
        if (!StringUtils.hasText(notificationDTO.getReceiverPhoneNumber())) {
            throw new Exception("Receiver phone number is required.");
        }
    }

    /**
     * Update notification
     */
    public Notification updateNotification(Long id, Notification notification) {

        try {
            Notification existingNotification = notificationRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));

            existingNotification.setUserId(notification.getUserId());
            existingNotification.setOrderCode(notification.getOrderCode());
            existingNotification.setTransactionRef(notification.getTransactionRef());
            existingNotification.setMessage(notification.getMessage());
            existingNotification.setAmount(notification.getAmount());
            existingNotification.setReceiverPhoneNumber(notification.getReceiverPhoneNumber());
            existingNotification.setSubject(notification.getSubject());

            return notificationRepository.save(existingNotification);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred while saving the notification", e);
        } catch (Exception e) {
            throw new UpdateFailedException("Failed to update the notification", e);
        }
    }

    /**
     * Delete notification
     */
    public boolean deleteNotification(@RequestParam("id") Long id) {
        
        Notification notification = getNotificationById(id);
    if (notification != null) {
        notificationRepository.delete(notification);
        return true; // Successfully deleted
    }
    return false; // Notification not found
    }
}
