package States;



import Levels.LevelManager;
import RPGgame.Game;
import UI.PauseOverlay;
import entities.Enemy;
import entities.EnemyManager;
import entities.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import ui.GameOverOverlay;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
        private EnemyManager enemyManager;
        private BufferedImage bg;
        private GameOverOverlay gameOverOverlay;
        
        private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
//	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
//	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX ;
        
        private boolean gameOver;
        private boolean paused = false;
        private boolean replay = false;
        private PauseOverlay pauseOverlay;
        private boolean lvlCompleted;
        
	public Playing(Game game) {
		super(game);
		initClasses();
                calcLvlOffset();
		loadStartLevel();
	}
        public void loadNextLevel() {
                player.setLvHealth();
		respawnPlayer();
		levelManager.loadNextLevel();
		player.setSpawn(100,300);
	}

	private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
	}

	private void calcLvlOffset() {
		maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
                enemyManager = new EnemyManager(this);
		player = new Player(200, 200,128,128,this);
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
                pauseOverlay = new PauseOverlay(this, levelManager);
                bg = LoadSave.GetSpriteAtlas(LoadSave.GAMEBACKGROUND);
                gameOverOverlay = new GameOverOverlay(this);
	}
        
        public void respawnPlayer(){
                gameOver = false;
		paused = false;
		lvlCompleted = false;
                player.resetSpawn();
                enemyManager.resetAllEnemies();
        }
        public void respawnLv(){
                gameOver = false;
		paused = false;
		lvlCompleted = false;
                player.resetLv();
                enemyManager.resetAllEnemies();
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
        
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
        public void resetAll() {
		gameOver = false;
		paused = false;
		lvlCompleted = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
	}
	@Override
	public void update() {
                if(paused){
                    pauseOverlay.update();
                }
                else if(lvlCompleted){
                    loadNextLevel();
                }
                else if (!gameOver) {
			levelManager.update();
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		}
	}

	@Override
	public void draw(Graphics g) {
                g.drawImage(bg, 0,0,1248,672, null);
		levelManager.draw(g, xLvlOffset);
                enemyManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
                if (paused)
			pauseOverlay.draw(g);
                else if (gameOver)
			gameOverOverlay.draw(g);
	}
        
        public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
	}

	@Override
	public void keyPressed(KeyEvent e) {
            if (gameOver){
                gameOverOverlay.keyPressed(e);
            }else{
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
        }

	@Override
	public void keyReleased(KeyEvent e) {
            if (!gameOver){
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
            if (!gameOver){
		if (paused)
			pauseOverlay.mousePressed(e);
            }
	}
        public void mouseDragged(MouseEvent e) {
            if (!gameOver){
		if (paused)
			pauseOverlay.mouseDragged(e);
            }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
            if (!gameOver){
		if (paused)
			pauseOverlay.mouseReleased(e);
            }

	}

	@Override
	public void mouseMoved(MouseEvent e) {
            if (!gameOver){
		if (paused)
			pauseOverlay.mouseMoved(e);
            }

	}

	
	public void setLevelCompleted(boolean levelCompleted) {
		this.lvlCompleted = levelCompleted;
	}

	public void setMaxLvlOffset(int lvlOffset) {
		this.maxLvlOffsetX = lvlOffset;
	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetPosBoolean();
	}

	public Player getPlayer() {
		return player;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

        @Override
        public void mouseClicked(MouseEvent e) {

        }

    

}