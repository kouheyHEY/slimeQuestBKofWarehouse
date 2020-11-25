package view;

import java.awt.Graphics;

import controll.ModelController;
import model.sprite.Sprite;
import model.sprite.movable.Box;
import model.sprite.unmovable.Block;
import model.sprite.unmovable.Goal;
import model.sprite.unmovable.Hole;

public class GamePanel extends BasePanel{

	protected ModelController modelCtrl;

	public GamePanel(int width, int height, ModelController modelCtrl) {
		super(width, height);

		this.modelCtrl = modelCtrl;

	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(buff == null) {
			return;
		}

		for(int i = 0; i < modelCtrl.map.getMapRow(); i++) {
			for(int j = 0; j < modelCtrl.map.getMapCol(); j++) {
				buff_g.drawImage(
						modelCtrl.getFloorImage(modelCtrl.map.floorMap[i][j]),
						j * UNITSIZE,
						i * UNITSIZE,
						null
						);
			}
		}

		for(Sprite sprite : modelCtrl.spriteList) {
			if(sprite == null) continue;
			if(sprite instanceof Block) {
				((Block) sprite).draw(buff_g);
			}else if(sprite instanceof Hole) {
				((Hole) sprite).draw(buff_g);
			}else if(sprite instanceof Goal) {
				((Goal) sprite).draw(buff_g);
			}
		}

		for(int i : modelCtrl.boxIndices) {
			Box box = (Box)modelCtrl.spriteList.get(i);
			if(box != null) ((Box)modelCtrl.spriteList.get(i)).draw(buff_g);
		}

		modelCtrl.player.draw(buff_g);

		g.drawImage(buff,
				0,
				0,
				WIDTH,
				HEIGHT,
				+ modelCtrl.offsetX,
				+ modelCtrl.offsetY,
				+ modelCtrl.offsetX + WIDTH,
				+ modelCtrl.offsetY + HEIGHT,
				this
				);
	}
}
