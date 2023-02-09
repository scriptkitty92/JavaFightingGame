package com.fightinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.fightinggame.Main;

public class LoadingScreen2 implements Screen {
	
	Main game;
	
	private ShapeRenderer shapeRenderer;
	
	private float progress;
	public LoadingScreen2(final Main game) {
		this.game = game;
		this.shapeRenderer = new ShapeRenderer();
		
		
	}
	
	private void queueAssets() {
		game.assets.load("Sprites/Grounded.png", Texture.class);
		game.assets.load("Sprites/Air.png", Texture.class);
		game.assets.load("Sprites/indicatorP1.png", Texture.class);
		game.assets.load("Sprites/indicatorP2.png", Texture.class);
		game.assets.load("Sprites/GroundedKick.png", Texture.class);
		game.assets.load("Sprites/GroundedPunch.png", Texture.class);
		game.assets.load("Sprites/AirKick.png", Texture.class);
		game.assets.load("Sprites/AirPunch.png", Texture.class);
		game.assets.load("Sprites/CrouchPunch.png", Texture.class);
		game.assets.load("Sprites/CrouchKick.png", Texture.class);
		game.assets.load("Sprites/Crouch.png", Texture.class);
		game.assets.load("Sprites/stun.png", Texture.class);
		game.assets.load("Sprites/GroundedLeft.png", Texture.class);
		game.assets.load("Sprites/AirLeft.png", Texture.class);
		game.assets.load("Sprites/GroundedKickLeft.png", Texture.class);
		game.assets.load("Sprites/GroundedPunchLeft.png", Texture.class);
		game.assets.load("Sprites/AirKickLeft.png", Texture.class);
		game.assets.load("Sprites/AirPunchLeft.png", Texture.class);
		game.assets.load("Sprites/CrouchPunchLeft.png", Texture.class);
		game.assets.load("Sprites/CrouchKickLeft.png", Texture.class);
		game.assets.load("Sprites/CrouchLeft.png", Texture.class);
		game.assets.load("Sprites/stunLeft.png", Texture.class);
		game.assets.load("Sprites/game_background.png", Texture.class);
		game.assets.load("Sprites/Tie.png", Texture.class);
		game.assets.load("Sprites/P1Win.png", Texture.class);
		game.assets.load("Sprites/P2Win.png", Texture.class);
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
			game.setScreen(game.gameScreen);
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
