package model.sprite.movable;

import java.awt.Graphics;
import java.awt.Image;

import controll.Const_Game;
import view.Const_Size;

public class Box extends MovableSprite{

	public boolean move;
	public Const_Game direction;
	public int nextSpriteIndex;

	public Box(int x, int y, int width, int height, Image[] img) {
		super(x, y, width, height, img);

		SPEED = Const_Game.BOX_SPEED.getCnt();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x, y, null);
		//		g.drawRect(x, y, width, height);
	}

	@Override
	public void update() {
		x = newX;
		y = newY;
	}

	public void move() {
		if(vx + vy == 0) {
			return;
		}
		newX += vx;
		newY += vy;
		move = true;
	}

	public void stop() {
		vx = 0;
		vy = 0;
		move = false;
	}

	public void setSpeed() {
		if(move) return;

		switch(direction){
		case BACK:
			vy = - SPEED;
			break;
		case FRONT:
			vy = SPEED;
			break;
		case RIGHT:
			vx = SPEED;
			break;
		case LEFT:
			vx = - SPEED;
			break;

		default:
			break;
		}
	}

	@Override
	public void changeImage() {}

	public int[] getPoint() {
		return new int[]{x / Const_Size.UNIT_BASESIZE.getCnt(), y / Const_Size.UNIT_BASESIZE.getCnt()};
	}
}
