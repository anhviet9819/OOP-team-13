package SortVisualisation.Model.Sorting;

import SortVisualisation.Model.Pointer;
public abstract class AbstractSort {
    protected int[] sortArray;
    protected AbstractSort(int[] unsortedArray) {
        this.sortArray = unsortedArray;
    }

    abstract public Pointer getPointer();

    abstract public int[] sortOneStep();

    abstract public boolean isFinished();
}
