package visuals;

import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle implements Comparable<Bar> {

    public Bar(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    @Override
    public int compareTo(Bar other) {
        Double current = this.getHeight();
        return current.compareTo(other.getHeight());
    }

    @Override
    public String toString() {
        return String.valueOf(super.getHeight());
    }
}
