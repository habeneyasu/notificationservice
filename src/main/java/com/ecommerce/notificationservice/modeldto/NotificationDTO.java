package com.ecommerce.notificationservice.modeldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long userId;
    private String orderCode;
    private String transactionRef;
    private BigDecimal amount;
    private String message;
    private String subject;
    private String receiverPhoneNumber;
    private String notificationType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;

}