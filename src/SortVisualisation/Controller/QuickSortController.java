package SortVisualisation.Controller;

import SortVisualisation.Model.ChartDataManager;
import SortVisualisation.Model.Sorting.QuickSort;
import javafx.event.ActionEvent;
public class QuickSortController extends AbstractSortController {

    public QuickSortController() {
    }
    @SuppressWarnings("unused")
    public void initialize() {
        // Use the ChartDataManager to manage our BarChart data
        this.chartData = new ChartDataManager(barChart);
    }

    @Override
    public void visualiseInput(ActionEvent actionEvent) {
        super.visualiseInput(actionEvent);

        // initialize our sorting algorithm
        sorter = null;
        sorter = new QuickSort(unsortedIntegers);
    }

}
