package Virus_fighter.menu;

import Virus_fighter.GameplayScreen;
import Virus_fighter.VirusFighterGame;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class CharactersScreen implements Screen{
    
    private VirusFighterGame game;
    private Texture character1,character2,character3,health1,health2,health3,stamina1,stamina2,stamina3,healthicon,choose_inactive;
    //This is used to determine the character selected from the menu
    //So it can be determined in the rest of the project if needed
    private String Chosen;
    
    public CharactersScreen(VirusFighterGame game) {
        this.game = game;
    }
    
    @Override
    public void show() {
        character1 = new Texture("desktop/menuAssets/1_police_Idle_000.png");
        character2 = new Texture("desktop/menuAssets/2_police_Idle_000.png");
        character3 = new Texture("desktop/menuAssets/3_police_Idle_000.png");
        
        health1 = new Texture("desktop/menuAssets/quite-magical-regular120.png");
        health2 = new Texture("desktop/menuAssets/quite-magical-regular100.png");
        health3 = new Texture("desktop/menuAssets/quite-magical-regular.png");
        
        stamina1 = new Texture("desktop/menuAssets/quite-magical-regular1.png");
        stamina2 = new Texture("desktop/menuAssets/quite-magical-regular2.png");
        stamina3 = new Texture("desktop/menuAssets/quite-magical-regular3.png");
        
        healthicon = new Texture("desktop/menuAssets/icons8-heart-health-80.png");
        
        choose_inactive = new Texture("desktop/menuAssets/choosein.png");
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        
        
        game.batch.draw(character1,100,600,100,150);
        game.batch.draw(character2,100,350,100,150);
        game.batch.draw(character3,100,80,100,150);
        
        game.batch.draw(health1,140,600,160,50);
        game.batch.draw(health2,140,350,160,50);
        game.batch.draw(health3,140,80,160,50);
        
        game.batch.draw(stamina1,370,600,220,50);
        game.batch.draw(stamina2,370,350,220,50);
        game.batch.draw(stamina3,370,80,200,50);
        
        
        if(Gdx.input.getX() > 140 && Gdx.input.getX() < Gdx.graphics.getWidth() - 140 && Gdx.graphics.getHeight() - Gdx.input.getY() < 600 + 50 && Gdx.graphics.getHeight() - Gdx.input.getY() > 600) {
            game.batch.draw(healthicon,310,600,50,50);
            game.batch.draw(choose_inactive,780,600,160,50);
            if(Gdx.input.isTouched()) {
                Chosen = "character1";
                game.setScreen(new GameplayScreen(Chosen,game));
            }
        }else if(Gdx.input.getX() > 140 && Gdx.input.getX() < Gdx.graphics.getWidth() - 140 && Gdx.graphics.getHeight() - Gdx.input.getY() < 350 + 50 && Gdx.graphics.getHeight() - Gdx.input.getY() > 350) {
            game.batch.draw(healthicon,310,350,50,50);
            game.batch.draw(choose_inactive,780,350,160,50);
            if(Gdx.input.isTouched()) {
                Chosen = "character2";
                game.setScreen(new GameplayScreen(Chosen,game));
            }
        }else if(Gdx.input.getX() > 140 && Gdx.input.getX() < Gdx.graphics.getWidth() - 140 && Gdx.graphics.getHeight() - Gdx.input.getY() < 80 + 50 && Gdx.graphics.getHeight() - Gdx.input.getY() > 80) {
            game.batch.draw(healthicon,310,80,50,50);
            game.batch.draw(choose_inactive,780,80,160,50);
            if(Gdx.input.isTouched()) {
                Chosen = "character3";
                game.setScreen(new GameplayScreen(Chosen,game));
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
