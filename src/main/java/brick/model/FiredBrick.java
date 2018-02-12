package brick.model;

import java.math.BigDecimal;

import static brick.utils.BrickType.FIRED;
import static java.math.BigDecimal.valueOf;

public class FiredBrick extends GenericBrick {
    public String getType() {
        return FIRED.toString();
    }

    public BigDecimal getCost() {
        return valueOf(20);
    }

    public BrickSize getSize() {
        return new BrickSize(valueOf(4.1), valueOf(3.4), valueOf(2.5),valueOf(7.6));
    }
}