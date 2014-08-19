package com.geekeclectic.stashcache;

import org.json.*;

import java.util.UUID;

/**
 * Created by sylk on 8/18/2014.
 */
public class StashFabric {

    private static int EDGE_BUFFER = 2;

    private static final String JSON_COUNT = "fabric count";
    private static final String JSON_WIDTH = "fabric width";
    private static final String JSON_HEIGHT = "fabric height";
    private static final String JSON_COLOR = "fabric color";
    private static final String JSON_TYPE = "fabric type";
    private static final String JSON_BY = "fabric company";
    private static final String JSON_ID = "unique ID";

    private int fabricCount;
    private int fabricWidth;
    private int fabricHeight;
    private String fabricColor;
    private String fabricType;
    private String fabricBy;
    private StashPattern fabricFor;
    private UUID fabricId;

    private int stitchWidth;
    private int stitchHeight;

    // width and height both recorded in inches

    public StashFabric(int count, int width, int height) {
        fabricCount = count;
        fabricWidth = width;
        fabricHeight = height;
        fabricId = UUID.randomUUID();

        updateStitchableArea();
    }

    public StashFabric(JSONObject json) throws JSONException {
        fabricCount = json.getInt(JSON_COUNT);
        fabricWidth = json.getInt(JSON_WIDTH);
        fabricHeight = json.getInt(JSON_HEIGHT);
        fabricId = UUID.fromString(json.getString(JSON_ID));

        updateStitchableArea();

        if (json.has(JSON_COLOR)) {
            fabricColor = json.getString(JSON_COLOR);
        }

        if (json.has(JSON_TYPE)) {
            fabricType = json.getString(JSON_TYPE);
        }

        if (json.has(JSON_BY)) {
            fabricBy = json.getString(JSON_BY);
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, fabricId.toString());
        json.put(JSON_COUNT, fabricCount);
        json.put(JSON_WIDTH, fabricWidth);
        json.put(JSON_HEIGHT, fabricHeight);

        if (fabricColor != null) {
            json.put(JSON_COLOR, fabricColor);
        }

        if (fabricType != null) {
            json.put(JSON_TYPE, fabricType);
        }

        if (fabricBy != null) {
            json.put(JSON_BY, fabricBy);
        }

        return json;
    }

    public void setMadeBy(String producer) {
        fabricBy = producer;
    }

    public String madeBy() {
        return fabricBy;
    }

    public void setColor(String color) {
        fabricColor = color;
    }

    public String getColor() {
        return fabricColor;
    }

    public void setType(String type) {
        fabricType = type;
    }

    public String getType() {
        return fabricType;
    }

    public void setUsedFor(StashPattern pattern) {
        fabricFor = pattern;
    }

    public StashPattern usedFor() {
        return fabricFor;
    }

    public void setCount(int count) {
        fabricCount = count;
        updateStitchableArea();
    }

    public int getCount() {
        return fabricCount;
    }

    public void setWidth(int width) {
        fabricWidth = width;
        updateStitchableArea();
    }

    public int getWidth() {
        return fabricWidth;
    }

    public void setHeight(int height) {
        fabricHeight = height;
        updateStitchableArea();
    }

    public int getHeight() {
        return fabricHeight;
    }

    public boolean willFit(int width, int height) {
        if (stitchWidth >= width && stitchHeight >= height) {
            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return fabricId.toString();
    }

    private void updateStitchableArea() {
        stitchWidth = (fabricWidth - EDGE_BUFFER * 2) * fabricCount;
        stitchHeight = (fabricHeight - EDGE_BUFFER * 2) * fabricCount;
    }

}
