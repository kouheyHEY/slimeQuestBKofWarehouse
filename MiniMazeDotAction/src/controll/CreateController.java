package controll;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import model.Map;
import model.sprite.unmovable.CreateButton;
import model.sprite.unmovable.SpriteIcon;
import resouceControll.ImageManager;
import util.CommonUtil;
import util.FileUtil;
import view.Const_Size;
import view.create.CreatePanel;

public class CreateController implements MouseInputListener{

	public int unitBaseSize = Const_Size.UNIT_BASESIZE.getCnt();
	public int unitSmallSize = Const_Size.UNIT_SMALLSIZE.getCnt();
	private int unitFrame_Pos = Const_Size.CREATE_UNIT_FRAME_POS.getCnt();
	private int unitFrame_Size = Const_Size.CREATE_UNIT_FRAME_SIZE.getCnt();

	public Const_Game selectedUnitState;
	private Const_Game stageState;

	private ImageManager imgMng = new ImageManager();
//	private KeyController keyCtrl = null;
	private MainController mainCtrl;

	public int mouseX;
	public int mouseY;
	public boolean isMousePressed;
	private boolean canPutUnit;

	public ArrayList<SpriteIcon> unitList;
	public ArrayList<CreateButton> createButtonList;
	public Const_Game[][] miniField;
	public int[][] miniFloorField;

	public int createFieldWidth;
	public int createFieldHeight;

	public int toolBarOffset = 0;
	public int toolBarOffsetLimit;

	private Point playerPos = new Point(-1, -1);
	private Point goalPos = new Point(-1, -1);

	public CreateController(Const_Game stageState, MainController mainCtrl) {
		this.stageState = stageState;
		imgMng.loadUnitFrameImage();
		imgMng.loadStgImage(stageState);
		imgMng.loadPlayerImage();
		imgMng.loadCreateButtonImage();
		this.mainCtrl = mainCtrl;

		/* setCreateScreen */
		setCreateField(7, 7);

		Const_Game[] unitStatus = {
				Const_Game.BLOCK,
				Const_Game.HOLE,
				Const_Game.BOX,
				Const_Game.GOAL,
				Const_Game.PLAYER
		};

		unitList = new ArrayList<SpriteIcon>() {{
			int unitFrameSpace = unitFrame_Pos + unitFrame_Size;
			for(int i = 0; i < unitStatus.length; i++) {
				add(new SpriteIcon(
						i * unitFrameSpace + unitFrame_Pos,
						unitFrame_Pos,
						unitFrame_Size,
						unitFrame_Size,
						imgMng.getStageUnitImage(
								stageState,
								unitStatus[i]
								),
						imgMng.getUnitFrameImage(),
						imgMng.getUnitFrameImage_Choose(),
						unitStatus[i])

						);
				toolBarOffsetLimit += unitFrameSpace;
			}
			toolBarOffsetLimit += unitFrame_Pos;
			toolBarOffsetLimit -= Const_Size.SCREEN_WIDTH.getCnt();
		}};


		Const_Game[] buttonStatus = {
				Const_Game.CREATE_BUTTON_OPEN,
				Const_Game.CREATE_BUTTON_SAVE,
				Const_Game.CREATE_BUTTON_TITLE
		};

		createButtonList = new ArrayList<CreateButton>() {{
			int buttonPosX = Const_Size.CREATE_BUTTON_POS_X.getCnt();
			int buttonPosY = Const_Size.CREATE_BUTTON_POS_Y.getCnt();
			int buttonSpace = Const_Size.CREATE_BUTTON_SPACE.getCnt();
			int buttonWidth = Const_Size.CREATE_BUTTON_WIDTH.getCnt();
			int buttonHeight = Const_Size.CREATE_BUTTON_HEIGHT.getCnt();

			int buttonPosSpace = buttonSpace + buttonHeight;

			for(int i = 0; i < buttonStatus.length; i++) {
				add(new CreateButton(
						buttonPosX,
						i * buttonPosSpace + buttonPosY + buttonSpace,
						buttonWidth,
						buttonHeight,
						imgMng.getCreateButtonImage(buttonStatus[i]),
						buttonStatus[i])
						);

			}

		}};

	}

	private void setCreateField(int width, int height) {
		createFieldWidth = width;
		createFieldHeight = height;

		miniField = new Const_Game[createFieldHeight][createFieldWidth];
		miniFloorField = new int[createFieldHeight][createFieldWidth];
		for(int y = 0; y < createFieldHeight; y++) {
			for(int x = 0; x < createFieldWidth; x++) {
				miniField[y][x] = Const_Game.FLOOR;
				miniFloorField[y][x] = CommonUtil.randInt(0, 1);
			}
		}
	}

	public Image getFloorImage(int x, int y) {
		return imgMng.getStageUnitImage(stageState, Const_Game.FLOOR)[miniFloorField[y][x]];
	}

	public void setMouseListener(CreatePanel cp) {
		cp.addMouseListener(this);
		cp.addMouseMotionListener(this);
	}

//	public void setKeyCtrl(KeyController keyCtrl) {
//		this.keyCtrl = keyCtrl;
//	}

	public Image getselectedUnitImage() {
		return imgMng.getStageUnitImage(stageState, selectedUnitState)[0];
	}

	public Image getGridUnitImage(int x, int y) {
		if(miniField[y][x] == Const_Game.PLAYER) {
			return imgMng.getPlayerImage().getFrontImage()[0];
		}else{
			return imgMng.getStageUnitImage(stageState, miniField[y][x])[0];
		}
	}

	public void update() {

		for(SpriteIcon si : unitList) {
			si.update();
		}

		for(CreateButton cb : createButtonList) {
			cb.update();
		}

		if(mouseY < Const_Size.CREATE_TOOLBAR_SIZE.getCnt()) {
			if(mouseX <= unitFrame_Size) {
				setOffset(-1);
			}else if(mouseX >= Const_Size.SCREEN_WIDTH.getCnt() - unitFrame_Size) {
				setOffset(1);
			}
		}
	}

	private void putUnitOnField() {
		int fieldMouseX = mouseX;
		int fieldMouseY = mouseY - Const_Size.CREATE_TOOLBAR_SIZE.getCnt();

		int fieldMouseGridX = fieldMouseX / unitSmallSize;
		int fieldMouseGridY = fieldMouseY / unitSmallSize;


		if(canPutUnit) {
			if( fieldMouseGridX >= 0 &&
					fieldMouseGridY >= 0 &&
					fieldMouseGridX < miniField[0].length &&
					fieldMouseGridY < miniField.length
					) {

				if(miniField[fieldMouseGridY][fieldMouseGridX] == Const_Game.FLOOR) {
					if(selectedUnitState != null) {

						switch(selectedUnitState) {
						case PLAYER:
							if(playerPos.x != -1) {
								miniField[playerPos.y][playerPos.x] = Const_Game.FLOOR;
							}
							playerPos.x = fieldMouseGridX;
							playerPos.y = fieldMouseGridY;
							break;
						case GOAL:
							if(goalPos.x != -1) {
								miniField[goalPos.y][goalPos.x] = Const_Game.FLOOR;
							}
							goalPos.x = fieldMouseGridX;
							goalPos.y = fieldMouseGridY;
							break;

						default:
							break;
						}

						miniField[fieldMouseGridY][fieldMouseGridX] = selectedUnitState;
					}
				}else {
					if(selectedUnitState == null) {

						switch(miniField[fieldMouseGridY][fieldMouseGridX]) {
						case PLAYER:
							playerPos.x = -1;
							playerPos.y = -1;
							break;

						case GOAL:
							goalPos.x = -1;
							goalPos.y = -1;
							break;
						default:
							break;
						}

						miniField[fieldMouseGridY][fieldMouseGridX] = Const_Game.FLOOR;
					}
				}

			}

		}
	}

	public void setOffset(int dir) {
		toolBarOffset = toolBarOffset + dir * 8;
		toolBarOffset = Math.min(toolBarOffset, toolBarOffsetLimit);
		toolBarOffset = Math.max(0, toolBarOffset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		isMousePressed = true;

		if(mouseY > Const_Size.CREATE_TOOLBAR_SIZE.getCnt() &&
				mouseX < Const_Size.CREATE_FIELD_WIDTH.getCnt()
				) {
			canPutUnit = true;

		}else {
			canPutUnit = false;
			boolean clickedIcon = false;

			for(SpriteIcon si : unitList) {
				if(si.isClicked(mouseX, mouseY)) {
					if(selectedUnitState == si.unitState) {
						selectedUnitState = null;
						si.isSelected = false;
					}else {
						selectedUnitState = si.unitState;
						si.isSelected = true;
					}
					clickedIcon = true;
				}
			}

			selectedUnitState = clickedIcon ? selectedUnitState : null;


			for(CreateButton cb : createButtonList) {
				if(cb.isPressed(mouseX, mouseY)) {

					switch(cb.buttonState) {
					case CREATE_BUTTON_OPEN:
						break;
					case CREATE_BUTTON_SAVE:
						break;
					default:
						break;
					}
				}
			}

		}

		putUnitOnField();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMousePressed = false;

		for(CreateButton cb : createButtonList) {
			if(cb.isPressed && cb.isReleased(mouseX, mouseY)) {

				switch(cb.buttonState) {
				case CREATE_BUTTON_OPEN:
					if(CommonUtil.confirmDialog("現在作成しているマップを保存せず、新しいマップを開きますか？", null) != 1) {
						CommonUtil.AlertWithMessage("マップを保存してから新しいマップを開いてください", null);
						break;
					}

					File mapFile = FileUtil.chooseFile(".", "mapsファイル", "maps", mainCtrl.mainFrame);
					if(mapFile != null) {
						Map newMap = FileUtil.loadUnitMapFromFile(mapFile.getPath());
						miniField = CommonUtil.changeToUnitArray(newMap.map);
						stageState = newMap.stageState;
					}else {
						CommonUtil.AlertWithMessage("マップの読み込みに失敗しました", null);
					}
					break;

				case CREATE_BUTTON_SAVE:
					if(playerPos.x == -1) {
						CommonUtil.AlertWithMessage("プレイヤーの初期位置を設定してください", null);
						break;
					}
					if(goalPos.x == -1) {
						CommonUtil.AlertWithMessage("ゴールの位置を設定してください", null);
						break;
					}

					int[][] fieldIntArray = CommonUtil.changeToIntArray(miniField);
					String fileName = FileUtil.saveFile(".", "mapsファイル", "maps", mainCtrl.mainFrame, this);
					if(fileName != null) {
						File saveFile = new File(fileName);
						if(FileUtil.saveMapToFile(saveFile, fieldIntArray, stageState)) {
							mainCtrl.gameState = Const_Game.TITLE;
						}
					}else {

						CommonUtil.AlertWithMessage("マップの保存に失敗しました", null);
					}

					break;

				case CREATE_BUTTON_TITLE:
					switch(CommonUtil.confirmDialog("マップを保存せずタイトルに戻りますか？", null)) {
					case 1:
						mainCtrl.gameState = Const_Game.TITLE;
						break;
					case -1:
						CommonUtil.AlertWithMessage("マップを保存してからタイトルに戻ってください", null);
						break;
					default:
						break;
					}

					break;

				default:
					break;
				}

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		putUnitOnField();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
