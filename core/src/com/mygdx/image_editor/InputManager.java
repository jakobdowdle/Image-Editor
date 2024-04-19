package com.mygdx.image_editor;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor{
	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();
	private IHoverable _currentlyHovered;
	private IClickable _currentlyClicked;
	public static InputManager Instance;
	public InputManager() {
		Instance = this;
	}
	
	private boolean _controlPressed;
	public boolean keyDown(int keycode) {
		if(_controlPressed && keycode == Keys.S)
			try {ImageInputOutput.Instance.saveImage("C:/Users/jakob/Desktop/output.bmp");}
			catch (IOException e) {e.printStackTrace();}
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
		return false;
	}
	public boolean keyUp(int keycode) {
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
		return false;
	}

	public boolean keyTyped(char character) {return false;}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY);
		IClickable collision = CollisionManager.Instance.getClicked(worldPosition);
		if(collision != null) collision.onClickDown(worldPosition);
		_currentlyClicked = collision;
		return true;
	}
	
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(_currentlyClicked != null)
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		
		return true;
		}
	
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null)
			 _currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));

		return true;
		}
	
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = CollisionManager.Instance.getHovered(
				new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY)
			);
		
		if(collision != _currentlyHovered && _currentlyHovered != null) _currentlyHovered.onHoverExit();
		if(collision != null) collision.onHovered();
		
		_currentlyHovered = collision;
			
		return true;
	}
	public boolean scrolled(float amountX, float amountY) {return false;}

}
