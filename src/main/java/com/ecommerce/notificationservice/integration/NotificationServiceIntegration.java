package com.ecommerce.paymentservice.integration;

import com.ecommerce.paymentservice.modeldto.NotificationDTO;
import com.ecommerce.paymentservice.modeldto.Login;
import reactor.core.publisher.Mono;

public interface NotificationServiceIntegration {

    /**
     * Consume the login API
     */
    public Mono<String> login(Login login);

    /**
     * Check if user is found
     */
    public Mono<NotificationDTO> isUserFound(Long userId, String authToken);
}
