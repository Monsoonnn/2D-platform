
package Levels;

import RPGgame.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import States.Gamestate;
import java.util.Random;
import utilz.LoadSave;

public class LevelManager {

	private Game game;
        private Random rnd = new Random();
	private BufferedImage[] levelSprite;
	private ArrayList<Level> levels;
	private int lvlIndex = rnd.nextInt(6);
        int count;
    
	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		levels = new ArrayList<>();
		buildAllLevels();
	}

	public void loadNextLevel() {
                lvlIndex = rnd.nextInt(6);
		buildLevel();
	}
        public void buildLevel(){
                Level newLevel = levels.get(lvlIndex);
                
		game.getPlaying().getEnemyManager().loadEnemies(newLevel);
		game.getPlaying().getPlayer().loadLevelData(newLevel.getLevelData());
		game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        }
	private void buildAllLevels() {
		BufferedImage[] allLevels = LoadSave.GetAllLevels();
		for (BufferedImage img : allLevels)
			levels.add(new Level(img));
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g, int lvlOffset) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
				int index = levels.get(lvlIndex).getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
	}
        
        public void resetLevel(){
            lvlIndex = rnd.nextInt(6);
        }
        
        
	public void update() {

	}

	public Level getCurrentLevel() {
		return levels.get(lvlIndex);
	}

	public int getAmountOfLevels() {
		return levels.size();
	}

}
