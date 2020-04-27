package visuals;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import sortingalgorithms.BubbleSort;

import java.util.ArrayList;
import java.util.List;

public class AnimationSequence {

    List<Animation> trans;
    BubbleSort<Bar> sorter;

    public AnimationSequence(Bar[] bars, int gap, double seconds) {

        trans = new ArrayList<>();
        sorter = new BubbleSort<>();

        sorter.sort(bars, trans, gap, seconds);
    }

    public List<Animation> getTrans() {
        return this.trans;
    }

    public SequentialTransition getSequenceTransition(){
        return new SequentialTransition(trans.toArray(new Animation[0]));
    }
}
