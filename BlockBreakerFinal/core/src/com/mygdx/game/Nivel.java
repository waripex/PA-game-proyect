package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public abstract class Nivel {
	protected int nivel;
    protected int vidas;
    protected int tamano;
    protected int vel;
    protected ArrayList<Block> blocks;
    protected Figura ball;
    protected Figura pad;

	
	public final void jugarNivel(int filas) {
		crearFiguras();
		crearBloques();
		
	}

	protected abstract void crearBloques();
	protected abstract Figura obtenerBall();
	protected abstract Figura obtenerPad();
	protected abstract void crearFiguras();
	
	 
	
	

	
}
