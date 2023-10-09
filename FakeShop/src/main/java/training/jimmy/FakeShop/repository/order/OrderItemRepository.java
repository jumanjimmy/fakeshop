package training.jimmy.FakeShop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import training.jimmy.FakeShop.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
