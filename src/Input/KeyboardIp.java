
package Input;

import RPGgame.GamePanel;
import States.Gamestate;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Direction.*;


public class KeyboardIp implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardIp(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
        
	@Override
	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		case OPTIONS:
			gamePanel.getGame().getGameSetting().keyPressed(e);
			break;
		default:
			break;
		}
	}
}