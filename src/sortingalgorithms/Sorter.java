package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.List;

public interface Sorter {

    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds);
}
