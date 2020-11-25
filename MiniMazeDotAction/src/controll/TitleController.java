package controll;

import java.awt.Graphics;
import java.io.File;

import model.sprite.unmovable.ImageSprite;
import resouceControll.ImageManager;
import util.CommonUtil;
import util.FileUtil;
import view.Const_Size;

public class TitleController {
	public Const_Game selectedMode = Const_Game.CREATE;
	private int stateNum = Const_Game.GAME_STATE_NUM.getCnt();
	private int cursorPos = selectedMode.getCnt();
	private boolean cursorMoved;

	private int createButtonPosX = Const_Size.TITLE_CREATE_POS_X.getCnt();
	private int createButtonPosY = Const_Size.TITLE_CREATE_POS_Y.getCnt();
	private int createButtonWidth = Const_Size.TITLE_CREATE_WIDTH.getCnt();
	private int createButtonHeight = Const_Size.TITLE_CREATE_HEIGHT.getCnt();

	private int playButtonPosX = Const_Size.TITLE_PLAY_POS_X.getCnt();
	private int playButtonPosY = Const_Size.TITLE_PLAY_POS_Y.getCnt();
	private int playButtonWidth = Const_Size.TITLE_PLAY_WIDTH.getCnt();
	private int playButtonHeight = Const_Size.TITLE_PLAY_HEIGHT.getCnt();

	private int titleCursorWidth = Const_Size.TITLLE_CURSOR_WIDTH.getCnt();
	private int titleCursorHeight = Const_Size.TITLLE_CURSOR_HEIGHT.getCnt();

	private int createButtonCursorPosX = createButtonPosX + createButtonWidth + titleCursorWidth / 2;
	private int createButtonCursorPosY = createButtonPosY + (createButtonHeight - titleCursorHeight) / 2;
	private int playButtonCursorPosX = playButtonPosX + playButtonWidth + titleCursorWidth / 2;
	private int playButtonCursorPosY = playButtonPosY + (playButtonHeight - titleCursorHeight) / 2;

	public ImageSprite createButton;
	public ImageSprite playButton;
	public ImageSprite titleCursor;
	public ImageSprite titleBg;

	private KeyController keyCtrl;
	private ImageManager imgMng = new ImageManager();

	private MainController mainCtrl;

	public TitleController(MainController mainCtrl) {
		selectedMode = Const_Game.CREATE;
		imgMng.loadTitleImage();

		createButton = new ImageSprite(
				createButtonPosX,
				createButtonPosY,
				createButtonWidth,
				createButtonHeight,
				imgMng.getTitleCreateButton()
				);

		playButton = new ImageSprite(
				playButtonPosX,
				playButtonPosY,
				playButtonWidth,
				playButtonHeight,
				imgMng.getTitlePlayButton()
				);

		titleCursor = new ImageSprite(
				createButtonCursorPosX,
				createButtonCursorPosY,
				titleCursorWidth,
				titleCursorHeight,
				imgMng.getTitleCursor()
				);

		titleBg = new ImageSprite(
				0,
				0,
				Const_Size.SCREEN_WIDTH.getCnt(),
				Const_Size.SCREEN_HEIGHT.getCnt(),
				imgMng.getTitleBgImage()
				);

		this.mainCtrl = mainCtrl;
	}

	public void setKeyCtrl(KeyController keyCtrl) {
		this.keyCtrl = keyCtrl;
	}

	private void changeCursorPos(int changeDir) {
		cursorPos += changeDir;

		cursorPos = Math.max(1, cursorPos);
		cursorPos = Math.min(cursorPos, stateNum);

		selectedMode = getSelectedState(cursorPos);

		if(!cursorMoved && changeDir != 0) {
			switch(selectedMode) {
			case CREATE:
				titleCursor.setX(createButtonCursorPosX);
				titleCursor.setY(createButtonCursorPosY);
				break;
			case PLAY:
				titleCursor.setX(playButtonCursorPosX);
				titleCursor.setY(playButtonCursorPosY);
				break;
			default:
				break;
			}
		}

		cursorMoved = keyCtrl.keyPressed;

	}

	public void drawImages(Graphics g) {
		titleBg.draw(g);
		createButton.draw(g);
		playButton.draw(g);
		titleCursor.draw(g);
	}

	public void update() {
		int changeDir = 0;
		if(keyCtrl.keyMap.get("RIGHT"))
			changeDir = 1;
		else if(keyCtrl.keyMap.get("LEFT"))
			changeDir = -1;

		changeCursorPos(changeDir);
		titleCursor.updateFloat();

		if(keyCtrl.keyMap.get("ENTER")) {
			switch(selectedMode) {
			case PLAY:
				File selectedFile = FileUtil.chooseFile(".", "mapsファイル", "maps", null);
				if(selectedFile != null) {
					mainCtrl.gameState = selectedMode;
					mainCtrl.setMapFile(selectedFile);
				}else {
					CommonUtil.AlertWithMessage(
							"ファイルの取得に失敗しました。",
							mainCtrl.mainFrame
							);
				}

				break;

			case CREATE:
				mainCtrl.gameState = selectedMode;
				break;
			default:
				break;
			}
		}
	}

	private Const_Game getSelectedState(int selectedStateCnt) {
		switch(selectedStateCnt) {
		case 1:
			return Const_Game.CREATE;
		case 2:
			return Const_Game.PLAY;
		default:
			return Const_Game.PLAY;
		}
	}
}
