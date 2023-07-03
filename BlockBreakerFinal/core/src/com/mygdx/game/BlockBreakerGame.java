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
	private Figura ball;
	private Figura pad;
	private ArrayList<BlockStrategy> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	private boolean gameOver;//extra
	private int vel;
	private int tamano;
	private Nivel lvl;
	private BlockStrategy nBlock;
	private BlockStrategy hBlock;
	private BlockStrategy eBlock;
		
    
		@Override
		public void create () {	
			lvl = new Nivel1();
			ball = lvl.obtenerBall();
			pad = lvl.obtenerPad();
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 500);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    nivel = 1;
		    crearBloques(1+nivel,nivel);
		    vel = 1;
		    tamano = 2;
		    
		    shape = new ShapeRenderer();
		    //ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10+tamano, 5+vel, 7+vel, true);
		    //pad = Paddle.getInstance(Gdx.graphics.getWidth()/2-50,40,100,10);
		    vidas = 3;
		    puntaje = 0; 
		    
		}
		
		// Metodo en el cual se crean todos los bloques que se mostraran al iniciar el juego
		public void crearBloques(int filas, int nivel) {
			blocks.clear();
			int blockWidth = 59;
		    int blockHeight = 30;
		    int resistencia1 = 1;
		    int resistencia3 = 3;
		    int resistencia5 = 5;
		    int y = Gdx.graphics.getHeight();
		    for (int cont = 0; cont<filas; cont++ ) {
		    	y -= blockHeight+10;
		    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 5) {//15 0 -15
		            //blocks.add(new Block(x, y, blockWidth, blockHeight));
		    		
		    		// Crear instancias de los bloques y agregarlos a las listas correspondientes
	               // NormalBlock normalBlock = new NormalBlock(x, y, blockWidth, blockHeight,resistencia1);
	                nBlock = new NormalBlock(x, y, blockWidth, blockHeight,resistencia1);
	                hBlock = new HardBlock(x, y, blockWidth, blockHeight,resistencia3);
	                eBlock = new ExtremeBlock(x, y, blockWidth, blockHeight,resistencia5);
	                //HardBlock hardBlock = new HardBlock(x, y, blockWidth, blockHeight,resistencia3);
	                //ExtremeBlock extremeBlock = new ExtremeBlock(x, y, blockWidth, blockHeight,resistencia5);
	                if(nivel == 1) {
	                	blocks.add(nBlock);
	                	
	                	
	                	//.add(normalBlock);
	                }
	                if(nivel==2) {

	                	blocks.add(hBlock);
		                //hardblocks.add(hardBlock);
	                }
	                if(nivel==3) {
	                	blocks.add(eBlock);
		                //extremeblocks.add(extremeBlock);
	                }  
		        }
		    }
		    nivel++;
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
				Gdx.gl.glClearColor(0, 1, 1, 0);//cambio de fondo de pantalla
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Borra pantalla 
				batch.begin();
				font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
				batch.end();
			}
			
		}
		
		
		@Override
		public void render () {
			Gdx.gl.glClearColor(0, 1, 1, 0);//cambio de fondo de pantalla
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        pad.draw(shape);
	        pad.update();
	        
	        
	        // monitorear inicio del juego
	        if (((PingBall)ball).estaQuieto()) {
	        	ball.setXY(pad.getX()+((Paddle)pad).getWidth()/2-5, pad.getY()+((Paddle)pad).getHeight()+30);//last n° comienzo de la pelota
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ((PingBall)ball).setEstaQuieto(false); gameOver =false;
	        }else ball.update();
	        
	        //verificar si se fue la bola x abajo
	        if (ball.getY()<0) {
	        	vidas--;
	        	//nivel = 1;
	        	ball = new PingBall(pad.getX()+((Paddle)pad).getWidth()/2-5, pad.getY()+((Paddle)pad).getHeight()+11, 10+tamano, 5+vel, 7+vel, true);
	        	// verificar game over+
	        	if (vidas <= 0) {
	                // Reinicio del nivel y otras variables
	                vidas = 3;
	                nivel = 1;
	                vel = 1;
	                tamano = 2;
	                puntaje = 0;
	                crearBloques(1+nivel,nivel);
	                gameOver = true;
	            }
	        }
	        
	        // verificar game over
	        if (vidas<=0) {
	        	// Reinicio del nivel
	        	vidas = 3;
	        	nivel = 1;
	        	vel = 1;
	        	tamano = 2;
	        	puntaje = 0;
	        	crearBloques(1+nivel,nivel);
	        	gameOver = true; // extra
	        	
	        	
	        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        
	        }
	        
	        // verificar si el nivel se terminó
	        if (blocks.size()==0) {
	        	nivel++;
	        	vidas ++;// Se le agrega una vida extra al pasar de nivel
	        	tamano +=2;
	        	vel ++;
	        	crearBloques(1+nivel,nivel);
	        	ball = new PingBall(pad.getX()+((Paddle)pad).getWidth()/2-5, pad.getY()+((Paddle)pad).getHeight()+11, 10+tamano, 5+vel, 7+vel, true);
	        }    	
	        
	        
	        //dibujar bloques
	        for (BlockStrategy b: blocks) {        	
	            b.draw(shape);
	            //ball.checkCollision(b);
	            b.checkCollision((PingBall)(ball));
	        }
	        
	        
	        // actualizar estado de los bloques 
	        for (int i = 0; i < blocks.size(); i++) {
	            BlockStrategy b = blocks.get(i);
	            if (b.isDestroyed()) {
	            	puntaje++;
	                blocks.remove(b);
	                i--; //para no saltarse 1 tras eliminar del arraylist
	            }
	        }
	        
	        //ball.checkCollision(pad);
	        ((Paddle)pad).checkCollision((PingBall)(ball));
	        ball.draw(shape);
	        
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
	}
