package Virus_fighter;

import Virus_fighter.menu.GameOverScreen;
import Virus_fighter.menu.VictoryScreen;
import Virus_fighter.HUD.VirusFighterHUD;
import Virus_fighter.Tools.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class GameplayScreen extends ScreenAdapter {
    
    private ExtendViewport gameplayViewport;
    //The selected fighter selected from character screen
    private String selectedFighter;
    //Used for drawing the sprites(enemies,characters,bullets,etc....)
    private SpriteBatch batch;
    private long levelEndOverlayStartTime;
    private Level level;
    //Camera focused on the player.
    private ChaseCam chaseCam;
    private VirusFighterHUD hud;
    private VirusFighterGame game;
    
    
    public GameplayScreen(String selectedFighter, VirusFighterGame game)
    {
        this.selectedFighter=selectedFighter;
        this.game=game;
    }
    
    @Override
    public void show() {
        //Manages the loading and storing of assets during the runtime of the game
        AssetManager am = new AssetManager();
        Assets.instance.init(am);
        batch = new SpriteBatch();
        gameplayViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        //loads level data
        level = LevelLoader.load("Level1", gameplayViewport,selectedFighter);
        chaseCam = new ChaseCam(gameplayViewport.getCamera(), level.getFighter());
        hud = new VirusFighterHUD();
        
        startLevel();
    }
    
    @Override
    public void resize(int width, int height) {
        gameplayViewport.update(width, height, true);
        hud.viewport.update(width, height, true);
        level.viewport.update(width, height, true);
        chaseCam.camera = level.viewport.getCamera();
    }
    
    @Override
    public void dispose() {
        Assets.instance.dispose();
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        level.update(delta);
        chaseCam.update(delta);
        gameplayViewport.apply();
        level.render(batch);
        hud.render(batch, level.getFighter().getHEALTH(), level.score, selectedFighter);
        
       renderLevelEndOverlays();
    
       
    }
    private void startLevel() {
        chaseCam.camera = level.viewport.getCamera();
        chaseCam.target = level.getFighter();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    //Renders the game over/victory screen after a
    // brief delay to give the app time to play death animation or finish a running animation
    public void renderLevelEndOverlays() {
        if (level.victory) {
            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();
                if (Utils.secondsSince(levelEndOverlayStartTime) > 1) {
                    game.setScreen(new VictoryScreen(game, level));
                }
            }
        }
    
        if (level.gameOver) {
            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();
            }
            if (Utils.secondsSince(levelEndOverlayStartTime) > 1) {
                levelEndOverlayStartTime = 0;
                game.setScreen(new GameOverScreen(game, level));
            }
        }
    }
    
}

