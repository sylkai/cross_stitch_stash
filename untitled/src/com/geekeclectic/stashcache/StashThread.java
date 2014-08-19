package com.geekeclectic.stashcache;

import org.json.*;

import java.util.ArrayList;

/**
 * Created by sylk on 8/18/2014.
 */
public class StashThread {

    private static final String JSON_MAKER = "manufacturer";
    private static final String JSON_TYPE = "floss type";
    private static final String JSON_ID = "id code";
    private static final String JSON_OWNED = "number owned";

    private String madeBy;
    private String idCode;
    private String flossType;
    private int skeinsOwned;
    private ArrayList<StashPattern> usedIn;

    public StashThread(String companyId, String floss, String id) {
        madeBy = companyId;
        flossType = floss;
        idCode = id;
        skeinsOwned = 0;
    }

    public StashThread(JSONObject json) throws JSONException {
        madeBy = json.getString(JSON_MAKER);
        flossType = json.getString(JSON_TYPE);
        idCode = json.getString(JSON_ID);
        skeinsOwned = json.getInt(JSON_OWNED);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_MAKER, madeBy);
        json.put(JSON_TYPE, flossType);
        json.put(JSON_ID, idCode);
        json.put(JSON_OWNED, skeinsOwned);
        return json;
    }

    public void usedInPattern(StashPattern pattern) {
        usedIn.add(pattern);
    }

    public void removePattern(StashPattern pattern) {
        int index = usedIn.indexOf(pattern);

        if (index != -1) {
            usedIn.remove(index);
        }
    }

    public String getCompany() {
        return madeBy;
    }

    public String getId() {
        return idCode;
    }

    public String getType() {
        return flossType;
    }

    public String getKey() {
        return madeBy + " " + flossType + " " + idCode;
    }

    public void setSkeinsOwned(int number) {
        skeinsOwned = number;
    }

    public int getSkeinsOwned() {
        return skeinsOwned;
    }

    public boolean isOwned() {
        return (skeinsOwned != 0);
    }

}
