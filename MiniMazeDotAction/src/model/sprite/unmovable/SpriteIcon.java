package model.sprite.unmovable;

import java.awt.Graphics;
import java.awt.Image;

import controll.Const_Game;
import model.sprite.Sprite;
import view.Const_Size;

public class SpriteIcon extends Sprite {

	private Image iconFrame;
	private Image[] iconFrame_selected;

	private Image currentFrame;

	private int frameCount;
	private int currentImageIndex;

	public boolean isSelected;
	public Const_Game unitState;

	private int offset;

	public SpriteIcon(int x, int y, int width, int height, Image[] spriteImg, Image iconImage, Image[] iconImageSelected, Const_Game unitState) {
		super(x, y, width, height, spriteImg);

		iconFrame = iconImage;
		iconFrame_selected = iconImageSelected;
		this.unitState = unitState;

		currentFrame = iconFrame;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(currentFrame, x - offset, y, null);

		g.drawImage(
				currentImage,
				x + Const_Size.CREATE_UNIT_IMAGE_POS.getCnt() - offset,
				y + Const_Size.CREATE_UNIT_IMAGE_POS.getCnt(),
				null
				);
	}

	@Override
	public void update() {
		changeImage();
	}

	public void changeImage() {
		if(isSelected) {
			if((frameCount++) == Const_Game.CREATE_UNIT_ANIMATION_FRAME_NUM.getCnt()) {
				frameCount = 0;
				currentImageIndex = (currentImageIndex + 1) % Const_Game.CREATE_UNIT_ANIMATION_NUM.getCnt();
				currentFrame = iconFrame_selected[currentImageIndex];
			}
		}else {
			currentFrame = iconFrame;
		}
	}

	public boolean isClicked(int mx, int my) {
		isSelected = false;

		if(x - offset < mx && mx < x + width - offset) {
			if(y < my && my < y + height) {
				isSelected = true;
			}
		}

		return isSelected;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}