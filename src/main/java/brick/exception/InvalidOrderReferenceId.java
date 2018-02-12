package brick.exception;

public class InvalidOrderReferenceId extends RuntimeException {
    private long orderReferenceId;
    public InvalidOrderReferenceId(long orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }
    public long getOrderReferenceId() {
        return orderReferenceId;
    }
}