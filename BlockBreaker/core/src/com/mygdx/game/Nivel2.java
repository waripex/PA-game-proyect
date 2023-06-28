package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Nivel2 extends Nivel{
	@Override
	public void crearPiezas(Figura ballInput, Figura padInput, int nivel, int tamano, int vel) {
		
		
		
	// Crea las figuras del juego
    ballInput = PingBall.getInstance(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 7+vel, 10+vel, true );
    //ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true);
    padInput = Paddle.getInstance(Gdx.graphics.getWidth()/2-50,40,100,10);
    //padInput = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
    crearBloques(2+nivel);
	}
}

