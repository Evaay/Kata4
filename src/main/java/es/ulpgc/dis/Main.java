package es.ulpgc.dis;

import es.ulpgc.dis.View.MainFrame;
import es.ulpgc.dis.io.Loaders.HistogramLoader;
import es.ulpgc.dis.io.Loaders.TitleTypeHistogramLoader;
import es.ulpgc.dis.io.Readers.TSVTitleReader;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File tsvFile = new File("C:\\Users\\evaay\\Desktop\\IS2\\title.basics.tsv");
        MainFrame mainFrame = new MainFrame();
        HistogramLoader histogram = new TitleTypeHistogramLoader();
        mainFrame.histogramDisplay().show(histogram.load(new TSVTitleReader(tsvFile)));
        mainFrame.setVisible(true);
    }
}
