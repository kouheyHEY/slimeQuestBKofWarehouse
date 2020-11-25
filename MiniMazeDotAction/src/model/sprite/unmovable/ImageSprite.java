package model.sprite.unmovable;

import java.awt.Graphics;
import java.awt.Image;

import model.sprite.Sprite;

public class ImageSprite extends Sprite {

	private int frameCount = 0;
	private int yokoyureFrameCount = 0;
	private int yokoyureNum = 4;
	private int yokoyureInterval = 4;
	private int yokoyureWidth;

	public ImageSprite(int x, int y, int width, int height, Image image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.currentImage = image;

		yokoyureWidth = width / 8;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x, y, width, height, null);
	}

	@Override
	public void update() {

	}

	public void updateFloat() {
		frameCount = (frameCount + 1) % yokoyureInterval;
		if(frameCount == 0) {
			yokoyureFrameCount = (yokoyureFrameCount + 1) % yokoyureNum;
		}else {
			return;
		}

		if(yokoyureFrameCount < 2) {
			this.x += yokoyureWidth;
		}else {
			this.x -= yokoyureWidth;
		}

	}

}
