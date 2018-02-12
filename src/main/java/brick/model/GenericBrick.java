package brick.model;

public abstract class GenericBrick implements Brick {
    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Type").append(":\t").append(getType()).append("\n");
        strBuild.append("Cost").append(":\t").append(getCost()).append("\n");
        strBuild.append("Size").append(":\t").append(getSize()).append("\n");
        return strBuild.toString();
    }
}