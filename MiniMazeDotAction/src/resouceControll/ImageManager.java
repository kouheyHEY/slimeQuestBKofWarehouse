package resouceControll;

import java.awt.Image;

import controll.Const_Game;
import resouceControll.load.ImageLoader;
import resouceControll.resource.PlayerImage;
import resouceControll.resource.StageImage;

public class ImageManager {

	private StageImage[] stgImages = new StageImage[Const_Game.STAGE_NUM.getCnt()];
	private PlayerImage playerImage = new PlayerImage();

	private Image unitFrame;
	private Image[] unitFrame_choose;

	private Image titleCreateButton;
	private Image titlePlayButton;
	private Image titleBgImage;
	private Image titleCursor;

	private Image[] button_save;
	private Image[] button_open;
	private Image[] button_title;

	private ImageLoader imgLoader = new ImageLoader();

	public void loadStgImage(Const_Game stageState) {

		if(stageState == Const_Game.GRASS) {
			String floorImage1Url = "image/field_grass_1.png";
			String floorImage2Url = "image/field_grass_2.png";
			Image[] floorImage = {
					imgLoader.loadImageFromFilePath(floorImage1Url),
					imgLoader.loadImageFromFilePath(floorImage2Url)
			};

			String blockImageUrl = "image/block_Rock.png";
			Image[] blockImage = {
					imgLoader.loadImageFromFilePath(blockImageUrl)
			};

			String holeImage1Url = "image/hole_grass_1.png";
			String holeImage2Url = "image/hole_grass_2.png";
			String holeImage3Url = "image/hole_grass_3.png";
			Image[] holeImage = {
					imgLoader.loadImageFromFilePath(holeImage1Url),
					imgLoader.loadImageFromFilePath(holeImage2Url),
					imgLoader.loadImageFromFilePath(holeImage3Url)
			};

			String boxImageUrl = "image/box_wood.png";
			Image[] boxImage = {
					imgLoader.loadImageFromFilePath(boxImageUrl)
			};

			String goalImageUrl = "image/goal_houseRamen.png";
			Image[] goalImage = {
					imgLoader.loadImageFromFilePath(goalImageUrl)
			};

			stgImages[stageState.getCnt()] = new StageImage(
					floorImage,
					blockImage,
					holeImage,
					boxImage,
					goalImage
					);
		}
	}

	public void loadPlayerImage() {
		Image[] frontImage = new Image[Const_Game.PLAYER_ANIMATION_NUM.getCnt()];
		Image[] rightImage = new Image[Const_Game.PLAYER_ANIMATION_NUM.getCnt()];
		Image[] backImage = new Image[Const_Game.PLAYER_ANIMATION_NUM.getCnt()];
		Image[] leftImage = new Image[Const_Game.PLAYER_ANIMATION_NUM.getCnt()];

		frontImage[0] = imgLoader.loadImageFromFilePath("image/slime_front_normal_1.png");
		frontImage[1] = imgLoader.loadImageFromFilePath("image/slime_front_normal_2.png");
		frontImage[2] = imgLoader.loadImageFromFilePath("image/slime_front_normal_3.png");
		frontImage[3] = imgLoader.loadImageFromFilePath("image/slime_front_normal_2.png");
		playerImage.setFrontImage(frontImage);

		rightImage[0] = imgLoader.loadImageFromFilePath("image/slime_right_normal_1.png");
		rightImage[1] = imgLoader.loadImageFromFilePath("image/slime_right_normal_2.png");
		rightImage[2] = imgLoader.loadImageFromFilePath("image/slime_right_normal_3.png");
		rightImage[3] = imgLoader.loadImageFromFilePath("image/slime_right_normal_2.png");
		playerImage.setRightImage(rightImage);

		backImage[0] = imgLoader.loadImageFromFilePath("image/slime_back_normal_1.png");
		backImage[1] = imgLoader.loadImageFromFilePath("image/slime_back_normal_2.png");
		backImage[2] = imgLoader.loadImageFromFilePath("image/slime_back_normal_3.png");
		backImage[3] = imgLoader.loadImageFromFilePath("image/slime_back_normal_2.png");
		playerImage.setBackImage(backImage);

		leftImage[0] = imgLoader.loadImageFromFilePath("image/slime_left_normal_1.png");
		leftImage[1] = imgLoader.loadImageFromFilePath("image/slime_left_normal_2.png");
		leftImage[2] = imgLoader.loadImageFromFilePath("image/slime_left_normal_3.png");
		leftImage[3] = imgLoader.loadImageFromFilePath("image/slime_left_normal_2.png");
		playerImage.setLeftImage(leftImage);

	}

	public void loadUnitFrameImage() {
		unitFrame = imgLoader.loadImageFromFilePath("image/itemFrame_normal.png");

		unitFrame_choose = new Image[Const_Game.CREATE_UNIT_ANIMATION_NUM.getCnt()];
		unitFrame_choose[0] = imgLoader.loadImageFromFilePath("image/itemFrame_chosen_1.png");
		unitFrame_choose[1] = imgLoader.loadImageFromFilePath("image/itemFrame_chosen_2.png");
		unitFrame_choose[2] = imgLoader.loadImageFromFilePath("image/itemFrame_chosen_3.png");
		unitFrame_choose[3] = imgLoader.loadImageFromFilePath("image/itemFrame_chosen_2.png");
	}

	public void loadCreateButtonImage() {
		button_save = new Image[Const_Game.CREATE_BUTTON_ANIMATION_NUM.getCnt()];
		button_open = new Image[Const_Game.CREATE_BUTTON_ANIMATION_NUM.getCnt()];
		button_title = new Image[Const_Game.CREATE_BUTTON_ANIMATION_NUM.getCnt()];

		button_save[0] = imgLoader.loadImageFromFilePath("image/button_save_normal.png");
		button_save[1] = imgLoader.loadImageFromFilePath("image/button_save_pressed.png");
		button_open[0] = imgLoader.loadImageFromFilePath("image/button_open_normal.png");
		button_open[1] = imgLoader.loadImageFromFilePath("image/button_open_pressed.png");
		button_title[0] = imgLoader.loadImageFromFilePath("image/button_title_normal.png");
		button_title[1] = imgLoader.loadImageFromFilePath("image/button_title_pressed.png");
	}

	public void loadTitleImage() {
		titleCreateButton = imgLoader.loadImageFromFilePath("image/title_create.png");
		titlePlayButton = imgLoader.loadImageFromFilePath("image/title_game.png");
		titleCursor = imgLoader.loadImageFromFilePath("image/title_select_cursor.png");
		titleBgImage = imgLoader.loadImageFromFilePath("image/title_bg.png");
	}

	public StageImage getStageImage(Const_Game stageState) {
		return stgImages[stageState.getCnt()];
	}

	public Image[] getStageUnitImage(Const_Game stageState, Const_Game unitState) {
		if(unitState == Const_Game.PLAYER) {
			return playerImage.getFrontImage();
		}
		return stgImages[stageState.getCnt()].getUnitImage(unitState);
	}

	public PlayerImage getPlayerImage() {
		return playerImage;
	}

	public Image getUnitFrameImage() {
		return unitFrame;
	}

	public Image[] getUnitFrameImage_Choose() {
		return unitFrame_choose;
	}

	public Image[] getCreateButtonImage(Const_Game buttonState) {
		switch(buttonState) {
		case CREATE_BUTTON_OPEN:
			return button_open;

		case CREATE_BUTTON_SAVE:
			return button_save;

		case CREATE_BUTTON_TITLE:
			return button_title;

		default:
			return null;
		}
	}

	public Image getTitleBgImage() {
		return titleBgImage;
	}

	public Image getTitleCreateButton() {
		return titleCreateButton;
	}

	public Image getTitlePlayButton() {
		return titlePlayButton;
	}

	public Image getTitleCursor() {
		return titleCursor;
	}

}
