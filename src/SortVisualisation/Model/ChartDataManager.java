package SortVisualisation.Model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.lang.reflect.Array;
import java.util.LinkedList;
public class ChartDataManager {
    private BarChart barChart;
    private XYChart.Series<String, Integer> chartDataSeries;
    private LinkedList<Node> selectedNodes;
    public ChartDataManager(BarChart initializedBarChart) throws IllegalArgumentException {
        if (initializedBarChart == null) {
            throw new IllegalArgumentException("instance of BarChart must be initialized.");
        }

        this.barChart = initializedBarChart;
        this.selectedNodes = new LinkedList<>();
    }
    public void updateDataSet(int[] inputArray) {
        if (barChart.getData().size() > 0)
            this.barChart.getData().remove(0);

        this.chartDataSeries = new XYChart.Series<>();

        for (int i = 0; i < inputArray.length; i++) {
            XYChart.Data<String, Integer> dataItem = new XYChart.Data<>(i + "", inputArray[i]);
            chartDataSeries.getData().add(dataItem);
        }
    }
    public void load() {
        //no inspection unchecked
        this.barChart.getData().add(this.chartDataSeries);
    }

    public void styleChartData(String styleClass) {
        for (XYChart.Data<String, Integer> dataItem : chartDataSeries.getData()) {
            dataItem.getNode().getStyleClass().add("BarChart-default");
        }
    }

    public void clearSelectedNodes() {
        selectedNodes.forEach(node -> {
            node.getStyleClass().remove("BarChart-selected");
            node.getStyleClass().add("BarChart-default");
        });
        selectedNodes.clear();
    }

    public void selectNode(int index) {
        Node node = chartDataSeries.getData().get(index).getNode();
        node.getStyleClass().remove("BarChart-default");
        node.getStyleClass().add("BarChart-selected");
        selectedNodes.add(node);
    }
}
