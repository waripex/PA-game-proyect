package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ExtremeBlock implements BlockStrategy {
	//se usan la interfaz BlockStrategy y ICollidable 
	//para la implementacion de este nuevo tipo de bloque
	private int x,y,width,height;
    private Color cc = Color.FIREBRICK;
    boolean destroyed;
    private int resistencia=5;
    //se le agrega una resistencia y otro tipo de color

   
 // Constructor de la clase ExtremeBlock
    public ExtremeBlock(int x, int y, int width, int height, int resistencia) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        this.resistencia = resistencia;
    }
    

    public void draw(ShapeRenderer shape){
    	// metodo en el que se "dibuja" y se "pinta" el bloque extremo
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
 // Implementacion metodos de interfaz ICollidable
    @Override
    public void checkCollision(PingBall ball) {
    	// Metodo que define las consecuencias de la colision
        if (collidesWith(ball)) {
            ball.setYSpeed(-ball.getYSpeed());
            resistencia--;
            if(resistencia <= 0)
            	destroyed = true;
            
        }
    }
    
    @Override
    public boolean collidesWith(PingBall ball) {
    	// Metodo que verifica si colisionaron o no
        boolean intersectX = (x + width >= ball.getX() - ball.getSize()) && (x <= ball.getX() + ball.getSize());
        boolean intersectY = (y + height >= ball.getY() - ball.getSize()) && (y <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }
	
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

}
