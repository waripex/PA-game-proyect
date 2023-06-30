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
	private int vel;
	private int tamano;

	 
	 

    
		@Override
		public void create () {	
			
			// Elementos de Pantalla 
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    shape = new ShapeRenderer();
		    
		    // Datos iniciales del juego
		    nivel = 1;
		    vel = 1;
		    tamano = 2;
		    vidas = 3;
		    puntaje = 0;
		    crearBloques(2+nivel);
		    
		    //Figuras: Ball y Paddle
		    
		    ball = PingBall.getInstance(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true );// instancia ball singleton
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		   
		     
		     
		    
		}
		
		// Metodo en el cual se crean todos los bloques que se mostraran al iniciar el juego
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
		
		// Metodo encargado de imprimir todos los textos del juego.
		public void dibujaTextos() {
			//actualizar matrices de la cámara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//dibujar textos
			//font.draw(batch, "velocidad: " + vel, 300, 100);
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
	        
	        
	        // monitorear inicio del juego
	        if (ball.estaQuieto()) {
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false); gameOver =false;
	        }else ball.update();
	        
	         
	         
	         
	        
	        

	        // Verifica el estado de la pelota
	        // si se cae la pelota bajo el paddle, reinica figura pelota
	        if (ball.getY()<0) {
	        	vidas--;
	        	//nivel = 1;
	        	
	        	ball = PingBall.getInstance(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true );
	        }
	        
	        
	        
	        // Verifica fin del juego = si no hay mas vidas
	        if (vidas<=0) {
	        	// Reinicio del nivel en caso de perder
	        	vidas = 3;
	        	nivel = 1;
	        	vel = 1;
	        	tamano = 2;
	        	puntaje = 0;
	        	crearBloques(2+nivel);
	        	gameOver = true; // extra
	        	
	        	
	        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        
	        }
	        
	        // Verificar fin del nivel: si no hay mas bloques
	        // se inicia el siguiente nivel
	        if (blocks.size()==0) {
	        	nivel++;
	        	vidas ++;// Se le agrega una vida extra al pasr de nivel
	        	tamano +=2;
	        	vel ++;
	        	crearBloques(2+nivel);	        	
	        	ball = PingBall.getInstance(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true );
	        }    	
	        
	        
	        //dibujar bloques
	        for (Block b : blocks) {        	
	            b.draw(shape);
	            //ball.checkCollision(b);
	            b.checkCollision(ball);
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
	        
	       
	        pad.checkCollision(ball);
	        ball.draw(shape);
	        
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
	}
