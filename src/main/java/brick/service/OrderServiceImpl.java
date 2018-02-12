package brick.service;

import brick.beans.OrderBuilder;
import brick.exception.InvalidOrderReferenceId;
import brick.exception.OrderAlreadyDispatched;
import brick.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static brick.utils.OrderStatus.DISPATCHED;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderBuilder orderBuilder;

    private final Map<Long,Order> mapOfOrders = new HashMap<>();

    @Override
    public Order addOrder(String brickType, Integer quantity) {
        Order order = orderBuilder.buildOrder(brickType,quantity);
        mapOfOrders.put(order.getOrderReferenceId(),order);
        return order;
    }

    @Override
    public Order getOrder(Long orderReferenceId) {
        Order order = mapOfOrders.get(orderReferenceId);
        if(order==null) {
            throw new InvalidOrderReferenceId(orderReferenceId);
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(mapOfOrders.values());
    }

    @Override
    public Order updateOrder(Long orderReferenceId,Integer quantity) {
        Order order = mapOfOrders.get(orderReferenceId);
        if(order==null) {
            throw new InvalidOrderReferenceId(orderReferenceId);
        }

        if(order.getStatus().equals(DISPATCHED)) {
            throw new OrderAlreadyDispatched(orderReferenceId);
        }

        Order orderUpdated = orderBuilder.copyOrder(order,quantity);
        mapOfOrders.put(orderUpdated.getOrderReferenceId(),orderUpdated);
        return orderUpdated;
    }

    @Override
    public void dispatchOrder(Long orderReferenceId) {
        Order order = mapOfOrders.get(orderReferenceId);
        if(order==null) {
            throw new InvalidOrderReferenceId(orderReferenceId);
        }

        if(order.getStatus().equals(DISPATCHED)) {
            throw new OrderAlreadyDispatched(orderReferenceId);
        }
        orderBuilder.dispatchOrder(order);
    }
}
