package model.sprite.movable;

import java.awt.Graphics;

import controll.Const_Game;
import resouceControll.resource.PlayerImage;
import view.Const_Size;

public class Player extends MovableSprite {

	private int playerOffsetY;
	private int playerOffsetX;
	private int playerBaseSize = Const_Size.PLAYER_BASESIZE.getCnt();

	public Player(int x, int y, int width, int height, PlayerImage pImage) {
		super(x, y, width, height, pImage.getAllImage());

		SPEED = Const_Game.PLAYER_SPEED.getCnt();
		playerOffsetY = Const_Size.PLAYER_BASESIZE.getCnt() - height;
		playerOffsetX = Const_Size.PLAYER_BASESIZE.getCnt() - width;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentImage, x - playerOffsetX, y - playerOffsetY, playerBaseSize, playerBaseSize, null);

//		g.drawRect(x, y, width, height);
	}

	public void move() {
		newX += vx;
		newY += vy;
	}

	@Override
	public void update() {
		x = newX;
		y = newY;
		changeImage();

		stop();
	}

	@Override
	public void changeImage() {
		if((frameCount++) == Const_Game.PLAYER_ANIMATION_FRAME_NUM.getCnt()) {
			frameCount = 0;
			currentImageIndex = (currentImageIndex + 1) % Const_Game.PLAYER_ANIMATION_NUM.getCnt();
			currentImage = dirImages[direction.getCnt()][currentImageIndex];
		}
	}
}