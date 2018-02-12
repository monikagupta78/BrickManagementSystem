package brick.exception;

public class OrderAlreadyDispatched extends RuntimeException {
    private long orderReferenceId;
    public OrderAlreadyDispatched(long orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }
    public long getOrderReferenceId() {
        return orderReferenceId;
    }
}