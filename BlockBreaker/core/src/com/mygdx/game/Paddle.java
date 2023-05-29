package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends Figura{
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Paddle(int x, int y, int ancho, int alto) {
    	super(x, y, Color.BLUE);
    	this.x = x;
    	this.y= y;
    	width = 200;//350 max
    	height = 15;// lo k quieras
    }
     
    // Setter & getter: atributos primitivos
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public void update() {
        int x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 =x-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2=x+15; 
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            x = x2;
        }
    }
    
	@Override
    public void draw(ShapeRenderer shape) {
    	shape.setColor(Color.BLUE);
    	shape.rect(x, y, width, height);
    }
}
