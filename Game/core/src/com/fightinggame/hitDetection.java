package com.fightinggame;

public class hitDetection {
	
	public static int verticalSpeed;
	public static int horizontalSpeed;
	static int attackCoordx;
	static int attackCoordy;
	static int attackSizex;
	static int attackSizey;
	static int defenseSizey;
	static float stunTime;
	static float immuneTime;
	static int damage;
	public static boolean hitDetected = false;
	static String type = "";
	
	public static boolean doesItHit(int punch, int kick, boolean facingRight,int aggressiveX, int aggressiveY, int defensiveX, int defensiveY, boolean aggresiveGrounded, boolean aggresiveCrouch, boolean defensiveGrounded, boolean defensiveCrouch) {
		type = hitType(punch,kick,facingRight,aggresiveCrouch,aggresiveGrounded);
		attackCoords(aggressiveX, aggressiveY);
		hitboxSize(defensiveCrouch);
		hitDetected = hitboxDetection(facingRight, defensiveX, defensiveY);
		return hitDetected;
	}
	
	public static String hitType(int punch, int kick, boolean facingRight, boolean aggresiveCrouch, boolean aggresiveGrounded){
		String result = "";
		if (punch == 1) {
			if(aggresiveCrouch) {
				if(facingRight) {
					result = "punchCrouchRight";
				} else result = "punchCrouchLeft";
			} else if (aggresiveGrounded){
				if(facingRight) {
					result = "punchRight";
				} else result = "punchLeft";
			} else {
				if(facingRight) {
					result = "punchAirRight";
				} else result = "punchAirLeft";
			}
		}
		if (kick == 1) {
			if(aggresiveCrouch) {
				if(facingRight) {
					result = "kickCrouchRight";
				} else result = "kickCrouchLeft";
			} else if (aggresiveGrounded){
				if(facingRight) {
					result = "kickRight";
				} else result = "kickLeft";
			} else {
				if(facingRight) {
					result = "kickAirRight";
				} else result = "kickAirLeft";
			}
		}
		return result;
	}
	public static void attackCoords(int aggressiveX, int aggressiveY) {
		if (type.equals("punchCrouchRight")) {
			attackCoordx = aggressiveX + 228;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 72;
			attackSizey= attackCoordy + 80;
			horizontalSpeed = 5;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("punchCrouchLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 72;
			attackSizey= attackCoordy + 80;
			horizontalSpeed = -5;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("punchRight")) {
			attackCoordx = aggressiveX + 238;
			attackCoordy = aggressiveY + 157;
			attackSizex= attackCoordx + 60;
			attackSizey= attackCoordy + 50;
			horizontalSpeed = 5;
			verticalSpeed = 5;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
			
		}
		if (type.equals("punchLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 60;
			attackSizey= attackCoordy + 50;
			horizontalSpeed = -5;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("punchAirRight")) {
			attackCoordx = aggressiveX + 196;
			attackCoordy = aggressiveY + 197;
			attackSizex= attackCoordx + 60;
			attackSizey= attackCoordy + 60;
			horizontalSpeed = 20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("punchAirLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 60;
			attackSizey= attackCoordy + 60;
			horizontalSpeed = -20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("kickCrouchRight")) {
			attackCoordx = aggressiveX + 224;
			attackCoordy = aggressiveY + 10;
			attackSizex= attackCoordx + 80;
			attackSizey= attackCoordy + 45;
			horizontalSpeed = -20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("kickCrouchLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 80;
			attackSizey= attackCoordy + 45;
			horizontalSpeed = 20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 0.6f;
			damage = 10;
		}
		if (type.equals("kickRight")) {
			attackCoordx = aggressiveX + 232;
			attackCoordy = aggressiveY + 157;
			attackSizex= attackCoordx + 70;
			attackSizey= attackCoordy + 60;
			horizontalSpeed = 20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 1.6f;
			damage = 10;
		}
		if (type.equals("kickLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 70;
			attackSizey= attackCoordy + 60;
			horizontalSpeed = -20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 1.6f;
			damage = 10;
		}
		if (type.equals("kickAirRight")) {
			attackCoordx = aggressiveX + 248;
			attackCoordy = aggressiveY + 163;
			attackSizex= attackCoordx + 50;
			attackSizey= attackCoordy + 70;
			horizontalSpeed = 20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 1.6f;
			damage = 10;
		}
		if (type.equals("kickAirLeft")) {
			attackCoordx = aggressiveX;
			attackCoordy = aggressiveY + 204;
			attackSizex= attackCoordx + 50;
			attackSizey= attackCoordy + 70;
			horizontalSpeed = -20;
			verticalSpeed = 20;
			stunTime = 3;
			immuneTime = 1.6f;
			damage = 10;
		}
	}

	public static void hitboxSize(boolean Crouch) {
		if (Crouch) {
			defenseSizey = 150;
		} else {
			defenseSizey = 300;
		}
	}
	public static boolean hitboxDetection(boolean facingRight, int defensiveX, int defensiveY) {
		for(int i = attackCoordx; i < attackSizex; i++) {
			for(int j = attackCoordy; j < attackSizey; j++) {
				if (i >= defensiveX && j >= defensiveY && i <= defensiveX + 300 && j <= defensiveY + defenseSizey) {
					return true;
				}
			}
		}
		return false;
		
	}
	public static void clear() {
		verticalSpeed=0;
		horizontalSpeed=0;
		attackCoordx=0;
		attackCoordy=0;
		attackSizex=0;
		attackSizey=0;
		defenseSizey=0;
	}
	
	public static int getVertical() {
		return verticalSpeed;
	}
	public static int getHorizontal() {
		return horizontalSpeed;
	}
	public static boolean getHit() {
		return hitDetected;
	}
	public static float getStunTime() {
		return stunTime;
	}
	public static float getImmuneTime() {
		return immuneTime;
	}
	public static float getDamage() {
		return damage;	
	}
}
