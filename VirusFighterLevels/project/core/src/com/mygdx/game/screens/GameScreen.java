package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.virusgame;

public class GameScreen implements Screen{
	
	Texture img;
	float x,y;
	
	virusgame game;
	Texture Chosen;
	public GameScreen(virusgame game,Texture Chosen) {
		this.game = game;
		this.Chosen = Chosen;
	}
	

	@Override
	public void show() {
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(Chosen,100,600,100,150);
		if(Gdx.input.isKeyPressed(Keys.BACKSPACE))
			game.setScreen(new CharactersScreen(game));
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
