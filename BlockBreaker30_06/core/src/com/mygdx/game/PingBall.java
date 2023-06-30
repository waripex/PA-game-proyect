package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends Figura{
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    
	    
	    // Constructor clase PingBall
	    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	    	super(x,y, Color.WHITE);
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    
	    
	    // Setter y getters especiales
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
	    
	    
	    // Setters y getters: Atributos primitivos
	    public int getSize() {return size;}
	    public int getXSpeed() {return xSpeed;}
	    public int getYSpeed() {return ySpeed;}
	    
	    
	    // implementacion de metodos heredados de la clase abstracta Figura
		// Los metodos son: update() y draw ()

	    @Override
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(super.getX(), super.getY(), size);// size : radio
	    }
	    
	
	    @Override
	    public void update() {
	    	if (estaQuieto) return;
	        //super.setX(xSpeed+1);
	        //super.setY(ySpeed+1);
	        super.setX(super.getX() + xSpeed);
	        super.setY(super.getY() + ySpeed);
	        if (super.getX()-size < 0 || super.getX()+size > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }
	        if (super.getY()+size > Gdx.graphics.getHeight()) {
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
