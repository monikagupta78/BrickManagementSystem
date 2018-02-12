package brick.beans;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class OrderSequenceGenerator {
    AtomicLong orderReferenceGenerator = new AtomicLong();

    public Long getNextOrderReferenceNumber() {
        return orderReferenceGenerator.incrementAndGet();
    }

}
