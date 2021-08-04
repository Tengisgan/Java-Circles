package com.game.entities;

import org.newdawn.slick.GameContainer;

public abstract class Boss extends Entity {

    public Boss(GameContainer container, int id, float x, float y, int radius) {
        super(container, id, x, y, radius);
        this.boss = true;
    }
    
}   
