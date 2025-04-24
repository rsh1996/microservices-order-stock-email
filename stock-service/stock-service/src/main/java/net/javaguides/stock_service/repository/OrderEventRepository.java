package net.javaguides.stock_service.repository;

import net.javaguides.stock_service.model.OrderEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepository extends MongoRepository<OrderEventDocument, String> {
}