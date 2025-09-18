package com.ecommerce.notificationservice.service;

public interface SmsService {
    public void sendSms(String toPhoneNumber, String body);
}
