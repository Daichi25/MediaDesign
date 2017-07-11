package com.example.daichi.mediadesign;

import java.io.Serializable;

/**
 * Created by shimbaroid on 2017/07/11.
 */

public class SensorParams implements Serializable{

    private float x;
    private float y;
    private float z;

    public SensorParams(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
