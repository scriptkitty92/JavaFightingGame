package com.fightinggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.fightinggame.screens.GameScreen;
import com.fightinggame.screens.LoadingScreen;
import com.fightinggame.screens.LoadingScreen2;
import com.fightinggame.screens.MainMenuScreen;

public class Main extends Game {
	
	public static final int SCREENWIDTH = 1080;
	public static final int SCREENHEIGHT = 720;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	public BitmapFont font24;
	
	public AssetManager assets;
	
	public LoadingScreen loadingScreen;
	public LoadingScreen2 loadingScreen2;
	public MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;
	
	@Override
	public void create () {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		
		initFonts();
		
		mainMenuScreen = new MainMenuScreen(this);
		loadingScreen = new LoadingScreen(this);
		loadingScreen2 = new LoadingScreen2(this);
		gameScreen = new GameScreen(this);
		this.setScreen(loadingScreen);
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font24.dispose();
		assets.dispose();
		loadingScreen.dispose();
		loadingScreen2.dispose();
		mainMenuScreen.dispose();
		gameScreen.dispose();
	}
	
	private void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		params.size = 24;
		params.color = Color.BLACK;
		font24 = generator.generateFont(params);
	}
}
