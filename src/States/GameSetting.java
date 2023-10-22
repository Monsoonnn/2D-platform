
package States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import RPGgame.Game;
import UI.AudioSetings;
import UI.PauseButton;
import UI.UrmButton;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.LoadSave.URM_HOME;

public class GameSetting extends State implements Statemethods {

	private AudioSetings audioOptions;
	private BufferedImage backgroundImg, optionsBackgroundImg;
	private int bgX, bgY, bgW, bgH;
	private UrmButton menuB;

	public GameSetting(Game game) {
		super(game);
		loadImgs();
		loadButton();
		audioOptions = game.getAudioSetings();
	}

	private void loadButton() {
		int menuX = (int) (387 * Game.SCALE);
		int menuY = (int) (325 * Game.SCALE);

		menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 0,URM_HOME);
	}

	private void loadImgs() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND);
                optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (optionsBackgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (optionsBackgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW + 20;
		bgY = (int) (25 * Game.SCALE) + 85;
	}

	@Override
	public void update() {
		menuB.update();
		audioOptions.update();

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(optionsBackgroundImg, bgX, bgY, 400, 500, null);

		menuB.draw(g);
		audioOptions.draw(g);

	}

	public void mouseDragged(MouseEvent e) {
		audioOptions.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB)) {
			menuB.setMousePressed(true);
		} else
			audioOptions.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed())
				Gamestate.state = Gamestate.MENU;
		} else
			audioOptions.mouseReleased(e);

		menuB.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else
			audioOptions.mouseMoved(e);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			Gamestate.state = Gamestate.MENU;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
