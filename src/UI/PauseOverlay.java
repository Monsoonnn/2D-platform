
package UI;

import Levels.LevelManager;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import States.Gamestate;
import States.Playing;
import RPGgame.Game;
import utilz.Constants;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;
import static utilz.LoadSave.URM_BACK;
import static utilz.LoadSave.URM_HOME;
import static utilz.LoadSave.URM_REPLAY;
import static States.Playing.*;
import entities.Enemy;
import static entities.Player.*;
import static Levels.LevelManager.*;
public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButton menuB, replayB, unpauseB;
	private VolumeButton volumeButton;
        private AudioSetings audioSetings;
        private Enemy enemy;
        private LevelManager levelManager;
	public PauseOverlay(Playing playing, LevelManager lvl) {
		this.playing = playing;
                this.levelManager = lvl;
                audioSetings = playing.getGame().getAudioSetings();
		loadBackground();
		createUrmButtons();
	

	}


	private void createUrmButtons() {
		int menuX = (int) (325 * Game.SCALE);
		int replayX = (int) (390 * Game.SCALE);
		int unpauseX = (int) (450 * Game.SCALE);
		int bY = (int) (280 * Game.SCALE) + 50;

		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 0,URM_HOME);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 0,URM_BACK);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0,URM_REPLAY);

	}


	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW + 20;
		bgY = (int) (25 * Game.SCALE)+ 80;

	}

	public void update() {

		menuB.update();
		replayB.update();
		unpauseB.update();

		audioSetings.update();

	}

	public void draw(Graphics g) {
		// Background
		g.drawImage(backgroundImg, bgX, bgY , 400, 500, null);

		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);

		audioSetings.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
		audioSetings.mouseDragged(e);
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB)){
                    menuB.setMousePressed(true);
                }
			
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else
			audioSetings.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
                                playing.respawnPlayer();
                                levelManager.resetLevel();
                                playing.resetAll();
                                levelManager.buildLevel();
				playing.unpauseGame();
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed()) {
				playing.respawnLv();
				playing.unpauseGame();
//                                playing.resetAll();
                        }
		} else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		} else
			audioSetings.mouseReleased(e);

		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();

	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else
			audioSetings.mouseMoved(e);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
