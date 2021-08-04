package com.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import com.game.projectiles.Projectile;
import com.game.utils.Healthbar;
import com.game.utils.Vector;

public abstract class Entity {

	protected GameContainer container;
	protected Graphics g;
	
	protected int id;
	protected Vector position;
	protected int radius;
	
	protected Circle shape;
	protected Color color;
	
	protected Healthbar healthbar;
	protected int maxHealth = 3;
	protected int health = maxHealth;
	protected boolean alive = true;
	
	protected boolean boss = false;
	
	public Entity(GameContainer container, int id, float x, float y, int radius) {
		this.container = container;
		this.g = container.getGraphics();
		this.id = id;
		this.position = new Vector(x, y);
		this.radius = radius;
		this.shape = new Circle(x, y, radius);
		this.healthbar = new Healthbar(container, this, 50, 4);
	}
	
	public abstract void render();
	public abstract void update(int delta);
	
	
	public boolean intersects(Entity other) {
		double dx = position.x - other.getX();
		double dy = position.y - other.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance <= (radius + other.getRadius());
	}
	
	public boolean intersects(Projectile projectile) {
		double dx = position.x - projectile.getX();
		double dy = position.y - projectile.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance <= (radius + projectile.getRadius());
	}
	
	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
		shape.setCenterX(x);
		shape.setCenterY(y);
	}
	
	
	public int getId() {
		return id;
	}
	
	public Vector getPosition() {
	    return position;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public int getMaxHealth() {
	    return maxHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decreaseHealth(int amount) {
		this.health -= amount;
		if(health < 0) {
			health = 0;
			alive = false;
		}
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public boolean isBoss() {
	    return boss;
	}

}
