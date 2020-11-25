package model.sprite.movable;

import java.awt.Image;

import controll.Const_Game;
import model.sprite.Sprite;

public abstract class MovableSprite extends Sprite implements Movable{

	public Const_Game direction = Const_Game.FRONT;

	protected Image[] frontImages;
	protected Image[] rightImages;
	protected Image[] backImages;
	protected Image[] leftImages;

	protected Image[][] dirImages;

	protected int currentImageIndex;
	protected int frameCount;

	protected static int SPEED;
	public int vx;
	public int vy;

	public int newX;
	public int newY;

	public MovableSprite(int x, int y, int width, int height, Image[][] dirImg) {
		super(x, y, width, height, dirImg[Const_Game.FRONT.getCnt()]);

		dirImages = dirImg;
		frontImages = dirImg[Const_Game.FRONT.getCnt()];
		rightImages = dirImg[Const_Game.RIGHT.getCnt()];
		backImages = dirImg[Const_Game.BACK.getCnt()];
		leftImages = dirImg[Const_Game.LEFT.getCnt()];

		newX = x;
		newY = y;
	}

	public MovableSprite(int x, int y, int width, int height, Image[] img) {
		super(x, y, width, height, img);
		newX = x;
		newY = y;
	}

	public abstract void changeImage();

	public void moveRight() {
		vx = SPEED;
		direction = Const_Game.RIGHT;
	}

	public void moveFront() {
		vy = SPEED;
		direction = Const_Game.FRONT;
	}

	public void moveLeft() {
		vx = - SPEED;
		direction = Const_Game.LEFT;
	}

	public void moveBack() {
		vy = - SPEED;
		direction = Const_Game.BACK;
	}

	public boolean collideSprite(Sprite sprite) {
		if(newX < sprite.getX() + sprite.getWidth() && newX + width > sprite.getX()) {
			if(newY < sprite.getY() + sprite.getHeight() && newY + height > sprite.getY()) {
				return true;
			}
		}
		return false;
	}

	public void stop() {
		vx = 0;
		vy = 0;
		newX = x;
		newY = y;
	}
}
