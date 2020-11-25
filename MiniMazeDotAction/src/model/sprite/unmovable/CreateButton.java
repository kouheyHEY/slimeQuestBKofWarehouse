package model.sprite.unmovable;

import java.awt.Graphics;
import java.awt.Image;

import controll.Const_Game;
import model.sprite.Sprite;

public class CreateButton extends Sprite{

	public boolean isPressed;
	public Const_Game buttonState;

	public CreateButton(int x, int y, int width, int height, Image[] img, Const_Game buttonState) {
		super(x, y, width, height, img);
		this.buttonState = buttonState;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x, y, null);
	}

	@Override
	public void update() {
		changeImage();
	}

	public void changeImage() {
		if(isPressed) {
			currentImage = img[1];
		}else {
			currentImage = img[0];
		}
	}

	public boolean isPressed(int mx, int my) {

		if(x < mx && mx < x + width) {
			if(y < my && my < y + height) {
				isPressed = true;
			}
		}

		return isPressed;
	}

	public boolean isReleased(int mx, int my) {
		isPressed = false;
		if(x < mx && mx < x + width) {
			if(y < my && my < y + height) {
				return true;
			}
		}

		return false;
	}

}
