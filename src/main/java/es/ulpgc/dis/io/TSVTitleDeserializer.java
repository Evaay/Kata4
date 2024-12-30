package es.ulpgc.dis.io;

import es.ulpgc.dis.Model.Title;

public class TSVTitleDeserializer implements TitleDeserializer {
    @Override
    public Title deserialize(String line) {
        String[] fields = line.split("\t");
        return new Title(fields[0],
                toTitleType(fields[1]),
                fields[2]);
    }

    private Title.TitleType toTitleType(String field) {
        return Title.TitleType.valueOf(toCapitalize(field));
    }

    private String toCapitalize(String field) {
        return field.toUpperCase().charAt(0) + field.substring(1);
    }
}
