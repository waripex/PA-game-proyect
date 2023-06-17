package com.mygdx.game;

public class PingBallSingleton {
	private static PingBall instance;
	
	private PingBallSingleton() {
		// constructor privado para evitar la creacion directa de la instancia
	}

	public static PingBall getInstance(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
		if (instance == null) {
			instance = new PingBall(x, y, size, xSpeed, ySpeed, iniciaQuieto);
		}
		return instance;
	}
}
