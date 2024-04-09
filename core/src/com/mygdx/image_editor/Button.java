package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D {
	private Color _startColor;
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		InputManager.Instance.Buttons.add(this);
		_state = ButtonState.None;
	}
	
	public enum ButtonState {Clicked, Hovered, None};
	private ButtonState _state;
	public void onHovered() {
		if(_state == ButtonState.Clicked) return; 
		_recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
		_state = ButtonState.Hovered;
		generateTexture();
		}
	
	public void onHoverExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
		}
	
	public void onClickUp()
	{
		_state = ButtonState.Hovered;
		_recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
		generateTexture();
	}
	
	public void onClickDown() {	
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r / 4f, _startColor.g / 4f , _startColor.b / 4f, 1);
		generateTexture();
	}



}

