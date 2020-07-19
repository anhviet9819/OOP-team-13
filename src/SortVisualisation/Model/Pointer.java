package SortVisualisation.Model;

import java.util.LinkedList;
public class Pointer {
    private LinkedList<Integer> indices;

    public Pointer() {
        indices = new LinkedList<>();
    }

    public LinkedList<Integer> getIndices() {
        return indices;
    }

    public void setCurrent(int index) {
        indices.clear();
        indices.add(index);
    }

    public void setCurrent(int[] newIndices) {
        indices.clear();
        for (int i : newIndices) {
            indices.add(i);
        }
    }

    public void updateIndex(int i, int value) {
        indices.set(i, value);
    }
}
