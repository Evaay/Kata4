package es.ulpgc.dis.io;

import es.ulpgc.dis.Model.Title;
import es.ulpgc.dis.io.Readers.TitleReader;

import java.io.IOException;
import java.util.*;

public class HistogramGenerator {
    private Map<Title.TitleType, Integer> generateMapHistogram(List<Title> titles) {
        Map<Title.TitleType, Integer> histogram = new HashMap<>();
        for (Title title : titles) {
            histogram.put(title.titleType(), histogram.getOrDefault(title.titleType(), 0) + 1);
        }
        return histogram;
    }

    public Map<Title.TitleType, Integer> createMapHistogram(TitleReader titleReader) throws IOException {
        Iterator<Title> titlesIterator = titleReader.read();
        List<Title> titlesList = getTitlesList(titlesIterator);
        Map<Title.TitleType, Integer> histogram = generateMapHistogram(titlesList);
        return histogram;
    }

    private static List<Title> getTitlesList(Iterator<Title> titlesIterator) {
        List<Title> titlesList = new ArrayList<>();
        while (titlesIterator.hasNext()) {
            Title title = titlesIterator.next();
            titlesList.add(title);
        }
        return titlesList;
    }
}
