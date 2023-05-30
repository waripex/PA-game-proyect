package com.mygdx.game;

public interface ICollidable {
	void checkCollision(PingBall ball);
	boolean collidesWith(PingBall ball);

}
