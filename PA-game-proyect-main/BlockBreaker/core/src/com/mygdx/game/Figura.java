package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Figura {

	protected int x;
	protected int y;
	protected Color color;
	
	public Figura(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
	}
	
	public abstract void draw(ShapeRenderer shapre);
	public abstract void update();
}
