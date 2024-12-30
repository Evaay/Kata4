package es.ulpgc.dis.View;

import es.ulpgc.dis.Control.JFreeHistogramAdapter;
import es.ulpgc.dis.Model.Histogram;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public class JFreeHistogramDisplay extends JPanel implements HistogramDisplay {
    public JFreeHistogramDisplay() {
        setLayout(new BorderLayout());
    }

    @Override
    public void show(Histogram histogram) {
        add(new ChartPanel(adapt(histogram)));
    }

    private JFreeChart adapt(Histogram histogram) {
        return JFreeHistogramAdapter.adapt(histogram);
    }
}
