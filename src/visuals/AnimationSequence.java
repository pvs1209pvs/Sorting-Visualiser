package visuals;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import sortingalgorithms.SorterFactory;

import java.util.ArrayList;
import java.util.List;

class AnimationSequence {

    SequentialTransition getSequenceTransition(SorterFactory.ALGORITHMS algorithm, Bar[] bars, int gap, double sec) {

        List<Animation> trans = new ArrayList<>();

        SorterFactory.getSorter(algorithm).sort(bars, trans, gap, sec);

        return new SequentialTransition(trans.toArray(new Animation[0]));

    }
}
