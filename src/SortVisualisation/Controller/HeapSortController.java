package SortVisualisation.Controller;

import SortVisualisation.Model.ChartDataManager;
import SortVisualisation.Model.Sorting.HeapSort;
import javafx.event.ActionEvent;
public class HeapSortController extends AbstractSortController {

    public HeapSortController() {
    }
    @SuppressWarnings("unused")
    public void initialize() {
        // Use the ChartDataManager to manage our BarChart data
        this.chartData = new ChartDataManager(barChart);
    }

    public void visualiseInput(ActionEvent actionEvent) {
        super.visualiseInput(actionEvent);

        // initialize our sorting algorithm
        sorter = null;
        sorter = new HeapSort(unsortedIntegers);
        // set default pointers within barChart
    }
}
