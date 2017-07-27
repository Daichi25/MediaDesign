package com.example.daichi.mediadesign;

import java.io.Serializable;

/**
 * Created by daichi on 2017/07/18.
 */

public class SensorParams implements Serializable {

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

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

}
