package SortVisualisation.Model;

import java.util.ArrayList;
import java.util.Collections;
public class RandomGen {
    public static int[] generateRandomInts(int max) {
        int[] intSeries = new int[max];
        for(int i=0; i<max; i++) {
            intSeries[i] = (int) (Math.random() * max + 1); // +1 for minimum is 1
        }
        return intSeries;
    }

}
