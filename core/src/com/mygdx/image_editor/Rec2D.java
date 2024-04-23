package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

public class Rec2D {
	public Vector2 Scale;
	public Vector2 Position;
	public Texture RecTexture;
	private Pixmap _pixelMap;
	public Outline Outline;
	protected Color _recColor;
	
	public Rec2D(Vector2 scale, Vector2 position, Color recColor) {
		Position = position;
		Scale = scale;
		_recColor = recColor;
		generateTexture();
		Outline = new Outline(scale, Color.BLACK, 1);
		ImageEditor.Instance.Rectangles.add(this);
	}
	
	protected void generateTexture() {
		_pixelMap = new Pixmap((int) Scale.x, (int) Scale.y, Format.RGBA8888);
		_pixelMap.setColor(_recColor);
		for(int x = 0; x < _pixelMap.getWidth(); x++) {
			for(int y = 0; y < _pixelMap.getHeight(); y++) {
				_pixelMap.drawPixel(x, y);
			}
		}
		RecTexture = new Texture(_pixelMap);
	}
	
	public void changeColor(Color newColor) {
		_recColor = newColor;
		//Regernate our texture using our new color
		generateTexture();
		}

	
	

}
