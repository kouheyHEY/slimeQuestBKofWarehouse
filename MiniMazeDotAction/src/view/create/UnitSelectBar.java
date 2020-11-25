package view.create;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controll.CreateController;
import model.sprite.unmovable.SpriteIcon;
import view.Const_Color;
import view.Const_Size;

public class UnitSelectBar{

	public int barWidth = Const_Size.SCREEN_WIDTH.getCnt();
	public int barHeight = Const_Size.CREATE_TOOLBAR_SIZE.getCnt();

	private int stroke = Const_Size.CREATE_TOOLBAR_STROKE.getCnt();
	private int miniStroke = Const_Size.CREATE_TOOLBAR_STROKE_MINI.getCnt();
	private CreateController createCtrl;

	public UnitSelectBar(CreateController createCtrl) {
		this.createCtrl = createCtrl;

	}

	public void draw(Graphics g) {
		g.setColor(Const_Color.CREATE_COMPONENT_BG.getColor());
		g.fillRect(0, 0, barWidth, barHeight);

		if(createCtrl != null) {

			for(SpriteIcon spriteIcon : createCtrl.unitList) {
				spriteIcon.setOffset(createCtrl.toolBarOffset);
				spriteIcon.draw(g);
			}
		}

		g.setColor(Const_Color.CREATE_COMPONENT_STROKE_LIGHT.getColor());
		((Graphics2D)g).setStroke(new BasicStroke(stroke));
		g.drawRect(
				(stroke / 2),
				(stroke / 2),
				barWidth - stroke,
				barHeight - stroke
				);

		g.setColor(Const_Color.CREATE_COMPONENT_STROKE_DARK.getColor());
		((Graphics2D)g).setStroke(new BasicStroke(miniStroke));
		g.drawRect(
				(miniStroke / 2),
				(miniStroke / 2),
				barWidth - miniStroke,
				barHeight - miniStroke
				);


		((Graphics2D)g).setStroke(new BasicStroke(1));
	}
}
