package es.ulpgc.dis.View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JFreeHistogramDisplay histogramDisplay;

    public MainFrame() throws HeadlessException {
        this.setTitle("Kata 3");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(histogramDisplay = staticsPanel());
    }

    private JFreeHistogramDisplay staticsPanel() {
        return new JFreeHistogramDisplay();
    }

    public HistogramDisplay histogramDisplay() {
        return histogramDisplay;
    }
}
