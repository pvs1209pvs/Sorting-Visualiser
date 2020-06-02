package visuals;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import sortingalgorithms.Sorter;
import sortingalgorithms.SorterFactory;

import java.util.ArrayList;
import java.util.List;

class AnimationSequence {

    SequentialTransition getSequenceTransition(String algorithm, Bar[] bars,  int gap, double sec) {

        List<Animation> trans = new ArrayList<>();

        Sorter s = SorterFactory.getSorter(algorithm);
        s.sort(bars, trans, gap, sec);

        return new SequentialTransition(trans.toArray(new Animation[0]));

    }
}
