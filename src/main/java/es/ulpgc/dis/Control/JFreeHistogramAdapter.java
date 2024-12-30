package es.ulpgc.dis.Control;

import es.ulpgc.dis.Model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeHistogramAdapter {
    public static JFreeChart adapt(Histogram histogram) {
        return ChartFactory.createBarChart(
                histogram.getTitle(),
                histogram.getxAxis(),
                histogram.getyAxis(),
                datasetOf(histogram)
        );
    }

    private static CategoryDataset datasetOf(Histogram histogram) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String category : histogram.labels()) {
            dataset.addValue(histogram.valueOf(category), "Frecuency", category);
        }
        return dataset;
    }
}
