package com.game.utils;

public class Vector {
    
    public float x;
    public float y;
    
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector() {
        this(0, 0);
    }
    
    public void add(Vector other) {
        x += other.x;
        y += other.y;
    }
    
    public void sub(Vector other) {
        x -= other.x;
        y -= other.y;
    }
    
    public void multiply(float scale) {
        x *= scale;
        y *= scale;
    }
    
    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }
    
    public void setMagnitude(float magnitude) {
        normalize();
        multiply(magnitude);
    }
    
    public void normalize() {
        float magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
    }
    
    public void limit(float limit) {
        if(magnitude() > limit) {
            setMagnitude(limit);
        }
    }
    
    
    public static Vector add(Vector one, Vector two) {
        return new Vector(one.x + two.x, one.y + two.y);
    }
    
    public static Vector sub(Vector one, Vector two) {
        return new Vector(one.x - two.x, one.y - two.y);
    }
    
}
