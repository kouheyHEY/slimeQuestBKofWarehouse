package controll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyController implements KeyListener {

	public HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>(){
		{
			put("UP", false);
			put("DOWN", false);
			put("RIGHT", false);
			put("LEFT", false);

			put("Z", false);
			put("X", false);

			put("ENTER", false);
			put("ESC", false);
		}
	};
	public boolean keyPressed;

	public KeyController() {}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyMap.put("UP", true);
			break;
		case KeyEvent.VK_RIGHT:
			keyMap.put("RIGHT", true);
			break;
		case KeyEvent.VK_DOWN:
			keyMap.put("DOWN", true);
			break;
		case KeyEvent.VK_LEFT:
			keyMap.put("LEFT", true);
			break;
		case KeyEvent.VK_ENTER:
			keyMap.put("ENTER", true);
			break;
		case KeyEvent.VK_ESCAPE:
			keyMap.put("ESC", true);
			break;
		}

		keyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyMap.put("UP", false);
			break;
		case KeyEvent.VK_RIGHT:
			keyMap.put("RIGHT", false);
			break;
		case KeyEvent.VK_DOWN:
			keyMap.put("DOWN", false);
			break;
		case KeyEvent.VK_LEFT:
			keyMap.put("LEFT", false);
			break;
		case KeyEvent.VK_ENTER:
			keyMap.put("ENTER", false);
			break;
		case KeyEvent.VK_ESCAPE:
			keyMap.put("ESC", false);
			break;
		}
		keyPressed = false;
	}

	public void resetKeyState() {
		keyMap.put("UP", false);
		keyMap.put("DOWN", false);
		keyMap.put("RIGHT", false);
		keyMap.put("LEFT", false);
		keyMap.put("ENTER", false);
		keyMap.put("ESC", false);
	}

}
