package Virus_fighter.HUD;

import Virus_fighter.Tools.Constants;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//An overlay that displays the score and health of the character
public class VirusFighterHUD {
    
    public final Viewport viewport;
    private final BitmapFont font;
    
    public VirusFighterHUD() {
        this.viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(1);
    }
    
    public void render(SpriteBatch batch, int health, int score, String selectedFighter) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        font.setColor(Color.BLACK);
        font.draw(batch, "SCORE : "+score, Constants.HUD_MARGIN+350, viewport.getWorldHeight()-20); //+ Constants.HUD_MARGIN);
        font.draw(batch, "HEALTH : "+health, Constants.HUD_MARGIN, viewport.getWorldHeight() - Constants.HUD_MARGIN);
        batch.end();
        
    }
}
