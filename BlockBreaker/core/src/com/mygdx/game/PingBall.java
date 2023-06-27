package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends Figura{
	    private int x;
	    private int y;
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    private static PingBall ball;
	    
	    // Constructor clase PingBall
	    private PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	    	super(x,y, Color.WHITE);
	        this.x = x;
	        this.y = y;
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    // Setter y getters especiales
	    
	    	// get de Singleton 
    
	    public static PingBall getInstance(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	    	if (ball == null) {
				ball = new PingBall(x, y, size, xSpeed, ySpeed, iniciaQuieto);
			}
			return ball;
	    	
	    	//return PingBallSingleton.getInstance(x, y, size, xSpeed, ySpeed, iniciaQuieto);
	    }
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }

	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }
	    public void setColor(Color color) {
	    	this.color = color;
	    }
	    
	    public void setYSpeed(int ySpeed) {
	    	this.ySpeed = ySpeed;
	    }
	    
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    // Setters y getters: Atributos primitivos
	    public int getY() {return y;}
	    public int getX() {return x;}
	    public int getSize() {return size;}
	    public int getXSpeed() {return xSpeed;}
	    public int getYSpeed() {return ySpeed;}
	    
	    
	    // implementacion de metodos heredados de la clase abstracta Figura
		// Los metodos son: update() y draw ()

	    @Override
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, size);// size : radio
	    }
	    
	
	    @Override
	    public void update() {
	    	if (estaQuieto) return;
	        x += xSpeed;
	        y += ySpeed;
	        if (x-size < 0 || x+size > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }
	        if (y+size > Gdx.graphics.getHeight()) {
	            ySpeed = -ySpeed;
	        }
	        
	    }
	    
	    // EL siguiente trozo de codigo, le otorga la responsabilidad de revisar las colisiones a esta clase
	    // Por diseno, esto se modularizo en una interfaz, la cual se implementa en las clases Paddle y Block
	    
	    /*
	    public void checkCollision(Paddle paddle) {
	        if(collidesWith(paddle)){
	            color = Color.GREEN;
	            ySpeed = -ySpeed;
	        }
	        else{
	            color = Color.WHITE;
	        }
	    }
	    private boolean collidesWith(Paddle pp) {

	    	boolean intersectaX = (pp.getX() + pp.getWidth() >= x-size) && (pp.getX() <= x+size);
	        boolean intersectaY = (pp.getY() + pp.getHeight() >= y-size) && (pp.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	    public void checkCollision(Block block) {
	        if(collidesWith(block)){
	            ySpeed = - ySpeed;
	            block.destroyed = true;
	        }
	    }
	    private boolean collidesWith(Block bb) {

	    	boolean intersectaX = (bb.x + bb.width >= x-size) && (bb.x <= x+size);
	        boolean intersectaY = (bb.y + bb.height >= y-size) && (bb.y <= y+size);		
	    	return intersectaX && intersectaY;
	    }*/

	    
	}