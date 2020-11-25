package controll;

import java.io.File;

import model.Map;
import util.FileUtil;
import view.MainFrame;

public class MainController {

	private ModelController modelCtrl;
	private CreateController createCtrl;
	private TitleController titleCtrl;

	private Const_Game stageState = Const_Game.GRASS;
	public Const_Game gameState = Const_Game.TITLE;

	private Map map;
	public MainFrame mainFrame;
	private File selectedFile;

	public MainController() {

		mainFrame = new MainFrame();

		manageGameState();

	}

	public void manageGameState() {
		while(true) {
			switch(gameState) {
			case TITLE:
				runTitleMode();
				break;

			case PLAY:
				runGameMode();
				break;

			case CREATE:
				runCreateMode();
				break;

			default:
				break;
			}
		}
	}

	public void runTitleMode() {
		titleCtrl = new TitleController(this);
		mainFrame.startTitleMode(titleCtrl);

		while(gameState == Const_Game.TITLE) {
			titleCtrl.update();
			mainFrame.drawTitlePanel();

			sleepThread();
		}

	}

	public void runGameMode() {
		map = FileUtil.loadUnitMapFromFile(selectedFile.getPath());
		modelCtrl = new ModelController(map, stageState, this);
		modelCtrl.loadRealMap();
		mainFrame.startGameMode(modelCtrl);

		while(gameState == Const_Game.PLAY) {

			modelCtrl.update();
			mainFrame.drawGamePanel();

			sleepThread();
		}
	}

	public void runCreateMode() {
		createCtrl = new CreateController(stageState, this);
		mainFrame.startCreateMode(createCtrl);

		createCtrl.setMouseListener(mainFrame.getCreatePanel());
		while(gameState == Const_Game.CREATE) {

			createCtrl.update();
			mainFrame.drawCreatePanel();

			sleepThread();
		}
	}

	public void createMap_Random(int row, int col) {
		map.init(row, col);
		map.createRandomMap();
	}

	public void createMap_FromTextFile(String filePath) {
		map = FileUtil.loadUnitMapFromFile(filePath);
	}

	public static void sleepThread() {
		try {
			Thread.sleep(Const_Game.THREAD_SLEEP_TIME.getCnt());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setMapFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
}
