package view.create;

import java.awt.Color;
import java.awt.Graphics;

import controll.CreateController;
import view.BasePanel;

public class CreatePanel extends BasePanel {

//	private int TOOLBARSIZE = Const_Size.CREATE_TOOLBAR_SIZE.getCnt();
	private CreateController createCtrl;
	private UnitSelectBar usBar;
	private CreateField createField;
	private CreateManageBar cmBar;

	public CreatePanel(int width, int height, CreateController createCtrl) {
		super(width, height);
		this.createCtrl = createCtrl;
		usBar = new UnitSelectBar(createCtrl);
		createField = new CreateField(createCtrl);
		cmBar = new CreateManageBar(createCtrl);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(buff == null) {
			return;
		}

		buff_g.setColor(Color.white);
		buff_g.fillRect(0, 0, WIDTH, HEIGHT);

		cmBar.draw(buff_g);
		usBar.draw(buff_g);
		createField.draw(buff_g);

		if(createCtrl.selectedUnitState != null) {
			buff_g.drawImage(
					createCtrl.getselectedUnitImage(),
					createCtrl.mouseX,
					createCtrl.mouseY,
					createCtrl.unitSmallSize,
					createCtrl.unitSmallSize,
					this
					);
		}

		g.drawImage(buff, 0, 0, this);

	}

}
