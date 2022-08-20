import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreCharts {
    public static void main(String[] args) {

        // Data collected by running Store.java with inventoryStockCount = 30, 45, and 60
        int[] days = new int[30];
        int[] sales30 = new int[] {85, 46, 34, 59, 56, 65, 28, 51, 43, 39, 54, 45, 67, 59, 46, 48, 73, 25, 62, 44, 30, 45, 65, 62, 28, 71, 54, 61, 27, 21};
        int[] outages30 = new int[] {0, 6, 1, 4, 6, 0, 5, 3, 5, 3, 0, 9, 0, 1, 10, 0, 0, 6, 0, 0, 4, 8, 3, 2, 5, 5, 7, 2, 8, 5};
        int[] sales45 = new int[] {63, 77, 43, 57, 51, 46, 22, 23, 47, 41, 47, 61, 52, 46, 26, 35, 60, 48, 42, 82, 43, 60, 69, 60, 45, 43, 50, 45, 79, 77};
        int[] outages45 = new int[] {0, 0, 1, 11, 0, 1, 3, 4, 0, 2, 2, 6, 0, 2, 6, 0, 2, 10, 0, 1, 3, 1, 2, 0, 7, 3, 0, 6, 6, 1};
        int[] sales60 = new int[] {44, 76, 65, 42, 47, 36, 38, 53, 36, 62, 57, 80, 37, 66, 35, 46, 75, 27, 52, 46, 90, 44, 67, 63, 64, 73, 50, 65, 44, 83};
        int[] outages60 = new int[] {0, 0, 0, 3, 2, 0, 0, 5, 8, 0, 6, 3, 1, 3, 6, 0, 2, 3, 4, 0, 2, 1, 0, 2, 0, 1, 4, 0, 1, 0};

        int totalSales30 = 0;
        int totalOutages30 = 0;
        double totalCostOverall30 = 7722.11;
        int totalSales45 = 0;
        int totalOutages45 = 0;
        double totalCostOverall45 = 8547.63;
        int totalSales60 = 0;
        int totalOutages60 = 0;
        double totalCostOverall60 = 8275.46;

        // Calculate totals and create x-axis days list
        for (int i = 0; i < days.length; i++) {
            days[i] = i + 1;

            totalSales30 += sales30[i];
            totalOutages30 += outages30[i];

            totalSales45 += sales45[i];
            totalOutages45 += outages45[i];

            totalSales60 += sales60[i];
            totalOutages60 += outages60[i];
        }

        // Total Quantities for Bar Chart
        String[] quantities = new String[] {"30", "60", "90"};
        Number[] totalSales = new Number[] {totalSales30, totalSales45, totalSales60};
        Number[] totalOutages = new Number[] {totalOutages30, totalOutages45, totalOutages60};
        Number[] totalCosts = new Number[] {totalCostOverall30, totalCostOverall45, totalCostOverall60};

        // Declare a new XY Chart
        XYChart chart;

        // Build Line Chart for 30 Inventory Stock Sales and Outages
        chart = new XYChartBuilder().width(600).height(500).title("30 Inventory Stock Sales and Outages").xAxisTitle("X").yAxisTitle("Y").build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.addSeries("Daily Sales 30 Stock", days, sales30);
        chart.addSeries("Daily Outages 30 Stock", days, outages30);

        new SwingWrapper<>(chart).displayChart();

        // Build Line Chart for 45 Inventory Stock Sales and Outages
        chart = new XYChartBuilder().width(600).height(500).title("45 Inventory Stock Sales and Outages").xAxisTitle("X").yAxisTitle("Y").build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.addSeries("Daily Sales 45 Stock", days, sales45);
        chart.addSeries("Daily Outages 45 Stock", days, outages45);

        new SwingWrapper<>(chart).displayChart();

        // Build Line Chart for 60 Inventory Stock Sales and Outages
        chart = new XYChartBuilder().width(600).height(500).title("60 Inventory Stock Sales and Outages").xAxisTitle("X").yAxisTitle("Y").build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.addSeries("Daily Sales 60 Stock", days, sales60);
        chart.addSeries("Daily Outages 60 Stock", days, outages60);

        new SwingWrapper<>(chart).displayChart();

        CategoryChart barchart = new CategoryChartBuilder().width(800).height(600).title("Summary Measure Comparison").xAxisTitle("Inventory Stock").yAxisTitle("Amount").theme(Styler.ChartTheme.GGPlot2).build();

        barchart.addSeries("Total Roll Sales", new ArrayList<>(Arrays.asList(quantities)), new ArrayList<>(Arrays.asList(totalSales)));
        barchart.addSeries("Total Roll Outage Impacts", new ArrayList<>(Arrays.asList(quantities)), new ArrayList<>(Arrays.asList(totalOutages)));
        barchart.addSeries("Total Money in Sales", new ArrayList<>(Arrays.asList(quantities)), new ArrayList<>(Arrays.asList(totalCosts)));

        new SwingWrapper<>(barchart).displayChart();
    }
}
