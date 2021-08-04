package com.game.states;

import static com.game.projectiles.Bullet.bullets;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.game.entities.EmotionBoss;

import com.game.entities.Entity;
import com.game.entities.Player;
import com.game.projectiles.Bullet;

public class Play extends BasicGameState {
	
    private int timer = 0; 
    
    private Music music; 
    private boolean musicIsPlayed;
    
    private Image background;
	private int id;
	public Play(int id) {
		this.id = id;
	}
	
	private Input input;
	private ArrayList<Entity> entities;
	
	private Player player;
	
	

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	    entities = new ArrayList<>();
	    music = new Music("Music/Boss.wav");
	    Bullet.bullets.clear();
	    musicIsPlayed = false; 
	    
		background = new Image("Images/BossBackground.png");
		input = container.getInput();
		
		player = new Player(container, 100, 100, 7);
		entities.add(player);
//		entities.add(new Enemy(container, 300, 300, 7));
		
		entities.add(new EmotionBoss(container, player, 2, container.getWidth() / 2, container.getHeight() / 2, 30));
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	    
	    g.setAntiAlias(true);
	    background.draw();
		for(int i=0; i<entities.size(); i++) {
			entities.get(i).render();
		}
		
		for(int i=0; i<Bullet.bullets.size(); i++) {
			if(Bullet.bullets.get(i).isAlive()) {
			    Bullet.bullets.get(i).render();
			}
		}
	}
	

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	    if (input.isKeyDown(Input.KEY_ESCAPE)) {
	        music.stop();
	        game.getState(0).init(container, game);;
	        game.enterState(0, new FadeOutTransition(), new FadeInTransition());
	    }
	    if (!player.isAlive()) {
	        music.stop();
	        
	        game.enterState(2, new FadeOutTransition(), new FadeInTransition());
	    }
	    
	    for (int i = 0; i < entities.size(); i++) {
	        if (entities.get(i).isBoss() && !entities.get(i).isAlive()) {
	            music.stop();
	            game.getState(4).init(container, game);
	            game.enterState(4);
	        }
	    }
	    
        if (!musicIsPlayed) {
            
            music.loop();
            musicIsPlayed = true; 
        }
	    if(input.isMouseButtonDown(0) && timer >= 250) {  
	        new Bullet(container, 0, player.getX(), player.getY(), input.getMouseX(), input.getMouseY(), 2, 3);
	        timer = 0;
	    }
	    
		for(int i=0; i<entities.size(); i++) {
			if(entities.get(i).isAlive()) {
				entities.get(i).update(delta);
			}else {
				entities.remove(i);
			}
		}
		
		for(int i=0; i<bullets.size(); i++) {
			if(bullets.get(i).isAlive()) {
				bullets.get(i).update();
				for(int j=0; j<entities.size(); j++) {
					if(entities.get(j).getId() != bullets.get(i).getId() && entities.get(j).intersects(bullets.get(i))) {
					    
					    if(entities.get(j).isBoss()) {
					        if (((EmotionBoss)entities.get(j)).isHappy()) {
					            bullets.get(i).invertDx();
					            bullets.get(i).invertDy();
					            bullets.get(i).changeId(entities.get(j).getId());
					        }
					        else {
	                           bullets.get(i).setAlive(false);
	                           entities.get(j).decreaseHealth(1);
					        }
					        
					    }
//					        }
//					    }
					    
//					    if(entities.get(j).getId() == 2) {
//					        bullets.get(i).invertDx();
//		                    bullets.get(i).invertDy();
//		                    bullets.get(i).changeId(entities.get(j).getId());
//					    }else {
					    else {
					        bullets.get(i).setAlive(false);
					        entities.get(j).decreaseHealth(1);
					    }
//					    }
//					    System.out.println(entities.get(j).getId() + ", " + bullets.get(i).getId());
//					    bullets.get(i).invertDx();
//					    bullets.get(i).invertDy();
//					    bullets.get(i).changeId(entities.get(j).getId());
					}
				}
			}else {
				bullets.remove(i);
			}
		}
		timer += delta;
	}

	public int getID() {
		return id;
	}

}
