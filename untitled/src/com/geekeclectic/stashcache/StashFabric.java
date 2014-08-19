package com.geekeclectic.stashcache;

/**
 * Created by sylk on 8/18/2014.
 */
public class StashFabric {

    private static int EDGE_BUFFER = 2;

    private int fabricCount;
    private int fabricWidth;
    private int fabricHeight;
    private String fabricColor;
    private String fabricType;
    private String fabricBy;
    private StashPattern fabricFor;

    private int stitchWidth;
    private int stitchHeight;

    // width and height both recorded in inches

    public StashFabric(int count, int width, int height) {
        fabricCount = count;
        fabricWidth = width;
        fabricHeight = height;

        updateStitchableArea();
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

    private void updateStitchableArea() {
        stitchWidth = (fabricWidth - EDGE_BUFFER * 2) * fabricCount;
        stitchHeight = (fabricHeight - EDGE_BUFFER * 2) * fabricCount;
    }

}
