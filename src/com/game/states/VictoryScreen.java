package com.game.states;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class VictoryScreen extends BasicGameState {
    
    private Image background;
    
    private int id;
    
    private Input input;
    private AngelCodeFont font;
    
    public VictoryScreen(int id) {
        this.id = id;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("Images/TransitionBackground.png");
        
        input = container.getInput();
        font = (AngelCodeFont) container.getDefaultFont();
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        background.draw();
        g.setColor(Color.white);
        String message = "Congratulations, you win. Please give me suggestions on improving the game.";
        
        int xPosition = (container.getWidth() - font.getWidth(message)) / 2;
        int yPosition = container.getHeight() / 2;
        
        g.drawString(message, xPosition, yPosition);
        
        if (input.isMousePressed(0)) {
            game.getState(0).init(container, game);
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    public int getID() {
        return id;
    }

}
