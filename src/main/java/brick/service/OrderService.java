package brick.service;

import brick.model.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(String brickType, Integer quantity);
    Order getOrder(Long orderReferenceId);
    List<Order> getAllOrders();
    Order updateOrder(Long orderReferenceId,Integer quantity);
    void dispatchOrder(Long orderReferenceId);

}
