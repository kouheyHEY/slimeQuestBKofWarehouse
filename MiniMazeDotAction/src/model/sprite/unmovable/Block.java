package model.sprite.unmovable;

import java.awt.Graphics;
import java.awt.Image;

import model.sprite.Sprite;

public class Block extends Sprite {

	public Block(int x, int y, int width, int height, Image[] img) {
		super(x, y, width, height, img);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x, y, null);
	}

	@Override
	public void update() {
	}

}
