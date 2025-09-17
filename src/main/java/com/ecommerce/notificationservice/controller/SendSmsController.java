package com.ecommerce.paymentservice.controller;

import com.ecommerce.paymentservice.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class SendSmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/sendSms")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        smsService.sendSms(to, message);
        return "SMS sent successfully!";
    }
}
