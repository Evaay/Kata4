package es.ulpgc.dis.io.Loaders;

import es.ulpgc.dis.Model.Histogram;
import es.ulpgc.dis.io.Readers.TitleReader;

import java.io.IOException;

public interface HistogramLoader {
    Histogram load(TitleReader titleReader) throws IOException;
}
