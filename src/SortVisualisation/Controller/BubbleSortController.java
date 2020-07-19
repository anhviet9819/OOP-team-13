package SortVisualisation.Controller;

import SortVisualisation.Model.ChartDataManager;
import SortVisualisation.Model.Sorting.BubbleSort;
import javafx.event.ActionEvent;
public class BubbleSortController extends AbstractSortController {

    public BubbleSortController() {}

    @SuppressWarnings("unused")
    public void initialize() {
        // Use the ChartDataManager to manage our BarChart data
        this.chartData = new ChartDataManager(barChart);
    }

    public void visualiseInput(ActionEvent actionEvent) {
        super.visualiseInput(actionEvent);

        // initialize our sorting algorithm
        sorter = null;
        sorter = new BubbleSort(unsortedIntegers);

        // set default pointers within barChart
        chartData.selectNode(0);
        chartData.selectNode(1); // the pointer always starts at 0 (and compares with 1)
    }

}
