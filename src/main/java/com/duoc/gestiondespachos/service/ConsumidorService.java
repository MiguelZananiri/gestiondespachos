package com.duoc.gestiondespachos.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.duoc.gestiondespachos.config.RabbitConfig;
import com.duoc.gestiondespachos.dto.GuiaDespachoResponseDTO;
import com.duoc.gestiondespachos.entity.EstadoGuia;
import com.duoc.gestiondespachos.entity.GuiaDespacho;
import com.duoc.gestiondespachos.repository.GuiaDespachoRepository;

@Service
public class ConsumidorService {

    private final GuiaDespachoRepository guiaDespachoRepository;

    public ConsumidorService(GuiaDespachoRepository guiaDespachoRepository) {
        this.guiaDespachoRepository = guiaDespachoRepository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void recibirMensaje(GuiaDespachoResponseDTO mensaje) {

        System.out.println("Recibiendo mensaje");

        GuiaDespacho guia = new GuiaDespacho();

        guia.setNumeroPedido(mensaje.getNumeroPedido().trim());
        guia.setTransportista(mensaje.getTransportista().trim());
        guia.setDestinatario(mensaje.getDestinatario().trim());
        guia.setDireccionDestino(mensaje.getDireccionDestino().trim());
        guia.setCiudadDestino(mensaje.getCiudadDestino().trim());
        guia.setFechaDespacho(mensaje.getFechaDespacho());
        guia.setDescripcionCarga(mensaje.getDescripcionCarga().trim());
        guia.setPesoKg(mensaje.getPesoKg());
        guia.setEstado(EstadoGuia.GENERADA);

        guiaDespachoRepository.save(guia);

        System.out.println("Guardado en Oracle");
    }
}
