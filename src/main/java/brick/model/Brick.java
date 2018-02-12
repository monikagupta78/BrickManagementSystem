package brick.model;

import java.math.BigDecimal;

public interface Brick {
    String getType();
    BigDecimal getCost();
    BrickSize getSize();
}