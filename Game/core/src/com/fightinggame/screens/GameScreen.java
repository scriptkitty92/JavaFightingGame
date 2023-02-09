package com.fightinggame.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fightinggame.Main;
import com.fightinggame.hitDetection;

public class GameScreen implements Screen {
	private Stage stage;
	public static final float SPEED1 = 1100;
	public static final float SPEED2 = 160;
	public static final float SPEED3 = 60;
	public float verticalSpeed1;
	public float horizontalSpeed1;
	public float verticalSpeed2;
	public float horizontalSpeed2;
	private ShapeRenderer shapeRenderer;
	Texture img;
	Texture img2;
	Texture img3;
	Texture img4;
	Texture img5;
	Texture img6;
	Texture img7;
	Texture img8;
	int outcome;
	int p1x;
	int p1y;
	int p2x;
	int p2y;
	int centerMass1;
	int centerMass2;
	int punch1;
	int punch2;
	int kick1;
	int kick2;
	int health1 = 100;
	int health2 = 100;
	float healthOutput1;
	float healthOutput2;
	boolean gameOver = false;
	boolean grounded1 = true;
	boolean crouch1 = true;
	boolean grounded2 = true;
	boolean crouch2 = true;
	boolean facingRight1 = true;
	boolean facingRight2 = false;
	float damage = 0;
	float delay1 = 0;
	float delay2 = 0;
	float immune1 = 0;
	float immune2 = 0;
	float stun1 = 0;
	float stun2 = 0;
	int ground = 200;
	int startPos1 = 200;
	int startPos2 = 600;
	Vector<hitDetection> v1 = new Vector<hitDetection>();
	Main game;
	
	public GameScreen(Main game) {
		this.game = game;
		this.stage = new Stage(new FitViewport(Main.SCREENWIDTH, Main.SCREENHEIGHT, game.camera));
		this.shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		img = game.assets.get("Sprites/Grounded.png", Texture.class);
		img2 = game.assets.get("Sprites/Grounded.png", Texture.class);
		img3 = game.assets.get("Sprites/game_background.png", Texture.class);
		img4 = game.assets.get("Sprites/indicatorP1.png", Texture.class);
		img5 = game.assets.get("Sprites/indicatorP2.png", Texture.class);
		img6 = game.assets.get("Sprites/P1Win.png", Texture.class);
		img7 = game.assets.get("Sprites/P2Win.png", Texture.class);
		img8 = game.assets.get("Sprites/Tie.png", Texture.class);
		p1y = ground;
		p1x = startPos1;
		p2y = ground;
		p2x = startPos2;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		timers();
		constants();
		gravity();
			if(delay1 <= 0 && stun1 <= 0 && grounded1 == true) {
				if (facingRight1) {
					img = game.assets.get("Sprites/Grounded.png", Texture.class);
				} else {
					img = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
				}
			}
			if (stun1 > 0) {
				if (facingRight1) {
					img = game.assets.get("Sprites/stun.png", Texture.class);
				} else {
					img = game.assets.get("Sprites/stunLeft.png", Texture.class);
				}
			}
			if(delay2 <= 0 && stun2 <= 0 && grounded2 == true) {
				if (facingRight2) {
					img2 = game.assets.get("Sprites/Grounded.png", Texture.class);
				} else {
					img2 = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
				}
			}
			if (stun2 > 0) {
				if (facingRight2) {
					img2 = game.assets.get("Sprites/stun.png", Texture.class);
				} else {
					img2 = game.assets.get("Sprites/stunLeft.png", Texture.class);
				}
			}
			if (stun1 <= 0) {
				if (Gdx.input.isKeyPressed(Keys.W) && grounded1) {
					if(delay1 <= 0) {
						p1y = jump1(p1y);
					}
				}
				if (Gdx.input.isKeyPressed(Keys.S)) {
					if(delay1 <= 0) {
						crouch1 = true;
						if (facingRight1) {
							img = game.assets.get("Sprites/Crouch.png", Texture.class);
						} else {
							img = game.assets.get("Sprites/CrouchLeft.png", Texture.class);
						}
					}
				}
				if (Gdx.input.isKeyPressed(Keys.A)) {
					if (delay1 <= 0 && grounded1 == true && crouch1 == false){
						p1x -= SPEED2 * Gdx.graphics.getDeltaTime();
					}
					if (grounded1 == false){
						horizontalSpeed1 -= SPEED3 * Gdx.graphics.getDeltaTime();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.D)) {
					if (delay1 <= 0 && grounded1 == true && crouch1 == false){
						p1x += SPEED2 * Gdx.graphics.getDeltaTime();
					}
					if (grounded1 == false){
						horizontalSpeed1 += SPEED3 * Gdx.graphics.getDeltaTime();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.C)) {
					if (delay1 <= 0){
						punch1();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.V)) {
					if (delay1 <= 0){
						kick1();
					}
				}
			}
			if (stun2 <= 0) {
				if (Gdx.input.isKeyPressed(Keys.UP) && grounded2) {
					if(delay2 <= 0) {
						p2y = jump2(p2y);
					}
				}
				if (Gdx.input.isKeyPressed(Keys.DOWN)) {
					if(delay2 <= 0) {
						crouch2 = true;
						if (facingRight2) {
							img2 = game.assets.get("Sprites/Crouch.png", Texture.class);
						} else {
							img2 = game.assets.get("Sprites/CrouchLeft.png", Texture.class);
						}
					}
				}
				if (Gdx.input.isKeyPressed(Keys.LEFT)) {
					if (delay2 <= 0 && grounded2 == true && crouch2 == false){
						p2x -= SPEED2 * Gdx.graphics.getDeltaTime();
					}
					if (grounded2 == false){
						horizontalSpeed2 -= SPEED3 * Gdx.graphics.getDeltaTime();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
					if (delay2 <= 0 && grounded2 == true && crouch2 == false){
						p2x += SPEED2 * Gdx.graphics.getDeltaTime();
					}
					if (grounded2 == false){
						horizontalSpeed2 += SPEED3 * Gdx.graphics.getDeltaTime();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.M)) {
					if (delay2 <= 0){
						punch2();
					}
				}
				if (Gdx.input.isKeyPressed(Keys.N)) {
					if (delay2 <= 0){
						kick2();
					}
				}
			}
			speedCheck();
			sideCollision();
			bodyCollision();
		healthCalculation();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(img3, 0, 0);
		game.batch.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(game.camera.viewportWidth/8 + game.camera.viewportWidth/16, game.camera.viewportHeight/8,game.camera.viewportWidth/4,game.camera.viewportHeight/16);
		shapeRenderer.rect(game.camera.viewportWidth/2 + game.camera.viewportWidth/8, game.camera.viewportHeight/8,game.camera.viewportWidth/4,game.camera.viewportHeight/16);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(game.camera.viewportWidth/8 + game.camera.viewportWidth/16, game.camera.viewportHeight/8 , (game.camera.viewportWidth/4) * healthOutput1 , game.camera.viewportHeight/16);
		shapeRenderer.rect(game.camera.viewportWidth/2 + game.camera.viewportWidth/8, game.camera.viewportHeight/8 , (game.camera.viewportWidth/4) * healthOutput2 , game.camera.viewportHeight/16); 
		shapeRenderer.end();
		game.batch.begin();
		game.batch.draw(img, p1x, p1y);
		game.batch.draw(img2, p2x, p2y);
		game.batch.draw(img4, p1x + 90, p1y + 280);
		game.batch.draw(img5, p2x + 90, p2y + 280);
		if (outcome == 1) {
			game.batch.draw(img7, 0, 0);
		}
		if (outcome == 2) {
			game.batch.draw(img6, 0, 0);
		}
		if (outcome == 3) {
			game.batch.draw(img8, 0, 0);
		}
		game.batch.end();
		if (health1 <= 0 && health2 > 0) {
			outcome = 1;
		}
		else if (health2 <= 0 && health1 > 0) {
			outcome = 2;
		} 
		else if (health2 <= 0 && health1 <= 0){
			outcome = 3;
		}
		
	}
	public void constants() {
		p1y += verticalSpeed1;
		p1x += horizontalSpeed1;
		p2y += verticalSpeed2;
		p2x += horizontalSpeed2;
		crouch1 = false;
		crouch2 = false;
		p1y = Grounded1(p1y);
		p2y = Grounded2(p2y);
	}
	public void healthCalculation() {
		healthOutput1 = ((float)health1)/100;
		healthOutput2 = ((float)health2)/100;
	}
	public void bodyCollision() {
		centerMass1 = p1x+150;
		centerMass2 = p2x+150;
		if (p2x + 150 < centerMass1 && grounded1 == true) { 
			facingRight1 = false;
		} else if (p2x + 150 > centerMass1 && grounded1 == true) {
			facingRight1 = true;
		}
		if (p1x + 150 < centerMass2 && grounded2 == true) { 
			facingRight2 = false;
		} else if (p1x + 150 > centerMass2 && grounded2 == true) {
			facingRight2 = true;
		}
	}
	public int Grounded1(int height) {
		if(height < ground && stun1 <= 0 ) {
			height = ground;
			verticalSpeed1 = 0;
			horizontalSpeed1 = 0;
			grounded1 = true;
			if(delay1 <= 0) {
				if (facingRight1) {
					img = game.assets.get("Sprites/Grounded.png", Texture.class);
				} else {
					img = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
				}
			}
		}
		if(height == ground && stun1 > 0) {
			stun1 = stun1/2;
		}
		if(height < ground && stun1 > 0) {
			verticalSpeed1 = -(verticalSpeed1/(3*4))*stun1;
			stun1 = stun1/2;
			p1y = 1;
			if (facingRight1) {
				img = game.assets.get("Sprites/Grounded.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
			}
		}
		return height;
	}
	public int Grounded2(int height) {
		if(height < ground && stun2 <= 0 ) {
			height = ground;
			verticalSpeed2 = 0;
			horizontalSpeed2 = 0;
			grounded2 = true;
			if(delay2 <= 0) {
				if (facingRight2) {
					img2 = game.assets.get("Sprites/Grounded.png", Texture.class);
				} else {
					img2 = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
				}
			}
		}
		if(height == ground && stun2 > 0) {
			stun2 = stun2/2;
		}
		if(height < ground && stun2 > 0) {
			verticalSpeed2 = -(verticalSpeed2/(3*4))*stun2;
			stun2 = stun2/2;
			p2y = ground + 1;
			if (facingRight2) {
				img2 = game.assets.get("Sprites/Grounded.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/GroundedLeft.png", Texture.class);
			}
		}
		return height;
	}
	public int jump1(int height) {
		height = ground + 1;
		verticalSpeed1 += SPEED1 * Gdx.graphics.getDeltaTime();
		grounded1 = false;
		if (facingRight1) {
			img = game.assets.get("Sprites/Air.png", Texture.class);
		} else {
			img = game.assets.get("Sprites/AirLeft.png", Texture.class);
		}
		return height;
	}
	public int jump2(int height) {
		height = ground + 1;
		verticalSpeed2 += SPEED1 * Gdx.graphics.getDeltaTime();
		grounded2 = false;
		if (facingRight2) {
			img2 = game.assets.get("Sprites/Air.png", Texture.class);
		} else {
			img2 = game.assets.get("Sprites/AirLeft.png", Texture.class);
		}
		return height;
	}
	public void gravity() {
		if (grounded1 == false && p1y >= ground) {
			verticalSpeed1 += -1;
		}
		if (grounded2 == false && p2y >= ground) {
			verticalSpeed2 += -1;
		}
	}
	public void speedCheck() {
		if (horizontalSpeed1 > 5) {
			horizontalSpeed1 = 5;
		}
		if (horizontalSpeed1 < -5) {
			horizontalSpeed1 = -5;
		}
		if (horizontalSpeed2 > 5) {
			horizontalSpeed2 = 5;
		}
		if (horizontalSpeed2 < -5) {
			horizontalSpeed2 = -5;
		}
	}
	public void sideCollision(){
		if (p1x <= 0 && stun1 <= 0) {
			p1x = 1;
		}
		if (p1x <= 0 && stun1 > 0) {
			p1x = 1;
			horizontalSpeed1 = -horizontalSpeed1;
		}
		if (p1x >= stage.getWidth()-300 && stun1 <= 0) {
			p1x = (int) (stage.getWidth()-300);
		}
		if (p1x >= stage.getWidth()-300 && stun1 > 0) {
			p1x = (int) (stage.getWidth()-300);
			horizontalSpeed1 = -horizontalSpeed1;
		}
		if (p2x <= 0 && stun2 <= 0) {
			p2x = 1;
		}
		if (p2x <= 0 && stun2 > 0) {
			p2x = 1;
			horizontalSpeed2 = -horizontalSpeed2;
		}
		if (p2x >= stage.getWidth()-300 && stun2 <= 0) {
			p2x = (int) (stage.getWidth()-300);
		}
		if (p2x >= stage.getWidth()-300 && stun2 > 0) {
			p2x = (int) (stage.getWidth()-300);
			horizontalSpeed2 = -horizontalSpeed2;
		}
	}
	public void punch1() {
		delay1 = 0.2f;
		punch1 = 1;
		if (immune2 <= 0) {
			if(hitDetection.doesItHit(punch1, kick1, facingRight1, p1x, p1y, p2x, p2y, grounded1, crouch1, grounded2, crouch2)) {
				stun2 = hitDetection.getStunTime();
				immune2 = hitDetection.getImmuneTime();
				verticalSpeed2 += hitDetection.getVertical();
				horizontalSpeed2 += hitDetection.getHorizontal();
				health2 -= hitDetection.getDamage();
				p2y += 1;
				grounded2 = false;
				System.out.println("hit!");
			}
			hitDetection.clear();
		}
		
		hitDetection.clear();
		punch1 = 0;
		if (grounded1 == true && crouch1 == false) {
			if (facingRight1) {
				img = game.assets.get("Sprites/GroundedPunch.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/GroundedPunchLeft.png", Texture.class);
			}
		} else if (grounded1 == true && crouch1 == true){
			if (facingRight1) {
				img = game.assets.get("Sprites/CrouchPunch.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/CrouchPunchLeft.png", Texture.class);
			}
		} else {
			if (facingRight1) {
				img = game.assets.get("Sprites/AirPunch.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/AirPunchLeft.png", Texture.class);
			}
		}
	}
	public void punch2() {
		delay2 = 0.2f;
		punch2 = 1;
		if (immune1 <= 0) {
			if(hitDetection.doesItHit(punch2, kick2, facingRight2, p2x, p2y, p1x, p1y, grounded2, crouch2, grounded1, crouch1)) {
				stun1 = hitDetection.getStunTime();
				immune1 = hitDetection.getImmuneTime();
				verticalSpeed1 += hitDetection.getVertical();
				horizontalSpeed1 += hitDetection.getHorizontal();
				p1y += 1;
				grounded1 = false;
				health1 -= hitDetection.getDamage();
				System.out.println("hit!");
			}
			hitDetection.clear();
		}
		
		hitDetection.clear();
		punch2 = 0;
		if (grounded2 == true && crouch2 == false) {
			if (facingRight2) {
				img2 = game.assets.get("Sprites/GroundedPunch.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/GroundedPunchLeft.png", Texture.class);
			}
		} else if (grounded2 == true && crouch2 == true){
			if (facingRight2) {
				img2 = game.assets.get("Sprites/CrouchPunch.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/CrouchPunchLeft.png", Texture.class);
			}
		} else {
			if (facingRight2) {
				img2 = game.assets.get("Sprites/AirPunch.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/AirPunchLeft.png", Texture.class);
			}
		}
	}
	public void kick1() {
		delay1 = 0.4f;
		kick1 = 1;
		if(hitDetection.doesItHit(punch1, kick1, facingRight1, p1x, p1y, p2x, p2y, grounded1, crouch1, grounded2, crouch2)) {
			stun2 = hitDetection.getStunTime();
			immune2 = hitDetection.getImmuneTime();
			verticalSpeed2 += hitDetection.getVertical();
			horizontalSpeed2 += hitDetection.getHorizontal();
			p2y += 1;
			grounded2 = false;
			health2 -= hitDetection.getDamage();
			System.out.println("hit!");
		}
		hitDetection.clear();
		kick1 = 0;
		if (grounded1 == true && crouch1 == false) {
			if (facingRight1) {
				img = game.assets.get("Sprites/GroundedKick.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/GroundedKickLeft.png", Texture.class);
			}
		} else if (grounded1 == true && crouch1 == true){
			if (facingRight1) {
				img = game.assets.get("Sprites/CrouchKick.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/CrouchKickLeft.png", Texture.class);
			}
		} else {
			if (facingRight1) {
				img = game.assets.get("Sprites/AirKick.png", Texture.class);
			} else {
				img = game.assets.get("Sprites/AirKickLeft.png", Texture.class);
			}
		}
	}
	public void kick2() {
		delay2 = 0.4f;
		kick2 = 1;
		if(hitDetection.doesItHit(punch2, kick2, facingRight2, p2x, p2y, p1x, p1y, grounded2, crouch2, grounded1, crouch1)) {
			stun1 = hitDetection.getStunTime();
			immune1 = hitDetection.getImmuneTime();
			verticalSpeed1 += hitDetection.getVertical();
			horizontalSpeed1 += hitDetection.getHorizontal();
			p1y += 1;
			grounded1 = false;
			health1 -= hitDetection.getDamage();
			System.out.println("hit!");
		}
		hitDetection.clear();
		kick2 = 0;
		if (grounded2 == true && crouch2 == false) {
			if (facingRight2) {
				img2 = game.assets.get("Sprites/GroundedKick.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/GroundedKickLeft.png", Texture.class);
			}
		} else if (grounded2 == true && crouch2 == true){
			if (facingRight2) {
				img2 = game.assets.get("Sprites/CrouchKick.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/CrouchKickLeft.png", Texture.class);
			}
		} else {
			if (facingRight2) {
				img2 = game.assets.get("Sprites/AirKick.png", Texture.class);
			} else {
				img2 = game.assets.get("Sprites/AirKickLeft.png", Texture.class);
			}
		}
	}
	public void timers() {
		if (delay1 > 0) {
			delay1 -= Gdx.graphics.getDeltaTime();
		}
		if (immune1 > 0) {
			immune1 -= Gdx.graphics.getDeltaTime();
		}
		if (stun1 > 0) {
			stun1 -= Gdx.graphics.getDeltaTime();
		}
		if (delay2 > 0) {
			delay2 -= Gdx.graphics.getDeltaTime();
		}
		if (immune2 > 0) {
			immune2 -= Gdx.graphics.getDeltaTime();
		}
		if (stun2 > 0) {
			stun2 -= Gdx.graphics.getDeltaTime();
		}
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, false);
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
		shapeRenderer.dispose();
		
	}

}
