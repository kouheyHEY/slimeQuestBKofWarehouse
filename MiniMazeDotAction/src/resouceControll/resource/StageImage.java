package resouceControll.resource;

import java.awt.Image;

import controll.Const_Game;
import util.CommonUtil;

public class StageImage {

	private Image[] floor;
	private Image[] block;
	private Image[] hole;
	private Image[] box;
	private Image[] goal;

	public StageImage() {}
	public StageImage(Image[] floor, Image[] block, Image[] hole, Image[] box, Image[] goal) {
		this.floor = floor;
		this.block = block;
		this.hole = hole;
		this.box = box;
		this.goal = goal;
	}

	public void setFloorImage(Image[] FloorImages) {
		this.floor = (Image[]) CommonUtil.deepCopyImage(FloorImages);
	}

	public void setBlockImage(Image[] blockImages) {
		this.block = (Image[]) CommonUtil.deepCopyImage(blockImages);
	}

	public void setHoleImage(Image[] holeImages) {
		this.hole = (Image[]) CommonUtil.deepCopyImage(holeImages);
	}

	public void setBoxImage(Image[] boxImages) {
		this.box = (Image[]) CommonUtil.deepCopyImage(boxImages);
	}

	public void setGoalImage(Image[] goalImages) {
		this.goal = (Image[]) CommonUtil.deepCopyImage(goalImages);
	}

	public Image[] getUnitImage(Const_Game unitState) {
		switch(unitState) {
		case FLOOR:
			return floor;
		case BLOCK:
			return block;
		case HOLE:
			return hole;
		case BOX:
			return box;
		case GOAL:
			return goal;
		default:
			return null;
		}
	}

}
