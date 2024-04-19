package com.mygdx.image_editor;

import java.io.IOException;
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
	private EditWindow _editWindow;

	@Override
	public void create () {
		Util.testIntToSignedBytes();
		Instance = this;
		new ImageInputOutput();
//		Pixmap editMap = ImageInputOutput.Instance.loadImage("testImage.bmp");
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 50);
		_editWindow = new EditWindow(
		 editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));
		Button b = new Button(new Vector2 (50, 50), Vector2.Zero, Color.GOLD);
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
		batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x,
			_editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		rectangle.dispose();
	}
	
	public void filesImported(String[] filePaths) {
		for(int i = 0; i < filePaths.length; i++) {
			Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
			if(map == null) return;
			_editWindow.RecTexture = new Texture(map);
		}
	}

}
