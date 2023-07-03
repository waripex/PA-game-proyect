package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;


public class Nivel2 extends Nivel{
	private ArrayList<Block> blocks = new ArrayList<>();
	private Figura ball;
	private Figura pad;

	
	
	
	
	
	@Override
	public void crearFiguras() {
		pad = Paddle.getInstance(Gdx.graphics.getWidth()/2-50,40,100,10);
		ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);// 10 : tamano; 5 y 7: vel
		
	}
	
	@Override
	protected Figura obtenerBall() {
		ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);// 10 : tamano; 5 y 7: vel
		return ball;
		
	}
	
	@Override
	protected Figura obtenerPad() {
		pad = Paddle.getInstance(Gdx.graphics.getWidth()/2-50,40,100,10);
		return pad;
	}

	@Override
	public void crearBloques() {
		blocks.clear();
		int blockWidth = 70;//70
	    int blockHeight =36;//26
	    int y = Gdx.graphics.getHeight();
	    for (int cont = 0; cont<2; cont++ ) {
	    	y -= blockHeight+10;
	    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 15) {//15 0 -15
	    		
	            blocks.add(new Block(x, y, blockWidth, blockHeight));
	        }
	    }
		
	}
}
	