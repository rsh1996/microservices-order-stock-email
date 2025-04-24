package net.javaguides.stock_service.kafka;

import net.javaguides.base_domains.dto.OrderEvent;
import net.javaguides.stock_service.model.OrderEventDocument;
import net.javaguides.stock_service.repository.OrderEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private final OrderEventRepository repository;

    public OrderConsumer(OrderEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}")
    private void consume(OrderEvent event) {
        try {
            LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
            //save the order event data into db
            OrderEventDocument doc = new OrderEventDocument();
            doc.setOrder(event.getOrder());
            doc.setStatus(event.getStatus());
            doc.setMessage(event.getMessage());

            repository.save(doc);
            LOGGER.info("📦 Saved event to MongoDB");
        } catch (Exception e) {
            LOGGER.error("Error processing order event", e);
            throw new RuntimeException("Failed to process order event", e); // Allows retry to happen
        }
    }
}
