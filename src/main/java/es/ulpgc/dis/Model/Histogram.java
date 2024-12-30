package es.ulpgc.dis.Model;

import java.util.ArrayList;

public interface Histogram {
    ArrayList<String> labels();
    int valueOf(String label);

    String getTitle();
    String getxAxis();
    String getyAxis();
}
