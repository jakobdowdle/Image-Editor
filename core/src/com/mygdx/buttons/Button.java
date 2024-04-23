package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.image_editor.Rec2D;

import Utility.IClickable;
import Utility.IHoverable;
import Utility.InputManager;

public class Button extends Rec2D implements IClickable, IHoverable {
	protected Color _startColor;
	public String ButtonText;
	private Color _hoveredColor;
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		InputManager.Instance.Clickables.add(this);
		InputManager.Instance.Hoverables.add(this);
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
	
	public void onClickUp(Vector2 clickPosition)
	{
		_state = ButtonState.Hovered;
		_recColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
		generateTexture();
	}
	
	public void onClickDown(Vector2 clickPosition) {	
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r / 4f, _startColor.g / 4f , _startColor.b / 4f, 1);
		generateTexture();
	}

	@Override
	public void onClickDragged(Vector2 clickPosition) {
		// TODO Auto-generated method stub
		
	}



}

