package es.ulpgc.dis.io.Readers;

import es.ulpgc.dis.Model.Title;
import es.ulpgc.dis.io.TSVTitleDeserializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TSVTitleReader implements TitleReader {
    private final File file;

    public TSVTitleReader(File file) {
        this.file = file;
    }

    @Override
    public Iterator<Title> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        readHeader(reader);
        return new Iterator<>() {
            String line = reader.readLine();

            @Override
            public boolean hasNext() {
                return line != null;  // ¡Este es el error!
            }

            @Override
            public Title next() {
                try {
                    Title title = line == null ? null : deserializeTitle(line);
                    line = reader.readLine();
                    if (line == null) reader.close();
                    return title;
                } catch (IOException e) {
                    return null;
                }
            }
        };
    }


    private Title deserializeTitle(String line) {
        return new TSVTitleDeserializer().deserialize(line);
    }

    private static void readHeader(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
