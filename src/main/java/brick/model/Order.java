package brick.model;

import brick.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Order {

    public Long orderReferenceId;
    public OrderStatus status;
    public Brick brick;
    public Integer quantity;
    public LocalDateTime dateTime;
    public Long newOrderReferenceId;

    public Order() {
    }

    public Order(Long orderReferenceId, Brick brick, Integer quantity) {
        this.orderReferenceId = orderReferenceId;
        this.brick = brick;
        this.quantity = quantity;
        this.status = OrderStatus.IN_PROGRESS;
        this.dateTime = now();
        this.newOrderReferenceId = null;
    }

    public Long getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(Long orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public Brick getBrick() {
        return brick;
    }

/*
    public void setBrick(Brick brick) {
        this.brick = brick;
    }
*/

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonIgnore
    public LocalDateTime getDateTime() {
        return dateTime;
    }

/*
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
*/

    public Long getNewOrderReferenceId() {
        return newOrderReferenceId;
    }

    public void setNewOrderReferenceId(Long newOrderReferenceId) {
        this.newOrderReferenceId = newOrderReferenceId;
    }

    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("orderReferenceId").append(":\t").append(orderReferenceId).append("\n");
        strBuild.append("status").append(":\t").append(status).append("\n");
        strBuild.append("brick").append(":\t").append(brick).append("\n");
        strBuild.append("quantity").append(":\t").append(quantity).append("\n");
        strBuild.append("dateTime").append(":\t").append(dateTime).append("\n");
        strBuild.append("newOrderReferenceId").append(":\t").append(newOrderReferenceId).append("\n");
        return strBuild.toString();
    }
}