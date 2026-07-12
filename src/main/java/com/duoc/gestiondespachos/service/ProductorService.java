package com.duoc.gestiondespachos.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.duoc.gestiondespachos.dto.GuiaDespachoResponseDTO;
import com.duoc.gestiondespachos.config.RabbitConfig;

@Service
public class ProductorService {

    private final RabbitTemplate rabbitTemplate;

    public ProductorService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(GuiaDespachoResponseDTO mensaje) {

        System.out.println("Enviando a RabbitMQ: " + mensaje);

        rabbitTemplate.convertAndSend(
                RabbitConfig.QUEUE,
                mensaje);
    }
}
