package model.sprite.unmovable;

import java.awt.Graphics;
import java.awt.Image;

import controll.Const_Game;
import model.sprite.Sprite;

public class Hole extends Sprite {

	public boolean underBox;
	private int frameCount;
	private int currentImageIndex = 1;

	public Hole(int x, int y, int width, int height, Image[] img) {
		super(x, y, width, height, img);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x, y, null);
	}

	@Override
	public void update() {

		if(!canWalk && underBox) {
			changeImage();
		}
	}

	public void changeImage() {
		if((frameCount++) == Const_Game.HOLE_ANIMATION_FRAME_NUM.getCnt()) {
			frameCount = 0;
			currentImageIndex = (currentImageIndex + 1) % Const_Game.HOLE_ANIMATION_NUM.getCnt();

			if(currentImageIndex == img.length - 1) canWalk = true;
		}

		currentImage = img[currentImageIndex];
	}
}
