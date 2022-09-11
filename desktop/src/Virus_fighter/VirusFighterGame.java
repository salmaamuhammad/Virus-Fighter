package Virus_fighter;

import Virus_fighter.menu.mainmenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Code Entry point, calls the main menu screen
public class VirusFighterGame extends Game {
    public SpriteBatch batch;
    
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        setScreen(new mainmenu(this));
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }
}
