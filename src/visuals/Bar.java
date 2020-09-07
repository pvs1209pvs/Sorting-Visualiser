package visuals;

import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle implements Comparable<Bar> {

    public boolean isVisited = false;
    public int id = -1;

    public Bar(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public Bar(double x, double y, double w, double h, int id){
        this(x,y,w,h);
        this.id = id;
    }

    @Override
    public int compareTo(Bar other) {
        Double current = this.getHeight();
        return current.compareTo(other.getHeight());
    }

    @Override
    public String toString() {
        return this.id+"";
        //return String.valueOf(super.getHeight());
    }

    public String string(){
        return getHeight()+"";
    }
}
