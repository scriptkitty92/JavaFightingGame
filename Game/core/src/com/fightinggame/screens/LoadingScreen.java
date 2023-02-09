package com.fightinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.fightinggame.Main;

public class LoadingScreen implements Screen {
	
	Main game;
	
	private ShapeRenderer shapeRenderer;
	
	private float progress;
	public LoadingScreen(final Main game) {
		this.game = game;
		this.shapeRenderer = new ShapeRenderer();
		
		
	}
	
	private void queueAssets() {
		game.assets.load("main_menu_ui/main_menu_background.png", Texture.class);
		game.assets.load("main_menu_ui/uiskin.atlas", TextureAtlas.class);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.progress = 0f;
		
		queueAssets();
	}
	
	
	private void update(float delta) {
		progress = MathUtils.lerp(progress, game.assets.getProgress(), .1f);
		if(game.assets.update() && progress >= game.assets.getProgress() - .001f) {
			game.setScreen(game.mainMenuScreen);
		}
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(game.camera.viewportWidth/2 - game.camera.viewportWidth/4, game.camera.viewportHeight/2 - game.camera.viewportHeight/4,game.camera.viewportWidth/2,(game.camera.viewportHeight/8)/2);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(game.camera.viewportWidth/2 - game.camera.viewportWidth/4, game.camera.viewportHeight/2 - game.camera.viewportHeight/4, (game.camera.viewportWidth/2) * progress , (game.camera.viewportHeight/8)/2); 
		shapeRenderer.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shapeRenderer.dispose();
		
	}
	
	

}
