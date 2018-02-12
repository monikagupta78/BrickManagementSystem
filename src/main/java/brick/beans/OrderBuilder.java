package brick.beans;

import brick.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static brick.utils.OrderStatus.CANCELLED;
import static brick.utils.OrderStatus.DISPATCHED;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class OrderBuilder {

    @Autowired
    public GetBrickFactory brickFactory;

    @Autowired
    public OrderSequenceGenerator orderSequenceGenerator;

    public Order buildOrder(String brickType, Integer quantity) {
        return new Order(orderSequenceGenerator.getNextOrderReferenceNumber(), brickFactory.getBrick(brickType), quantity);
    }

    public Order copyOrder(Order order, Integer quantity) {
        Order newOrder = new Order();
        copyProperties(order,newOrder);

        Long newOrderReferenceId = orderSequenceGenerator.getNextOrderReferenceNumber();
        order.setStatus(CANCELLED);
        order.setNewOrderReferenceId(newOrderReferenceId);

        newOrder.setOrderReferenceId(newOrderReferenceId);
        newOrder.setQuantity(quantity);

        return newOrder;
    }

    public void dispatchOrder(Order order) {
        order.setStatus(DISPATCHED);
    }
}