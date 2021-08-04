package com.game.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public abstract class Projectile {
	
	protected GameContainer container;
	protected Graphics g;
	protected float x;
	protected float y;
	protected int radius;
	
	protected int id;
	protected int speed;
	
	protected Shape shape;
	protected Color color;
	
	protected float angle;
	protected float dx;
	protected float dy;
	
	protected boolean alive = true;
	
	public Projectile(GameContainer container, int id, float x, float y, float toX, float toY, int radius, int speed) {
		this.container = container;
		this.g = container.getGraphics();
		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speed = speed;
		this.color = id == 0 ? Color.white : Color.red;
		this.shape = new Circle(x, y, radius);
		
		angle = (float) Math.atan2(toY - y, toX - x);
		dx = speed * (float) Math.cos(angle);
		dy = speed * (float) Math.sin(angle);
		
	}
	
	public abstract void render();
	public abstract void update();
	
	
	public int getId() {
		return id;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public float getAngle() {
		return angle;
	}
	
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void invertDx() {
	    dx *= -1;
	}
	
	public void invertDy() {
	    dy *= -1;
	}
	
	public void changeId(int id) {
	    this.id = id;
	}
	

	
}
