package com.game.projectiles;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class Bullet extends Projectile {
    
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    

    
	public Bullet(GameContainer container, int id, float x, float y, float toX, float toY, int size, int speed) {
		super(container, id, x, y, toX, toY, size, speed);
		bullets.add(this);
	}
	
	
	public void render() {
		g.setColor(color);
		g.fill(shape);
		g.draw(shape);
		g.setColor(Color.black);
		g.draw(shape);
	}
	
	public void update() {
		
		x += dx * speed;
		y += dy * speed;
		
		
		shape.setCenterX(x);
		shape.setCenterY(y);
		
		
//		if(x >= container.getWidth() - radius || x <= radius) {
//		    dx *= -1;
//		    bounced ++;
//		}
//		if(y > container.getHeight() - radius || y <= radius) {
//		    dy *= -1;
//		    bounced ++;
//		}
//		
//		if(bounced >= 3) alive = false;
		
		if(x <= 0 || x >= container.getWidth() || y <= 0 || y >= container.getHeight()) {
			alive = false;
		}
	}
	
}
