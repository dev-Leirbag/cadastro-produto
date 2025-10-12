package produto.api.application.infra.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String FILA_RESERVA_PRODUTO = "fila_reserva_produto";
    public static final String EXCHANGE_RESERVA_PRODUTO = "exchange_reserva_produto";
    public static final String ROUTING_KEY_RESERVA = "chave_reserva_produto";

    //Cria a fila
    @Bean
    public Queue filaReservaProduto(){
        return new Queue(FILA_RESERVA_PRODUTO, true);
    }

    //Cria a exchange(ponto central de troca de mensagens)
    @Bean
    public TopicExchange exchangeReservaProduto(){
        return new TopicExchange(EXCHANGE_RESERVA_PRODUTO);
    }

    //Liga a fila com a exchenge usando uma chave
    @Bean
    public Binding ligacaoReservaProduto(Queue filaReservaProduto, TopicExchange exchangeReservaProduto){
        return BindingBuilder.bind(filaReservaProduto)
                .to(exchangeReservaProduto)
                .with(ROUTING_KEY_RESERVA);
    }

    // Conversor JSON (resolve o erro de desserialização)
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate configurado para usar o conversor acima
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
