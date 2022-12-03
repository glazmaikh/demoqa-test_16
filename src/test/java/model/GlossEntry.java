package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GlossEntry {
    public String GlossTerm = "Standard Generalized Markup Language";
    public String ID = "SGML";
    public int Acronym = 123;
    public boolean Abbrev = true;
    public ArrayList<String> items;
    @SerializedName("wind")
    public Wind Wind;

    public static class Wind {
        public String title;
    }
}
