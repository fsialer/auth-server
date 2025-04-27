package com.fernando.auth_server.services.impl;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.auth_server.dto.UserSendDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServicesBusSenderService {
    @Value("${spring.cloud.azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${spring.cloud.azure.servicebus.queue-name}")
    private String queueName;

    private final ObjectMapper objectMapper;

    public ServicesBusSenderService(
            ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
    }

    public void sendMessage(UserSendDTO userSendDTO) {
        try{
            ServiceBusSenderClient sender = new ServiceBusClientBuilder()
                    .connectionString(connectionString)
                    .sender()
                    .queueName(queueName)
                    .buildClient();
            String messageContent = this.objectMapper.writeValueAsString(userSendDTO);
            sender.sendMessage(new ServiceBusMessage(messageContent));
            sender.close();
            log.info("Message sent to Service Bus queue: {}", messageContent);
        } catch (Exception e) {
            log.error("An error occurred: {}",e.getMessage());
        }

    }

}
