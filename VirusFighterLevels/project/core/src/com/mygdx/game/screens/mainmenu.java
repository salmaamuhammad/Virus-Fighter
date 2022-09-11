package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.virusgame;

public class mainmenu implements Screen{
	private final static int btn_width = 300;
	private final static int btn_height = 100;
	virusgame game;
	Texture exitbtn,playbtn,exitbtnin,playbtnin,title,icon1;
	
	public mainmenu(virusgame game) {
		this.game = game;
		
		
	}
	@Override
	public void show() {
		exitbtn = new Texture("exit_button_active.png");
		playbtn = new Texture("play_button_active.png");
		exitbtnin = new Texture("exit_button_inactive.png");
		playbtnin = new Texture("play_button_inactive.png");
		title = new Texture("resotedream.png");
		icon1 = new Texture("icons8-radioactive-96.png");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(title,100,600,800,150);
		
		if(Gdx.input.getX() > 350 && Gdx.input.getX() < Gdx.graphics.getWidth() - 350 && Gdx.graphics.getHeight() - Gdx.input.getY() < 400 + btn_height && Gdx.graphics.getHeight() - Gdx.input.getY() > 400) {
			game.batch.draw(playbtn, 350, 400,btn_width,btn_height);
			game.batch.draw(icon1, 250, 410,80, 80);
			if(Gdx.input.isTouched())
				game.setScreen(new CharactersScreen(game));
		}
		else {
			game.batch.draw(playbtnin, 350, 400,btn_width,btn_height);
		}
		
		if(Gdx.input.getX() > 340 && Gdx.input.getX() < Gdx.graphics.getWidth() - 340 && Gdx.graphics.getHeight() - Gdx.input.getY() < 200 + btn_height && Gdx.graphics.getHeight() - Gdx.input.getY() > 200) {
			game.batch.draw(exitbtn, 340, 200,btn_width,btn_height);
			game.batch.draw(icon1, 240, 210,80, 80);
			if(Gdx.input.isTouched())
				Gdx.app.exit();
		}
		else {
			game.batch.draw(exitbtnin, 340, 200,btn_width,btn_height);
		}
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
	}

	
}
