package com.game.states;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
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

public class Menu extends BasicGameState {
    
    private Music music;
    private boolean musicIsPlayed;
    
    private Image background;
    
    private int id;
    
    public Menu(int id) {
        this.id = id;
    }
    
    private Input input;
    private AngelCodeFont font;
    
 //   private GradientFill fill;
    
    private String[] options = new String[] {
        "Play", "Instructions", "Quit"
    };
    
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        music = new Music("Music/Menu.wav");
        musicIsPlayed = false;
        background = new Image("Images/MenuBackgroundV3.png");
        
        input = container.getInput();
        font = (AngelCodeFont) container.getDefaultFont();
        
       // music.setVolume(0.5f);
        //music.loop();
//        fill = new GradientFill(100, 100, new Color(1, 1, 1, 0.5F), 200, 100, new Color(.5F, .5F, .5F, .5F));
        
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
                    if (options[i] == "Play" && input.isMousePressed(0)) {
                        game.enterState(7, new FadeOutTransition(), new FadeInTransition());
                    }
                    else if (options[i] == "Instructions" && input.isMousePressed(0)) {
                        game.enterState(5, new FadeOutTransition(), new FadeInTransition());
                    }
                    else if (options[i] == "Quit" && input.isMousePressed(0)) {
                        container.exit();
                    }
                }
            }
            g.drawString(options[i], xPosition, yPosition);
            
        }
        
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!musicIsPlayed) {
            music.loop(1, 0.5f);
            musicIsPlayed = true; 
        }
    }

    public int getID() {
        return id;
    }

}
