package es.ulpgc.dis;

import es.ulpgc.dis.Control.GetRandomTitleCommand;
import es.ulpgc.dis.Control.ToggleStaticsCommand;
import es.ulpgc.dis.View.MainFrame;
import es.ulpgc.dis.io.Loaders.DBTitleTypeHistogramLoader;
import es.ulpgc.dis.io.Loaders.HistogramLoader;
import es.ulpgc.dis.io.Loaders.TitleTypeHistogramLoader;
import es.ulpgc.dis.io.Readers.SQLiteTitleReader;
import es.ulpgc.dis.io.Readers.TSVTitleReader;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File tsvFile = new File("C:\\Users\\evaay\\Desktop\\IS2\\title.basics.tsv");
        File dbFile = new File("database.db");
        MainFrame mainFrame = new MainFrame();
        HistogramLoader histogram = new DBTitleTypeHistogramLoader(dbFile);
        ((DBTitleTypeHistogramLoader) histogram).loadDB(new TSVTitleReader(tsvFile));

        mainFrame.put("toggle", new ToggleStaticsCommand(tsvFile, dbFile, mainFrame));
        mainFrame.put("getRandomTitle", new GetRandomTitleCommand(mainFrame, dbFile));

        mainFrame.histogramDisplay().show(histogram.load(new SQLiteTitleReader(dbFile)));
        mainFrame.setVisible(true);
    }
}
