package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controll.TitleController;

public class TitlePanel extends JPanel{
	private static int WIDTH = Const_Size.SCREEN_WIDTH.getCnt();
	private static int HEIGHT = Const_Size.SCREEN_HEIGHT.getCnt();

	private TitleController titleCtrl;

	public TitlePanel(TitleController titleCtrl) {
		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.WHITE);

		this.titleCtrl = titleCtrl;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		g.fillRect(0, 0, HEIGHT, HEIGHT);

		titleCtrl.drawImages(g);

	}
}
