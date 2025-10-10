package produto.api.application.infra.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutorEventoReserva {

    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(ProdutorEventoReserva.class);

    public ProdutorEventoReserva(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEventoReserva(EventoReservaProduto evento){
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_RESERVA_PRODUTO,
                RabbitConfig.ROUTING_KEY_RESERVA,
                evento
        );
        logger.info("\uD83D\uDCE4 Evento de reserva enviado par RabbitMQ: " + evento);
    }
}
