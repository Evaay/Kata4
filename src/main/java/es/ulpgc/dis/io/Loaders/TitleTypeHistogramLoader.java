package es.ulpgc.dis.io.Loaders;

import es.ulpgc.dis.Model.Histogram;
import es.ulpgc.dis.Model.TitletypeHistogram;
import es.ulpgc.dis.io.HistogramGenerator;
import es.ulpgc.dis.io.Readers.TitleReader;

import java.io.IOException;

public class TitleTypeHistogramLoader implements HistogramLoader {
    @Override
    public Histogram load(TitleReader titleReader) throws IOException {
        HistogramGenerator histogramGenerator = new HistogramGenerator();
        return new TitletypeHistogram(histogramGenerator.createMapHistogram(titleReader));
    }
}
