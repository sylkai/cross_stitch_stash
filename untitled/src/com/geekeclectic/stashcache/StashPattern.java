package com.geekeclectic.stashcache;

import java.util.ArrayList;
import java.util.HashMap;

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
        isKit = false;
    }

    public StashPattern(JSONObject json, HashMap<String, StashThread> threadMap, HashMap<String, StashFabric> fabricMap) throws JSONException {
        patternName = json.getString(JSON_NAME);
        patternHeight = json.getInt(JSON_HEIGHT);
        patternWidth = json.getInt(JSON_WIDTH);
        isKit = json.getBoolean(JSON_KIT);

        if (json.has(JSON_DESIGNER)) {
            patternDesigner = json.getString(JSON_DESIGNER);
        }

        if (json.has(JSON_FABRIC)) {
            patternFabric = fabricMap.get(json.get(JSON_FABRIC));
            patternFabric.setUsedFor(this);
        }

        JSONArray array = json.getJSONArray(JSON_THREADS);
        for (int i = 0; i < array.length(); i++) {
            StashThread thread = threadMap.get(array.getString(i));
            thread.usedInPattern(this);
            threads.add(thread);
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_NAME, patternName);
        json.put(JSON_HEIGHT, patternHeight);
        json.put(JSON_WIDTH, patternWidth);
        json.put(JSON_KIT, isKit);

        if (patternDesigner != null) {
            json.put(JSON_DESIGNER, patternDesigner);
        }

        if (patternFabric != null) {
            json.put(JSON_FABRIC, patternFabric.getId());
        }

        JSONArray array = new JSONArray();
        for (StashThread thread : threads) {
            array.put(thread.getKey());
        }

        json.put(JSON_THREADS, array);

        return json;
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

    public ArrayList<StashThread> getThreadList() {
        return threads;
    }

    public void addThread(StashThread thread) {
        threads.add(thread);
    }

}
