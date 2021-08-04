package com.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import com.game.projectiles.Bullet;
import com.game.utils.Vector;

public class EmotionBoss extends Boss {
    
    
    private Player player;
    
    private Vector target;
    private float speed = 5;
    
    private int timer = 0;
    
    private int healthbarWidth = 750;
    private float scale;

    private boolean angry;
    private boolean happy;
    private boolean neutral;
    private boolean sad;


    private boolean finishedMachineGun;
    private int machineGun = 0;
    
    public EmotionBoss(GameContainer container, Player player, int id, float x, float y, int radius) {
        super(container, id, x, y, radius);
        this.player = player;
        this.health = 200;
        
        scale = healthbarWidth / (float) health;
        
    }

    public void render() {
        g.setColor(color);
        g.fill(shape);
        g.draw(shape);
        g.setColor(Color.black);
        g.draw(shape);
//        g.drawString("" + health, 200, 10);
        
        
//        g.setColor(Color.red);
//        g.fillRect(50, container.getHeight() - 65, scale * health, 25);
        g.setColor(Color.white);
//        g.drawRect(50, container.getHeight() - 65, healthbarWidth, 25);
        g.fillRoundRect(25, container.getHeight() - 20, healthbarWidth, 5, 5);
        g.setColor(Color.red);
        g.fillRoundRect(25, container.getHeight() - 20, scale * health, 5, 5);
        
         
        
    }
    
    public void update(int delta) {
        if(timer >= 1500) {
            if(isFinishedWithPhase()) {
                chooseRandomMethod();
            }
            timer = 0;
        }
        
        if(isHappy() || isAngry()) {
            if(target != null) {
                Vector direction = Vector.sub(target, position);
                if(direction.magnitude() <= speed) {
                    target = null;
                }else {
                    direction.setMagnitude(speed);
                    position.add(direction);
                }
            }   
        }
        
        else if(isSad()) {

            if(machineGun >= 2000) {

                finishedMachineGun = true;
                machineGun = 0;
            }
            
            target = player.getPosition();
            
            new Bullet(container, id, position.x, position.y, target.x, target.y, 2, 3);
            if (health <= 100) {
                new Bullet(container, id, position.x, position.y, (target.x / 2), target.y / 2, 2, 3);
            }
            machineGun += delta;
            
        }
        else if (isNeutral()) {
            timer += 10;
        }
        
        
        if(player.intersects(this)) {
            player.decreaseHealth(1);
        }
        
        shape.setCenterX(position.x);
        shape.setCenterY(position.y);
        timer += delta;
    }
    
    private boolean isFinishedWithPhase() {
        if(isSad()) {
            return finishedMachineGun;
        }
        return true;
    }
    
    private void chooseRandomMethod() {
        angry = false;
        happy = false;
        neutral = false; 
        sad = false;
        
        int random = (int) (Math.random() * 4);
        if(random == 0) {
            angry();
        }else if(random == 1) {
            neutral();
        }
        else if(random == 2) {
            happy();
        }
        else if(random == 3) {
            sad();
        }
    }
    
    private void angry() { 
        color = Color.red;
        angry = true;
        
        int numShots = 12 + (int) ((1F - (health / 200F)) * 24);
        float angle = 360 / (float) numShots;
        for(int i=0; i<numShots; i++) {
            float bulletX = position.x + (float) Math.cos(Math.toRadians(i * angle)) * radius;
            float bulletY = position.y + (float) Math.sin(Math.toRadians(i * angle)) * radius;
            new Bullet(container, id, position.x, position.y, bulletX, bulletY, 2, 3);
            speed = 5;
            target = new Vector(player.getPosition().x, player.getPosition().y);
        }
    }
    
    private void neutral() {
        color = Color.yellow;
        neutral = true;
        target = null;
        int bulletSize = 25 + (int) ((1F - (health / 200F)) * 20);
        new Bullet(container, id, position.x, position.y, player.getPosition().x, player.getPosition().y, bulletSize, 3);
    }
    
    private void happy() {
        color = Color.green;

        happy = true;
        
        speed = 10 + (int) ((1F - (health / 200F)) * 20);
        target = new Vector(player.getPosition().x, player.getPosition().y);

    }
    
    public void sad() {
        color = Color.blue;
        sad = true;
    }
    
    //for telling the states
    public boolean isAngry() {
        return angry;
    }
    
    public boolean isHappy() {
        return happy;
    }
    
    public boolean isNeutral() {
        return neutral;
    }
    
    public boolean isSad() {
        return sad;
    }
}



