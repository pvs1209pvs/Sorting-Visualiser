package visuals;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle implements Comparable<Bar> {

    public boolean isVisited = false;

    public Bar(double x, double y, double w, double h) {

        super(x, y, w, h);

        if(x<0 || y <0|| w<0 || h < 0){
            throw new IllegalArgumentException("None of the Bar parameters can be negative.");
        }

        setFill(Paint.valueOf("#03b6fc"));
    }

    @Override
    public int compareTo(Bar other) {
        Double current = this.getHeight();
        return current.compareTo(other.getHeight());
    }

    @Override
    public String toString() {
        return String.valueOf((int) super.getHeight());
    }
}
