package com.fightinggame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.fightinggame.Main;

public class MainMenuScreen implements Screen {
	private Stage stage;
	private Skin skin;
	Main game;
	
	private TextButton buttonPlay, buttonExit;
	

	private Image mainMenuImg;
	
	public MainMenuScreen(Main game) {
		this.game = game;
		this.stage = new Stage(new FitViewport(Main.SCREENWIDTH, Main.SCREENHEIGHT, game.camera));
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		
		this.skin = new Skin();
		this.skin.addRegions(game.assets.get("main_menu_ui/uiskin.atlas", TextureAtlas.class));
		this.skin.add("default-font", game.font24);
		this.skin.load(Gdx.files.internal("main_menu_ui/uiskin.json"));
		
		
		
		Texture mainTex = game.assets.get("main_menu_ui/main_menu_background.png", Texture.class);
		mainMenuImg = new Image(mainTex);
		mainMenuImg.setOrigin(mainMenuImg.getWidth() / 2,mainMenuImg.getHeight() / 2 );
		mainMenuImg.setPosition(stage.getWidth() / 2 - mainMenuImg.getWidth() / 2,stage.getHeight() / 2 - mainMenuImg.getHeight() / 2);
		mainMenuImg.addAction(sequence(alpha(0f), fadeIn(1.5f)));
		
		initButtons();
		
		
		
		stage.addActor(mainMenuImg);
		stage.addActor(buttonPlay);
		stage.addActor(buttonExit);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		stage.draw();
		
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, false);
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
	
	private void initButtons() {
		buttonPlay = new TextButton("Play", skin, "default");
		buttonPlay.setPosition(stage.getWidth() / 2 - stage.getWidth() / 6 - 75, stage.getHeight() / 2 - stage.getHeight() / 3);
		buttonPlay.setSize(150, 100);
		buttonPlay.addAction(sequence(alpha(0f), fadeIn(1.5f)));
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.loadingScreen2);
			}
		});
		
		buttonExit = new TextButton("Exit", skin, "default");
		buttonExit.setPosition(stage.getWidth() / 2 + stage.getWidth() / 6 - 75, stage.getHeight() / 2 - stage.getHeight() / 3);
		buttonExit.setSize(150, 100);
		buttonExit.addAction(sequence(alpha(0f), fadeIn(1.5f)));
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}
	
}
