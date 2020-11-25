package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel {
	protected static int WIDTH;
	protected static int HEIGHT;
	protected static int UNITSIZE = Const_Size.UNIT_BASESIZE.getCnt();

	protected Image buff;
	protected Graphics buff_g;

	public BasePanel(int width, int height) {

		WIDTH = width;
		HEIGHT = height;

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.GREEN);
		this.setVisible(true);
	}

	public void createBuffer(int bufferWidth, int bufferHeight) {
		buff = createImage(bufferWidth, bufferHeight);
		buff_g = buff.getGraphics();
	}
}
