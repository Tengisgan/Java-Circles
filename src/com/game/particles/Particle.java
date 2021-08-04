package com.game.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Particle {
	
	private Graphics g;
	private Color color;
	private float x;
	private float y;
	private float dx;
	private float dy;
	
	public Particle(Graphics g, Color color, float x, float y, float angle) {
		this.g = g;
		this.color = color;
		this.x = x;
		this.y = y;
		dx = (float) Math.random() - 0.5F;
		dy = (float) Math.random() - 0.5F;
	}
	
	public void draw() {
		
		x += dx;
		y += dy;
		
		g.setColor(color);
		g.fillOval(x, y, 2, 2);
	}
	
}
