package training.jimmy.FakeShop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.jimmy.FakeShop.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
