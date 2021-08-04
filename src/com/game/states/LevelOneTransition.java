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

public class LevelOneTransition extends BasicGameState {
    
    private Image background;
    
    private int id;
    
    private Input input;
    private AngelCodeFont font;
    
    public LevelOneTransition(int id) {
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
        String levelNumber = "Level 1";
        
        int xPosition = (container.getWidth() - font.getWidth(levelNumber)) / 2;
        int yPosition = container.getHeight() / 2;
        g.drawString(levelNumber, xPosition, yPosition - 30);
        
        g.setColor(Color.red);
        g.drawString("C", xPosition + 2, yPosition);
        g.setColor(Color.green);
        g.drawString("o", xPosition + 12, yPosition);
        g.setColor(Color.blue);
        g.drawString("l", xPosition + 22, yPosition);
        g.setColor(Color.magenta);
        g.drawString("o", xPosition + 32, yPosition);
        g.setColor(Color.yellow);
        g.drawString("r", xPosition + 42, yPosition);
        g.setColor(Color.orange);
        g.drawString("s", xPosition + 52, yPosition);
        if (input.isMousePressed(0)) {
            game.getState(1).init(container, game);
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    public int getID() {
        return id;
    }

}
