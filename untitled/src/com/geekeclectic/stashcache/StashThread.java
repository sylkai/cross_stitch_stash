package com.geekeclectic.stashcache;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sylk on 8/18/2014.
 */
public class StashThread {

    private static final String JSON_MAKER = "manufacturer";
    private static final String JSON_TYPE = "floss type";
    private static final String JSON_ID = "id code";

    private String madeBy;
    private String idCode;
    private String flossType;
    private ArrayList<StashPattern> usedIn;

    public StashThread(String companyId, String floss, String id) {
        madeBy = companyId;
        flossType = floss;
        idCode = id;
    }

    public StashThread(JSONObject json) {

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

}
