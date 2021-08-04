package com.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import com.game.states.GameOver;
import com.game.states.Instructions;
import com.game.states.LevelOneTransition;
import com.game.states.LevelSelection;
import com.game.states.Menu;
import com.game.states.Play;
import com.game.states.VictoryScreen;

public class Main extends StateBasedGame {
	
	public Main(String name) {
		super(name);
	}

	public void initStatesList(GameContainer container) throws SlickException {
	    addState(new Menu(0));
		addState(new Play(1));
		addState(new GameOver(2));
		addState(new LevelOneTransition(3));
		addState(new VictoryScreen(4));
		addState(new Instructions(5));
		addState(new LevelSelection(6));
		addState(new LevelSelection(7));
		enterState(0);
	}

	public static void main(String[] args) {
		AppGameContainer app = null;
		try {
            app = new AppGameContainer(new Main("Circles"));
            app.setTargetFrameRate(60);
            app.setShowFPS(false);
            app.setAlwaysRender(true);
            app.setVerbose(false);
            app.setDisplayMode(800, 600, false);
            app.start();
        }catch(SlickException e) {
            e.printStackTrace();
        }
	}

}
