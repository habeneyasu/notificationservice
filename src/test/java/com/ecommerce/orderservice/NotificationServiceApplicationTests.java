package com.ecommerce.orderservice;

import com.ecommerce.paymentservice.NotificationServiceApplication;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotificationServiceApplication.class)
@AutoConfigureMockMvc
class NotificationServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void testNotification() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/notifications/testNotification"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome to notification service."));
	}

	@Test
	public void createNotification() throws Exception {
		String newNotification = "{\"userId\":1,\"orderCode\":\"AB30ID42\",\"amount\":90000,\"message\":1,\"Notification\":1,\"subject\":1,\"Order Notification\":1,\"notificationType\":\"Notification Type\",\"receiverPhoneNumber\":\"251942707424\",\"transactionRef\":\"79432\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/notifications/createNotification")
						.contentType(MediaType.APPLICATION_JSON)
						.content(newNotification)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void contextLoads() {
	}

}
