package es.ulpgc.dis.Control;

import es.ulpgc.dis.View.MainFrame;
import es.ulpgc.dis.io.Loaders.DBTitleTypeHistogramLoader;
import es.ulpgc.dis.io.Loaders.HistogramLoader;
import es.ulpgc.dis.io.Readers.SQLiteTitleReader;
import es.ulpgc.dis.io.Readers.TSVTitleReader;
import es.ulpgc.dis.io.Readers.TitleReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ToggleStaticsCommand implements Command{
    private final TitleReader titleReader;
    private final File dbFile;
    private final MainFrame mainFrame;
    private static Integer BATCH_SIZE = 1000;

    public ToggleStaticsCommand(File tsvFile, File dbFile, MainFrame mainFrame) {
        this.titleReader = new TSVTitleReader(tsvFile);
        this.dbFile = dbFile;
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() throws IOException {
        HistogramLoader histogram = new DBTitleTypeHistogramLoader(dbFile);
        ((DBTitleTypeHistogramLoader) histogram).loadMoreTitle(titleReader, BATCH_SIZE);
        BATCH_SIZE+= 1000;
        TitleReader SQLiteTitleReader = new SQLiteTitleReader(dbFile);
        mainFrame.histogramDisplay().show(histogram.load(SQLiteTitleReader));
    }
}
