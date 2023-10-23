package States;



import Levels.LevelManager;
import RPGgame.Game;
import UI.PauseOverlay;
import entities.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
        private BufferedImage bg;
        private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
        
        private boolean paused = false;
        private boolean replay = false;
        private PauseOverlay pauseOverlay;

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200,128,128);
		player.loadLevelData(levelManager.getLevel().getLevelData());
                pauseOverlay = new PauseOverlay(this);
                bg = LoadSave.GetSpriteAtlas(LoadSave.GAMEBACKGROUND);
	}
        
        public void respawnPlayer(){
            player.resetAll();
        }
        private void checkCloseToBorder() {
		int playerX = (int) player.getHitBox().x;
		int diff = playerX - xLvlOffset;
		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;

	}
	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
                        checkCloseToBorder();
		} else {
			pauseOverlay.update();
		}
	}

	@Override
	public void draw(Graphics g) {
                g.drawImage(bg, 0,0,1248,672, null);
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
                if (paused)
			pauseOverlay.draw(g);
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_W:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
                case KeyEvent.VK_J:
                        player.setAttack_1(true);
                    case KeyEvent.VK_K:
                        player.setAttack_2(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);

	}
        public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);

	}

	public void windowFocusLost() {
		player.resetPosBoolean();
	}

	public Player getPlayer() {
		return player;
	}
        
        public void unpauseGame() {
		paused = false;
	}

        @Override
        public void mouseClicked(MouseEvent e) {

        }


    

}