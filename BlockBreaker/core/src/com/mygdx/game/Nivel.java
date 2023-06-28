package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Nivel {
	
	private ArrayList<Block> blocks = new ArrayList<>();
	
	public void crearBaseGame(OrthographicCamera camera,SpriteBatch batch, BitmapFont font,ShapeRenderer shape) {
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
	    batch = new SpriteBatch();
	    font = new BitmapFont();
	    font.getData().setScale(3, 2);
	    shape = new ShapeRenderer();
	}
	
	
	public abstract void crearPiezas(Figura ballInput, Figura padInput, int nivel, int tamano, int vel);
	
	/*{
		// Crea las figuras del juego
	    ballInput = PingBall.getInstance(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true );
	    //ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true);
	    padInput = Paddle.getInstance(Gdx.graphics.getWidth()/2-50,40,100,10);
	    //padInput = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
	    crearBloques(2+nivel);
	    
	
	}*/
	
	public void crearBloques(int filas) {
		blocks.clear();
		int blockWidth = 70;
	    int blockHeight = 26;
	    int y = Gdx.graphics.getHeight();
	    for (int cont = 0; cont<filas; cont++ ) {
	    	y -= blockHeight+10;
	    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 15) {//15 0 -15
	            blocks.add(new Block(x, y, blockWidth, blockHeight));
	        }
	    }
	}
		
	
	

}
