package es.ulpgc.dis.io.Writers;

import es.ulpgc.dis.Model.Title;

import java.io.IOException;

public interface TitleWriter {
    void write(Title title) throws IOException;
}
