package es.ulpgc.dis.io.Readers;

import es.ulpgc.dis.Model.Title;

import java.io.IOException;
import java.util.Iterator;

public interface TitleReader {
    Iterator<Title> read() throws IOException;
}
