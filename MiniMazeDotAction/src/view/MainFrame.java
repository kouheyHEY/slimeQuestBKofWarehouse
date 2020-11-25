package view;

import javax.swing.JFrame;

import controll.Const_Game;
import controll.CreateController;
import controll.KeyController;
import controll.ModelController;
import controll.TitleController;
import resouceControll.load.ImageLoader;
import view.create.CreatePanel;

public class MainFrame extends JFrame {
	public Const_Game panelState;

	private GamePanel gameP;
	private TitlePanel titleP;
	private CreatePanel createP;

	private KeyController keyCtrl;

	public MainFrame() {
		setTitle("すらいむのそうこぼうけんたん");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		keyCtrl = new KeyController();
		addKeyListener(keyCtrl);

		setIconImage(
				(new ImageLoader()).loadImageFromFilePath("image/slime_front_normal_1.png")
				);
	}

	public void startTitleMode(TitleController titleCtrl) {
		getContentPane().removeAll();
		titleCtrl.setKeyCtrl(keyCtrl);
		titleP = new TitlePanel(titleCtrl);

		add(titleP);
		setVisible(true);

		pack();
	}

	public void drawTitlePanel() {
		titleP.repaint();
	}

	public void startGameMode(ModelController modelCtrl){
		getContentPane().removeAll();
		modelCtrl.setKeyCtrl(keyCtrl);
		gameP = new GamePanel(
				Const_Size.SCREEN_WIDTH.getCnt(),
				Const_Size.SCREEN_HEIGHT.getCnt(),
				modelCtrl
				);

		add(gameP);
		setVisible(true);

		pack();

		gameP.createBuffer(modelCtrl.mapWidth, modelCtrl.mapHeight);
	}

	public void drawGamePanel() {
		gameP.repaint();
	}

	public void startCreateMode(CreateController createCtrl) {
		getContentPane().removeAll();

		createP = new CreatePanel(
				Const_Size.SCREEN_WIDTH.getCnt(),
				Const_Size.SCREEN_HEIGHT.getCnt(),
				createCtrl
				);

		add(createP);
		setVisible(true);

		pack();

		createP.createBuffer(
				Const_Size.SCREEN_WIDTH.getCnt(),
				Const_Size.SCREEN_HEIGHT.getCnt()
				);
	}

	public void drawCreatePanel() {
		createP.repaint();
	}

	public CreatePanel getCreatePanel() {
		return createP;
	}

}
