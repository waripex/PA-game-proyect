package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends Figura {
    int x,y,width,height;
    Color cc;
    boolean destroyed;
    
    public Block(int x, int y, int width, int height) {
    	super(x,y, new Color(0, 0, 0, 0));
        this.x = x;
        this.y = y;
        this.width = 70;//75 para abajo
        this.height = 30;//35 para abajo
        destroyed = false;
        Random r = new Random(x+y);
        
       cc = new Color(0.1f+r.nextFloat(1), r.nextFloat(1), r.nextFloat(1), 10);
  
    }

    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(x, y, width, height);
    }
    
    @Override
    public void update() {
    }
}
