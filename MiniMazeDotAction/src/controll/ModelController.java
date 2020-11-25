package controll;

import java.awt.Image;
import java.util.ArrayList;

import model.Map;
import model.sprite.Sprite;
import model.sprite.movable.Box;
import model.sprite.movable.MovableSprite;
import model.sprite.movable.Player;
import model.sprite.unmovable.Block;
import model.sprite.unmovable.Goal;
import model.sprite.unmovable.Hole;
import resouceControll.ImageManager;
import util.CommonUtil;
import view.Const_Size;

public class ModelController {
	public ArrayList<Sprite> spriteList;
	public ArrayList<Integer> boxIndices = new ArrayList<Integer>();

	public Map map;
	public Player player;
	public Const_Game stageState;
	private MainController mainCtrl;

	private int unitSize = Const_Size.UNIT_BASESIZE.getCnt();
	private ImageManager imgMng = new ImageManager();
	private KeyController keyCtrl;

	private int centerX;
	private int centerY;
	public int offsetX = 0;
	public int offsetY = 0;

	public int mapWidth;
	public int mapHeight;

	public ModelController(Map map, Const_Game stageState, MainController mainCtrl) {
		this.map = map;
		this.mainCtrl = mainCtrl;
		this.stageState = stageState;
		imgMng.loadStgImage(stageState);
		imgMng.loadPlayerImage();

		centerX = (Const_Size.SCREEN_WIDTH.getCnt() - unitSize) / 2;
		centerY = (Const_Size.SCREEN_HEIGHT.getCnt() - unitSize) / 2;

		mapWidth = map.getMapCol() * unitSize;
		mapHeight = map.getMapRow() * unitSize;
	}

	public void setKeyCtrl(KeyController keyCtrl) {
		this.keyCtrl = keyCtrl;
	}

	public void loadRealMap() {
		spriteList = new ArrayList<Sprite>();
		for(int i = 0; i < map.getMapRow(); i++) {
			for(int j = 0; j < map.getMapCol(); j++) {
				Sprite sprite = null;
				if(map.map[i][j] == Const_Game.BLOCK.getCnt()) {
					sprite = new Block(
							j * unitSize,
							i * unitSize,
							unitSize,
							unitSize,
							imgMng.getStageUnitImage(stageState, Const_Game.BLOCK)
							);
				}else if(map.map[i][j] == Const_Game.HOLE.getCnt()) {
					sprite = new Hole(
							j * unitSize,
							i * unitSize,
							unitSize,
							unitSize,
							imgMng.getStageUnitImage(stageState, Const_Game.HOLE)
							);
				}else if(map.map[i][j] == Const_Game.BOX.getCnt()) {
					sprite = new Box(
							j * unitSize,
							i * unitSize,
							unitSize,
							unitSize,
							imgMng.getStageUnitImage(stageState, Const_Game.BOX)
							);
					boxIndices.add(spriteList.size());
				}else if(map.map[i][j] == Const_Game.GOAL.getCnt()) {
					sprite = new Goal(
							j * unitSize,
							i * unitSize,
							unitSize,
							unitSize,
							imgMng.getStageUnitImage(stageState, Const_Game.GOAL)
							);
				}else{
					if(map.map[i][j] == Const_Game.PLAYER.getCnt()) {
						player = new Player(
								j * unitSize,
								i * unitSize,
								Const_Size.PLAYER_WIDTH.getCnt(),
								Const_Size.PLAYER_HEIGHT.getCnt(),
								imgMng.getPlayerImage()
								);
					}
				}
				spriteList.add(sprite);
			}
		}
	}


	public void update() {
		if(keyCtrl.keyMap.get("ESC")) {
			int returnTitleAns = CommonUtil.confirmDialog("タイトル画面に戻りますか？", null);
			switch(returnTitleAns) {
			case 1:
				mainCtrl.gameState = Const_Game.TITLE;
				break;
			default:
				break;
			}
		}

		if(keyCtrl.keyMap.get("UP")) {
			player.moveBack();
		}else if(keyCtrl.keyMap.get("RIGHT")) {
			player.moveRight();
		}else if(keyCtrl.keyMap.get("DOWN")) {
			player.moveFront();
		}else if(keyCtrl.keyMap.get("LEFT")) {
			player.moveLeft();
		}

		player.move();

		for(Sprite sprite : spriteList) {
			if(sprite == null) continue;
			boolean collidePlayer = player.collideSprite(sprite);

			/* プレイヤーの位置調整 */
			if(collidePlayer && !sprite.canWalk) {
				player.stop();
				switch(player.direction) {
				case FRONT:
					player.newY = (sprite.getY() - player.getHeight());
					break;
				case BACK:
					player.newY = (sprite.getY() + sprite.getHeight());
					break;
				case RIGHT:
					player.newX = (sprite.getX() - player.getWidth());
					break;
				case LEFT:
					player.newX = (sprite.getX() + sprite.getWidth());
					break;
				default:
					break;
				}
			}

			/* 箱にぶつかったとき */
			if(sprite instanceof Box) {
				Box box = (Box)sprite;
				int[] boxPoint = box.getPoint();
				Sprite nextSprite = null;

				if(collidePlayer) {
					box.direction = player.direction;
					switch(box.direction) {
					case FRONT:
						box.nextSpriteIndex = (boxPoint[1] + 1) * map.getMapCol() + boxPoint[0];
						break;
					case BACK:
						box.nextSpriteIndex = (boxPoint[1] - 1) * map.getMapCol() + boxPoint[0];
						break;
					case RIGHT:
						box.nextSpriteIndex = boxPoint[1] * map.getMapCol() + (boxPoint[0] + 1);
						break;
					case LEFT:
						box.nextSpriteIndex = boxPoint[1] * map.getMapCol() + (boxPoint[0] - 1);
						break;
					default:
						break;
					}

					if(box.nextSpriteIndex < spriteList.size()) {
						nextSprite = spriteList.get(box.nextSpriteIndex);
					}

					if(nextSprite instanceof Hole || nextSprite == null) {
						box.setSpeed();
					}

				}

				if(box.nextSpriteIndex < spriteList.size()) {
					nextSprite = spriteList.get(box.nextSpriteIndex);
				}

				box.move();

				if(box.move) {
					player.stop();
					player.direction = box.direction;
				}

				box.update();

				if(box.getX() % unitSize == 0 && box.getY() % unitSize == 0) {

					box.stop();

					if(nextSprite instanceof Hole && !nextSprite.canWalk) {
						int boxIndex = spriteList.indexOf(box);
						spriteList.set(boxIndex, null);

						((Hole)nextSprite).underBox = true;
					}else if(nextSprite == null || nextSprite.canWalk) {
						int boxCurrentIndex = spriteList.indexOf(box);
						spriteList.set(box.nextSpriteIndex, box);
						spriteList.set(boxCurrentIndex, null);
						boxIndices.set(
								boxIndices.indexOf(boxCurrentIndex),
								box.nextSpriteIndex
								);
					}else {

					}
				}

			}else if(sprite instanceof Block) {
//				Block block = (Block)sprite;

				if(collidePlayer) {
				}
			}else if(sprite instanceof Goal) {
//				Goal goal = (Goal)sprite;

				if(collidePlayer) {
					keyCtrl.resetKeyState();
					gameClear();
				}
			}else if(sprite instanceof Hole) {
				Hole hole = (Hole)sprite;

				if(collidePlayer) {
				}

				hole.update();
			}
		}

		adjustStageFrame(player);

		player.update();

		offsetX = player.getX() - centerX;
		offsetX = Math.max(0, offsetX);
		offsetX = Math.min(offsetX, map.getMapCol() * unitSize - Const_Size.SCREEN_WIDTH.getCnt());

		offsetY = player.getY() - centerY;
		offsetY = Math.max(0, offsetY);
		offsetY = Math.min(offsetY, map.getMapRow() * unitSize - Const_Size.SCREEN_HEIGHT.getCnt());
	}

	private boolean adjustStageFrame(MovableSprite ms) {
		if(ms.newX < 0 ||
				ms.newX + ms.getWidth() > mapWidth ||
				ms.newY < 0 ||
				ms.newY + ms.getHeight() > mapHeight
				) {

			switch(ms.direction) {
			case FRONT:
				ms.newY = mapHeight - ms.getHeight();
				break;
			case BACK:
				ms.newY = 0;
				break;
			case RIGHT:
				ms.newX = mapWidth - ms.getWidth();
				break;
			case LEFT:
				ms.newX = 0;
				break;
			default:
				break;
			}
			return true;
		}
		return false;
	}

	private void gameClear() {
		CommonUtil.AlertWithMessage("ゲームクリア！！", null);
		mainCtrl.gameState = Const_Game.TITLE;
	}

	public Image getFloorImage(int floorPtrn) {
		return imgMng.getStageUnitImage(stageState, Const_Game.FLOOR)[floorPtrn];
	}
}
