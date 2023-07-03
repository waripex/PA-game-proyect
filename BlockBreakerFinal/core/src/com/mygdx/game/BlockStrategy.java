package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface BlockStrategy extends ICollidable{
	public void draw(ShapeRenderer shapre);
	void checkCollision(PingBall ball);
	boolean collidesWith(PingBall ball);
	boolean isDestroyed();
	
}
