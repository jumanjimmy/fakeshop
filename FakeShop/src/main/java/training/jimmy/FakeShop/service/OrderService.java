package training.jimmy.FakeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.jimmy.FakeShop.dto.OrderDto;
import training.jimmy.FakeShop.mapper.OrderMapper;
import training.jimmy.FakeShop.model.Cart;
import training.jimmy.FakeShop.model.order.Order;
import training.jimmy.FakeShop.repository.order.OrderItemRepository;
import training.jimmy.FakeShop.repository.order.OrderRepository;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
    }
}
