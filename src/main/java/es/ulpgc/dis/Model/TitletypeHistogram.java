package es.ulpgc.dis.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TitletypeHistogram implements Histogram{
    private final Map<Title.TitleType, Integer> titleTypeMap;

    public TitletypeHistogram(Map<Title.TitleType, Integer> titleTypeMap) {
        this.titleTypeMap = titleTypeMap;
    }

    @Override
    public ArrayList<String> labels() {
        return new ArrayList<>(getStringMap().keySet());
    }

    @Override
    public int valueOf(String label) {
        return getStringMap().get(label);
    }

    @Override
    public String getTitle() {
        return "TitleType Histogram";
    }

    @Override
    public String getxAxis() {
        return "Categories";
    }

    @Override
    public String getyAxis() {
        return "Frecuency";
    }

    private Map<String, Integer> getStringMap() {
        Map<String, Integer> StringMap = new HashMap<>();
        for (Title.TitleType titleType : titleTypeMap.keySet()) {
            StringMap.put(titleType.name(), titleTypeMap.get(titleType));
        }
        return StringMap;
    }
}
