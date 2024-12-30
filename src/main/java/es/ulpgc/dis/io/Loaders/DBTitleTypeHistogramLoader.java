package es.ulpgc.dis.io.Loaders;

import es.ulpgc.dis.Model.Histogram;
import es.ulpgc.dis.Model.Title;
import es.ulpgc.dis.Model.TitletypeHistogram;
import es.ulpgc.dis.io.HistogramGenerator;
import es.ulpgc.dis.io.Readers.TitleReader;
import es.ulpgc.dis.io.Writers.SQLiteTitleWriter;
import es.ulpgc.dis.io.Writers.TitleWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class DBTitleTypeHistogramLoader implements HistogramLoader{
    private final File dbFile;
    private static Integer BATCH_SIZE = 1000;

    public DBTitleTypeHistogramLoader(File dbFile) {
        this.dbFile = dbFile;
    }

    public void loadDB(TitleReader titleReader) throws IOException {
        Iterator<Title> titles = titleReader.read();
        TitleWriter dbWriter = new SQLiteTitleWriter(dbFile);

        int count = 0;
        while (titles.hasNext() && count < BATCH_SIZE) {
            dbWriter.write(titles.next());
            count++;
        }
    }

    public void loadMoreTitle(TitleReader titleReader, Integer LOAD_DB) throws IOException {
        Iterator<Title> titles = titleReader.read();
        TitleWriter dbWriter = new SQLiteTitleWriter(dbFile);

        int count = 0;
        for (int i = 0; i < LOAD_DB; i++) {
            titles.next();
        }
        while (titles.hasNext() && count < (BATCH_SIZE + LOAD_DB)) {
            dbWriter.write(titles.next());
            count++;
        }
    }

    @Override
    public Histogram load(TitleReader titleReader) throws IOException {
        HistogramGenerator histogramGenerator = new HistogramGenerator();
        return new TitletypeHistogram(histogramGenerator.createMapHistogram(titleReader));
    }
}
