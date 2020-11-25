package model.sprite;

import java.awt.Graphics;
import java.awt.Image;

import util.CommonUtil;

public abstract class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image[] img;
	protected Image currentImage;

	public boolean canWalk;

	public Sprite() {};

	public Sprite(int x, int y, int width, int height, Image[] img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = CommonUtil.deepCopyImage(img);
		currentImage = img[0];
	}

	public abstract void draw(Graphics g);

	public abstract void update();

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void setX(int newX) { x = newX;}
	public void setY(int newY) { y = newY;}

}
