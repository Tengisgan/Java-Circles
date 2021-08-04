package com.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

public class Enemy extends Entity {

	public Enemy(GameContainer container, float x, float y, int radius) {
		super(container, 1, x, y, radius);
	}


	public void render() {
		g.setColor(Color.red);
		g.fill(shape);
		g.draw(shape);
		
	}

	public void update(int delta) {
		
	}
	

}
