package view.create;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controll.Const_Game;
import controll.CreateController;
import view.Const_Size;

public class CreateField{
	public int offsetX = 0;
	public int offsetY = 0;

	public int ScreenWidth = Const_Size.CREATE_FIELD_WIDTH.getCnt();
	public int ScreenHeight = Const_Size.CREATE_FIELD_HEIGHT.getCnt();

	public int panelX = 0;
	public int panelY = Const_Size.CREATE_TOOLBAR_SIZE.getCnt();

	public CreateController createCtrl;

	public CreateField(CreateController createCtrl) {
		this.createCtrl = createCtrl;
	}

	public void draw(Graphics g) {

		g.setColor(Color.gray);
		((Graphics2D)g).setStroke(new BasicStroke(1));

		/* drawMiniField */
		for(int y = 0; y < createCtrl.createFieldHeight; y++) {
			for(int x = 0; x < createCtrl.createFieldWidth; x++) {
				g.drawImage(
						createCtrl.getFloorImage(x, y),
						x * createCtrl.unitSmallSize + panelX,
						y * createCtrl.unitSmallSize + panelY,
						createCtrl.unitSmallSize,
						createCtrl.unitSmallSize,
						null
						);
			}
		}

		for(int y = 0; y < createCtrl.createFieldHeight; y++) {
			for(int x = 0; x < createCtrl.createFieldWidth; x++) {
				if(createCtrl.miniField[y][x] != Const_Game.FLOOR) {
					g.drawImage(
							createCtrl.getGridUnitImage(x, y),
							x * createCtrl.unitSmallSize + panelX,
							y * createCtrl.unitSmallSize + panelY,
							createCtrl.unitSmallSize,
							createCtrl.unitSmallSize,
							null
							);
				}
			}
		}

		g.drawRect(panelX, panelY, ScreenWidth + panelX, ScreenHeight + panelY);

		for(int y = 0; y < createCtrl.createFieldHeight; y++) {
			g.drawLine(panelX, y * createCtrl.unitSmallSize + panelY, ScreenWidth + panelX, y * createCtrl.unitSmallSize + panelY);
		}

		for(int x = 0; x < createCtrl.createFieldWidth; x++) {
			g.drawLine(x * createCtrl.unitSmallSize + panelX, panelY, x * createCtrl.unitSmallSize + panelX, ScreenHeight + panelY);
		}



	}
}
