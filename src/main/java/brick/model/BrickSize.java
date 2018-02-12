package brick.model;

import java.math.BigDecimal;

public class BrickSize {

    BigDecimal weight;
    BigDecimal depth;
    BigDecimal height;
    BigDecimal length;

    public BrickSize(BigDecimal weight, BigDecimal depth, BigDecimal height, BigDecimal length) {
        this.weight = weight; //add units
        this.depth = depth; //add units
        this.height = height; //add units
        this.length = length; //add units
    }

    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Weight").append(":\t").append(weight).append("lbs").append("\n");
        strBuild.append("Depth").append(":\t").append(depth).append("\"").append("\n");
        strBuild.append("Height").append(":\t").append(height).append("\"").append("\n");
        strBuild.append("Length").append(":\t").append(length).append("\"").append("\n");
        return strBuild.toString();
    }

}