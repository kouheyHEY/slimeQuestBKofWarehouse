package view.create;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controll.CreateController;
import model.sprite.unmovable.CreateButton;
import view.Const_Color;
import view.Const_Size;

public class CreateManageBar {

	public int barWidth = Const_Size.CREATE_TOOLBAR_SIZE.getCnt();
	public int barHeight = Const_Size.SCREEN_HEIGHT.getCnt() - Const_Size.CREATE_TOOLBAR_SIZE.getCnt();

	private int stroke = Const_Size.CREATE_TOOLBAR_STROKE.getCnt();
	private int miniStroke = Const_Size.CREATE_TOOLBAR_STROKE_MINI.getCnt();
	private CreateController createCtrl;

	public int panelX = Const_Size.CREATE_FIELD_WIDTH.getCnt();
	public int panelY = Const_Size.CREATE_TOOLBAR_SIZE.getCnt();

	public CreateManageBar(CreateController createCtrl) {
		this.createCtrl = createCtrl;

	}

	public void draw(Graphics g) {
		g.setColor(Const_Color.CREATE_COMPONENT_BG.getColor());
		g.fillRect(panelX, panelY, barWidth + panelX, barHeight + panelY);

		g.setColor(Const_Color.CREATE_COMPONENT_STROKE_LIGHT.getColor());
		((Graphics2D)g).setStroke(new BasicStroke(stroke));

		g.drawRect(
				(stroke / 2) + panelX,
				(stroke / 2) + panelY,
				barWidth - stroke,
				barHeight - stroke
				);

		g.setColor(Const_Color.CREATE_COMPONENT_STROKE_DARK.getColor());
		((Graphics2D)g).setStroke(new BasicStroke(miniStroke));
		g.drawRect(
				(miniStroke / 2) + panelX,
				(miniStroke / 2) + panelY,
				barWidth - miniStroke,
				barHeight - miniStroke
				);


		if(createCtrl == null) return;

		for(CreateButton cb : createCtrl.createButtonList) {
			cb.draw(g);
		}

		((Graphics2D)g).setStroke(new BasicStroke(1));
	}
}
