package resouceControll.resource;

import java.awt.Image;

import controll.Const_Game;

public class PlayerImage {
	private Image[] frontImage;
	private Image[] rightImage;
	private Image[] backImage;
	private Image[] leftImage;

//	private Image[] groundImage;

	public Image[] getFrontImage() {
		return frontImage;
	}

	public Image[] getRightImage() {
		return rightImage;
	}

	public Image[] getBackImage() {
		return backImage;
	}

	public Image[] getLeftImage() {
		return leftImage;
	}


	public void setFrontImage(Image[] frontImage) {
		this.frontImage = (Image[]) util.CommonUtil.deepCopyImage(frontImage);
	}

	public void setRightImage(Image[] rightImage) {
		this.rightImage = (Image[]) util.CommonUtil.deepCopyImage(rightImage);
	}

	public void setBackImage(Image[] backImage) {
		this.backImage = (Image[]) util.CommonUtil.deepCopyImage(backImage);
	}

	public void setLeftImage(Image[] leftImage) {
		this.leftImage = (Image[]) util.CommonUtil.deepCopyImage(leftImage);
	}

	public Image[][] getAllImage() {
		Image[][] allImage = new Image[4][Const_Game.PLAYER_ANIMATION_NUM.getCnt()];
		allImage[Const_Game.FRONT.getCnt()] = frontImage;
		allImage[Const_Game.RIGHT.getCnt()] = rightImage;
		allImage[Const_Game.BACK.getCnt()] = backImage;
		allImage[Const_Game.LEFT.getCnt()] = leftImage;

		return allImage;
	}

}
