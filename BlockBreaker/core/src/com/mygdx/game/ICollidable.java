package com.mygdx.game;

public interface ICollidable {
	
	// Metodo que define las consecuencias de la colision
	void checkCollision(PingBall ball);
	
	// Metodo que verifica si colisionaron o no
	boolean collidesWith(PingBall ball);

}
