package brick.model;

import java.math.BigDecimal;

import static brick.utils.BrickType.CHEMICALLY_SET;
import static java.math.BigDecimal.valueOf;

public class ChemicallySetBrick extends GenericBrick {
    public String getType() {
        return CHEMICALLY_SET.toString();
    }

    public BigDecimal getCost() {
        return valueOf(25);
    }

    public BrickSize getSize() {
        return new BrickSize(valueOf(4.0), valueOf(3.1), valueOf(2.8),valueOf(7.2));
    }
}