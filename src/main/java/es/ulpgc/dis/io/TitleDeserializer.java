package es.ulpgc.dis.io;

import es.ulpgc.dis.Model.Title;

public interface TitleDeserializer {
    Title deserialize(String line);
}
