package com.ecommerce.paymentservice.service;

public interface SmsService {
    public void sendSms(String toPhoneNumber, String body);
}
