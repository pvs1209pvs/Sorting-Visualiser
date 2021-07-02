package sortingalgorithms;

import javafx.animation.Animation;
import org.jetbrains.annotations.NotNull;
import visuals.Bar;

import java.util.List;

public interface Sorter {
    void sort(@NotNull Bar[] bars, List<Animation> trans, int gap, double seconds);
}
