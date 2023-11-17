
package Levels;


import RPGgame.Game;
import entities.Goblin;
import entities.Skeleton;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utilz.HelpMethods;
import static utilz.HelpMethods.GetLevelData;


public class Level {

	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Goblin> goblin;
        private ArrayList<Skeleton> Skeleton;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
//	private Point playerSpawn;

	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		calcLvlOffsets();
//		calcPlayerSpawn();
	}

//	private void calcPlayerSpawn() {
//		playerSpawn = GetPlayerSpawn(img);
//	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	private void createEnemies() {
		goblin = HelpMethods.GetGoblins(img);
                Skeleton = HelpMethods.GetSkeletons(img);
	}

	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	public ArrayList<Goblin> GetGoblins() {
		return goblin;
	}
        public ArrayList<Skeleton> GetSkeletons() {
		return Skeleton;
	}

//	public Point getPlayerSpawn() {
//		return playerSpawn;
//	}

}