package brick.controller;

import brick.exception.Error;
import brick.exception.InvalidOrderReferenceId;
import brick.exception.OrderAlreadyDispatched;
import brick.model.Order;
import brick.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/{brickType}/{quantity}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order order(@PathVariable String brickType, @PathVariable Integer quantity) {
        return orderService.addOrder(brickType,quantity);
    }

    @RequestMapping(value = "/{orderReferenceId}", method = RequestMethod.GET)
    public Order order(@PathVariable Long orderReferenceId) {
        return orderService.getOrder(orderReferenceId);
    }

    @RequestMapping(value = "/{orderReferenceId}/{quantity}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Order order(@PathVariable Long orderReferenceId, @PathVariable Integer quantity) {
        return orderService.updateOrder(orderReferenceId, quantity);
    }

    @RequestMapping(value = "/{orderReferenceId}/dispatch", method = RequestMethod.PUT)
    public void dispatchOrder(@PathVariable Long orderReferenceId) {
        orderService.dispatchOrder(orderReferenceId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> allOrders() {
        return orderService.getAllOrders();
    }

    @ExceptionHandler(InvalidOrderReferenceId.class)
    public ResponseEntity<Error> invalidOrderReferenceId(
            InvalidOrderReferenceId e) {
        Error error = new Error(100, "Invalid Order Reference Id [" + e.getOrderReferenceId() + "]");
        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderAlreadyDispatched.class)
    public ResponseEntity<Error> orderAlreadyDispatched(
            OrderAlreadyDispatched e) {
        Error error = new Error(101, "Order Already Dispatched For Id [" + e.getOrderReferenceId() + "]");
        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
}
