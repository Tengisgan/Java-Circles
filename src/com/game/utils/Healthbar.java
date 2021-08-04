package com.game.utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import com.game.entities.Entity;

public class Healthbar {
	
	private Graphics g;
	
	private int width;
	private int height;
	
	private Entity entity;
	private float scale;
	
	private Color color;
	
	public Healthbar(GameContainer container, Entity entity, int width, int height) {
		this.g = container.getGraphics();
		this.entity = entity;
		this.width = width;
		this.height = height;

		this.scale = width / (float) entity.getHealth();
		this.color = entity.getId() == 0 ? Color.white : Color.red;
	}
	
	public void render() {
		g.setColor(color);
		
		g.fillRect(entity.getX() - width / 2, entity.getY() - entity.getRadius() - height * 3, entity.getHealth() * scale, height);
		g.drawRect(entity.getX() - width / 2, entity.getY() - entity.getRadius() - height * 3, width, height);
		
	}
	
}
