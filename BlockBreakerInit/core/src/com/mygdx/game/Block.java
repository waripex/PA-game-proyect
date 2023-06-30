package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block implements ICollidable{
    int x,y,width,height;
    Color cc;
    boolean destroyed;
    
 // Constructor de la clase Block
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        Random r = new Random(x+y);
        
       cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
  
    }
    
    //Extra
    
    // implementacion de metodos heredados de la clase abstracta Figura
 	// Los metodos son: update() y draw ()
    
    
    public void draw(ShapeRenderer shape){
    	// metodo en el que se "dibuja" y se "pinta" la figura Block
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
