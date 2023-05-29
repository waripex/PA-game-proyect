package com.mygdx.game;

import java.util.ArrayList;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	private boolean gameOver;//extra
	private int vel=2;
	private int tamano= 2;
	

    
		@Override
		public void create () {	
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    nivel = 1;
		    crearBloques(2+nivel);
		    shape = new ShapeRenderer();
		    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		    vidas = 3;
		    puntaje = 0; 
		    Mas(2+vel,2*tamano);
		}
		
		public void Mas(int vel,int tamano) {
			vel +=2;
			tamano +=2;
		}
		public void crearBloques(int filas) {
			blocks.clear();
			int blockWidth = 70;
		    int blockHeight = 26;
		    int y = Gdx.graphics.getHeight();
		    for (int cont = 0; cont<filas; cont++ ) {
		    	y -= blockHeight+10;
		    	//numeros negativos para bloques pequeños y positivos bloques normales
		    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 15)//o -15 {
		            blocks.add(new Block(x, y, blockWidth, blockHeight));
		        }
		    }
		
		public void dibujaTextos() {
			//actualizar matrices de la cámara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//dibujar textos
			font.draw(batch, "velocidad: " + vel, 300, 100);
			font.draw(batch, "nivel: " + nivel, 300, 25);// nivel en el que está
			font.draw(batch, "Puntos: " + puntaje, 10, 25);
			font.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth()-20, 25);
			batch.end();
			
			//Extra
			if (gameOver) {
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Borra pantalla 
				batch.begin();
				font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
				batch.end();
			}
			
			
		}
		
		
		@Override
		public void render () {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        pad.draw(shape); 
	        pad.update();
	        //
	        
	        
	        // monitorear inicio del juego
	        if (ball.estaQuieto()) {
	        	//pelota arriba de la barra comienzo
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+100);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false); gameOver =false;
	        }else ball.update();
	        
	        //verificar si se fue la bola x abajo
	        if (ball.getY()<0) {
	        	vidas--;
	        	//nivel = 1;
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10+tamano, 5+vel, 7+vel, true);
	        }
	        
	        // verificar game over
	        if (vidas<=0) {
	        	vidas = 3;
	        	nivel = 1;
	        	puntaje = 0;
	        	crearBloques(2+nivel);
	        	Mas(2+vel,2*tamano);
	        	gameOver = true; // extra
	        	
	        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        
	        }
	        
	        // verificar si el nivel se terminó
	        if (blocks.size()==0) {
	        	nivel++;
	        	//xSpeed += xSpeed;
	        	crearBloques(2+nivel);
	        	Mas(2+vel,2*tamano);
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10*tamano, 5+vel, 7+vel, true);
	        }    	
	        
	        
	        //dibujar bloques
	        for (Block b : blocks) {        	
	            b.draw(shape);
	            ball.checkCollision(b);
	        }
	        
	        // actualizar estado de los bloques 
	        for (int i = 0; i < blocks.size(); i++) {
	            Block b = blocks.get(i);
	            if (b.destroyed) {
	            	puntaje++;
	                blocks.remove(b);
	                i--; //para no saltarse 1 tras eliminar del arraylist
	            }
	        }
	        
	       
	        
	        ball.checkCollision(pad);
	        ball.draw(shape);
	        
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
	}
