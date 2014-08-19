package com.geekeclectic.stashcache;

import java.util.ArrayList;
import org.json.*;


/**
 * Created by sylk on 8/18/2014.
 */
public class StashPattern {

    private ArrayList<StashThread> threads;
    private int patternHeight;
    private int patternWidth;
    private String patternDesigner;
    private StashFabric patternFabric;
    private String patternName;
    private boolean isKit;

    private static final String JSON_NAME = "name";
    private static final String JSON_HEIGHT = "height";
    private static final String JSON_WIDTH = "width";
    private static final String JSON_DESIGNER = "designer";
    private static final String JSON_FABRIC = "fabric";
    private static final String JSON_KIT = "kit";
    private static final String JSON_THREADS = "threads";

    public StashPattern(String name, int height, int width) {
        patternName = name;
        patternHeight = height;
        patternWidth = width;
    }

    public StashPattern(JSONObject json) throws JSONException {
        patternName = json.getString(JSON_NAME);
        patternHeight = json.getInt(JSON_HEIGHT);
        patternWidth = json.getInt(JSON_WIDTH);
        isKit = json.getBoolean(JSON_KIT);

        if (json.has(JSON_DESIGNER)) {
            patternDesigner = json.getString(JSON_DESIGNER);
        }

        if (json.has(JSON_FABRIC)) {
            patternFabric = new StashFabric(json.getJSONObject(JSON_FABRIC));
        }

        JSONArray array = json.getJSONArray(JSON_THREADS);
        for (int i = 0; i < array.length(); i++) {
            threads.add(new Thread(array.getJSONObject(i)));
        }
    }

    public void setDesigner(String designer) {
        patternDesigner = designer;
    }

    public String getDesigner() {
        return patternDesigner;
    }

    public void setFabric(StashFabric fabric) {
        patternFabric = fabric;
    }

    public StashFabric getFabric() {
        return patternFabric;
    }

    public void setHeight(int height) {
        patternHeight = height;
    }

    public int getHeight() {
        return patternHeight;
    }

    public void setWidth(int width) {
        patternWidth = width;
    }

    public int getWidth() {
        return patternWidth;
    }

    public void setName(String name) {
        patternName = name;
    }

    public String getName() {
        return patternName;
    }

    public void setKit(boolean kit) {
        isKit = kit;
    }

    public boolean getKit() {
        return isKit;
    }

}
