package com.duoc.gestiondespachos.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.gestiondespachos.dto.GuiaDespachoResponseDTO;
import com.duoc.gestiondespachos.service.ProductorService;

@RestController
@RequestMapping("/rabbit/guias")
public class ProductorController {

    private final ProductorService producer;

    public ProductorController(ProductorService producer) {
        this.producer = producer;
    }

    @PostMapping
    public String enviarMensaje(@RequestBody GuiaDespachoResponseDTO mensaje) {
        producer.enviarMensaje(mensaje);
        return "Mensaje enviado correctamente a RabbitMQ";
    }
}
