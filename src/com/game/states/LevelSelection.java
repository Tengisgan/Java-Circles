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

public class LevelSelection extends BasicGameState {
    
    private Image background;
    
    private int id;
    
    private Input input;
    private AngelCodeFont font;
    
    public LevelSelection(int id) {
        this.id = id;
    }

    private String[] options = new String[] {
            "Level 1", "Level 2", "Level 3", "Back"
    };

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("Images/TransitionBackground.png");
        
        input = container.getInput();
        font = (AngelCodeFont) container.getDefaultFont();

    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        background.draw();
        g.setColor(Color.white);
//        g.fill(new Rectangle(100, 100, 100, 30), fill);
        int mx = input.getMouseX();
        int my = input.getMouseY();

        for (int i = 0; i < options.length; i++) {
            int xPosition = (container.getWidth() - font.getWidth(options[i])) / 2;
            int yPosition = 250 + i * 30;
            g.setColor(Color.white);
            if(mx >= xPosition && mx <= xPosition + font.getWidth(options[i])) {
                if(my >= yPosition && my <= yPosition + font.getHeight(options[i])) {
                    g.setColor(Color.lightGray);
                    if (options[i] == "Level 1" && input.isMousePressed(0)) {
                        game.enterState(3, new FadeOutTransition(), new FadeInTransition());
                    }
                    else if (options[i] == "Back" && input.isMousePressed(0)) {
                        game.enterState(0, new FadeOutTransition(), new FadeInTransition());
                    }
                }
            }
            g.drawString(options[i], xPosition, yPosition);

        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }

    @Override
    public int getID() {
        
        return id;
    }

    
}
