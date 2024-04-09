package com.mygdx.image_editor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	SpriteBatch batch;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public Vector2 ScreenSize;
//	Button button1;
//	Button button2;
//	Button button3;
//	Button button4;
//	Button button5;

	@Override
	public void create () {
		Instance = this;
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		Vector2 rectangleScale = new Vector2(100,100);
//		button1 = new Button(
//			 rectangleScale,
//			 new Vector2(ScreenSize.x / 2f - rectangleScale.x / 2f, ScreenSize.y / 2f - rectangleScale.y / 2f),
//			 Color.WHITE);
//		button2 = new Button(
//			 rectangleScale,
//			 new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2, 1.5f*(ScreenSize.y / 2f - rectangleScale.y / 2f)),
//			 Color.RED);
//		button3 = new Button(
//				 rectangleScale,
//				 new Vector2(ScreenSize.x / 2f + rectangleScale.x, 1.5f*(ScreenSize.y / 2f - rectangleScale.y / 2f)),
//				 Color.BLUE);
//		button4 = new Button(
//				 rectangleScale,
//				 new Vector2(ScreenSize.x / 2f - rectangleScale.x * 2, .5f*(ScreenSize.y / 2f - rectangleScale.y / 2f)),
//				 Color.ORANGE);
//		button5 = new Button(
//			 rectangleScale,
//			 new Vector2(ScreenSize.x / 2f + rectangleScale.x, .5f*(ScreenSize.y / 2f - rectangleScale.y / 2f)),
//			 Color.GREEN);
		
		CollisionManager collisionManager = new CollisionManager();

	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		Rec2D rec;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		rectangle.dispose();
	}
}
