package brick.model;

import java.math.BigDecimal;

import static brick.utils.BrickType.UN_FIRED;
import static java.math.BigDecimal.valueOf;

public class UnFiredBrick extends GenericBrick {

    public String getType() {
        return UN_FIRED.toString();
    }

    public BigDecimal getCost() {
        return valueOf(10.15);
    }

    public BrickSize getSize() {
        return new BrickSize(valueOf(4.2), valueOf(3.5), valueOf(2.25),valueOf(7.5));
    }
}