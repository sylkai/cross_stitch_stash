package com.geekeclectic.stashcache;

import java.util.*;
import java.io.*;
import org.json.*;


/**
 * Created by sylk on 8/18/2014.
 */
public class StashData {

    private ArrayList<StashPattern> patternsData;
    private HashMap<String, StashThread> threadsData;
    private HashMap<String, StashFabric> fabricData;

    public StashData() {
        HashMap<String, StashThread> threadsData = new HashMap();
        HashMap<String, StashFabric> fabricData = new HashMap();
        ArrayList<StashPattern> patternsData = new ArrayList();
    }

    public StashData(String filename) {
        HashMap<String, StashThread> threadsData = new HashMap();
        HashMap<String, StashFabric> fabricData = new HashMap();
        ArrayList<StashPattern> patternsData = new ArrayList();

        BufferedReader reader = null;
        try {
            // open and read file into a StringBuilder
            InputStream in = new FileInputStream(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                // line breaks omitted as irrelevant
                jsonString.append(line);
            }

            // parse the JSON using the JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            fillThreadData(array.getJSONArray(0));
            fillFabricData(array.getJSONArray(1));
            fillPatternData(array.getJSONArray(2));

            if (reader != null) {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray toJSON() {
        JSONArray array = new JSONArray();
        array.put(writeThreadData());
        array.put(writeFabricData());
        array.put(writePatternData());

        return array;
    }

    private void fillThreadData(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                StashThread thread = new StashThread(array.getJSONObject(i));
                threadsData.put(thread.toString(), thread);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray writeThreadData() {
        JSONArray array = new JSONArray();

        try {
            for (StashThread thread : threadsData.values()) {
                array.put(thread.toJSON());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    private void fillFabricData(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                StashFabric fabric = new StashFabric(array.getJSONObject(i));
                fabricData.put(fabric.toString(), fabric);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray writeFabricData() {
        JSONArray array = new JSONArray();

        try {
            for (StashFabric fabric : fabricData.values()) {
                array.put(fabric.toJSON());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    private void fillPatternData(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                StashPattern pattern = new StashPattern(array.getJSONObject(i), threadsData, fabricData);
                patternsData.add(pattern);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray writePatternData() {
        JSONArray array = new JSONArray();

        try {
            for (StashPattern pattern : patternsData) {
                array.put(pattern.toJSON());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

}
