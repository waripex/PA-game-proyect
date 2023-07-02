package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Block implements ICollidable{
    private int x,y,width,height;
    private Color cc = Color.SCARLET;
    private boolean destroyed;
    
   
 // Constructor de la clase Block
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
    }
    
 
    //Extra
    public void draw(ShapeRenderer shape){
    	// metodo en el que se "dibuja" y se "pinta" Block
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    

    public void update() {// No se utliza
    }
    
    // Implementacion metodos de interfaz ICollidable
    @Override
    public void checkCollision(PingBall ball) {
    	// Metodo que define las consecuencias de la colision
        if (collidesWith(ball)) {
            ball.setYSpeed(-ball.getYSpeed());
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
}
