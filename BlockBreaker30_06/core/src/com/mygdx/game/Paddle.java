package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends Figura implements ICollidable{
    private int width;
    private int height;
    
 // atributo prara aplicar singleton
    private static Paddle pad;
    
    
    // Constructor de la clase Paddle
    private Paddle(int x, int y, int ancho, int alto) {
    	super(x, y, Color.BLUE);
    	width = ancho;
    	height = alto;
    }
    
    // get de singleton
    
    public static Paddle getInstance(int x, int y, int ancho, int alto) {
    	if(pad == null) {
    		pad = new Paddle(x, y, ancho, alto);
    	}
    	return pad;
    }
     
    // Setter & getter: atributos primitivos
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	// implementacion de metodos heredados de la clase abstracta Figura
	// Los metodos son: update() y draw ()
	@Override
	public void update() {
		//metodo en el cual actualiza la informacion de estado del objeto
        int x2 = super.getX(); //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =super.getX()-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=super.getX()+15; 
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            super.setX(x2);
        }
    }
    
	@Override
    public void draw(ShapeRenderer shape) {
		// metodo en el que se "dibuja" y se "pinta" la figura Paddle
    	shape.setColor(Color.BLUE);
    	shape.rect(super.getX(), super.getY(), width, height);
    }
	
	// Implementacion metodos de interfaz ICollidable
	@Override
	public void checkCollision(PingBall ball) {
		// Metodo que define las consecuencias de la colision
        if (collidesWith(ball)) {
            ball.setYSpeed(-ball.getYSpeed());
            ball.setColor(Color.GREEN);
        } else {
            ball.setColor(Color.WHITE);
        }
    }
	
	@Override
	public boolean collidesWith(PingBall ball) {
		// Metodo que verifica si colisionaron o no
        boolean intersectX = (super.getX() + width >= ball.getX() - ball.getSize()) && (super.getX() <= ball.getX() + ball.getSize());
        boolean intersectY = (super.getY() + height >= ball.getY() - ball.getSize()) && (super.getY() <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }
	
	
}
