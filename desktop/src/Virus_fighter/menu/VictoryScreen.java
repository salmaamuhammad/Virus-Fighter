package Virus_fighter.menu;

import Virus_fighter.Level;
import Virus_fighter.VirusFighterGame;
import Virus_fighter.Tools.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

//The screen in case of victory,
//this class renders the elements of that screen
public class VictoryScreen implements Screen {
    private VirusFighterGame game;
    private Level level;
    private Texture youwin;
    private Texture Continue;
    private final BitmapFont font;
    
    public VictoryScreen(VirusFighterGame game, Level level)
    {
        this.game=game;
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        this.level=level;
    }
    @Override
    public void show()
    {
        youwin = new Texture("desktop/menuAssets/youwin.png");
        Continue = new Texture("desktop/menuAssets/Continue.png");
    }
    
    @Override
    public void render(float delta) {
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        
        game.batch.draw(youwin, 200, 300, 600, 600);
        font.setColor(Color.BLACK);
        font.draw(game.batch,"Score : "+level.getScore(), 500
                , 400, 0, Align.center, false);
        
        game.batch.draw(Continue, 680, 50, 320, 95);
        if(Gdx.input.getX() > 680 && Gdx.input.getX() < Gdx.graphics.getWidth()-180
                && Gdx.graphics.getHeight() - Gdx.input.getY() < 50+95
                && Gdx.graphics.getHeight() - Gdx.input.getY() > 50)
        {
            if(Gdx.input.isTouched())
            {
                game.setScreen(new mainmenu(game));
            }
        }
        game.batch.end();
        
    }
    
    @Override
    public void resize(int width, int height) {
    
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void hide() {
    
    }
    
    @Override
    public void dispose() {
    
    }
}
