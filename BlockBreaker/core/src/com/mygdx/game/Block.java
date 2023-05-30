package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends Figura implements ICollidable{
    int x,y,width,height;
    Color cc;
    boolean destroyed;
    
    public Block(int x, int y, int width, int height) {
    	super(x,y, new Color(0, 0, 0, 0));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        destroyed = false;
        Random r = new Random(x+y);
        
       cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
  
    }
    
    //Extra
    
    @Override
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public void checkCollision(PingBall ball) {
        if (collidesWith(ball)) {
            ball.setYSpeed(-ball.getYSpeed());
            destroyed = true;
        }
    }
    
    @Override
    public boolean collidesWith(PingBall ball) {
        boolean intersectX = (x + width >= ball.getX() - ball.getSize()) && (x <= ball.getX() + ball.getSize());
        boolean intersectY = (y + height >= ball.getY() - ball.getSize()) && (y <= ball.getY() + ball.getSize());
        return intersectX && intersectY;
    }
}
