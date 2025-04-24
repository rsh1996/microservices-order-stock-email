package net.javaguides.stock_service.model;

import lombok.Data;
import net.javaguides.base_domains.dto.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "order_events")
public class OrderEventDocument {
    @Id
    private String id;
    private String message;
    private String status;
    private Order order;
}
