package com.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends Entity {
	
	private Input input;
	
	private int speed = 4;
	
	private boolean immune;
	private int immuneTimer = 1000;
	
	public Player(GameContainer container, float x, float y, int radius) {
		super(container, 0, x, y, radius);
		this.input = container.getInput();
	}
	
	
	public void render() {
	    if(!immune)
	        g.setColor(Color.white);
	    else {
            g.setColor(new Color(1, 1, 1, 0.3F));
	    }
		g.fill(shape);
		g.draw(shape);
		g.setColor(Color.black);
		g.draw(shape);
		g.setColor(Color.white);
		for(int i=0; i<maxHealth; i++) {
		    g.drawRect(10 + i * 15, 10, 10, 10);
		    if(i < health) {
		        g.fillRect(10 + i * 15, 10, 10, 10);
		    }
		}
		
	}

	public void update(int delta) {
		if(input.isKeyDown(Input.KEY_W)) {
			position.y -= speed;
		}else if(input.isKeyDown(Input.KEY_S)) {
			position.y += speed;
		}
		
		if(input.isKeyDown(Input.KEY_A)) {
			position.x -= speed;
		}else if(input.isKeyDown(Input.KEY_D)) {
			position.x += speed;
		}
		
		if(position.x - radius <= 0) position.x = radius;
		else if(position.x + radius >= container.getWidth()) position.x = container.getWidth() - radius;
		if(position.y - radius <= 0) position.y = radius;
		else if(position.y + radius >= container.getHeight()) position.y = container.getHeight() - radius;
		
		shape.setCenterX(position.x);
		shape.setCenterY(position.y);
		
		if(immune) {
		    immuneTimer -= delta;
		    if(immuneTimer <= 0) {
		        immune = false;
		        immuneTimer = 1000;
		    }
		}
		
		
	}
	
	
	   public void decreaseHealth(int amount) {
	       if(immune) return;
	        this.health -= amount;
	        if(health < 0) {
	            health = 0;
	            alive = false;
	        }
	        immune = true;
	    }

	
	

}
