package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Figura {

	private int x;
	private int y;
	private Color color;
	
	public Figura(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
	}
	
	// metodo en el que se "dibuja" y se "pinta" la figura
	public abstract void draw(ShapeRenderer shapre);
	
	//metodo en el cual actualiza la informacion de estado del objeto
	public abstract void update();
	
	//setters y getters para una mejor herencia
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public void setXY(int x, int y) {
		this.x=x;
		this.y=y;
	}

}
