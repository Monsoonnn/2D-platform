
package Levels;

import RPGgame.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprites;
    private Level levelOne;
    
    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
        
//        levelSprites = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }
    
    
    public void draw(Graphics g){
        for(int j = 0; j< Game.TILES_IN_HEIGHT;j++)
           for(int i=0;i< Game.TILES_IN_WIDTH;i++){
               int index = levelOne.getSpriteIndex(i, j);
               g.drawImage(levelSprites[index], Game.TILES_SIZE*i, Game.TILES_SIZE*j,Game.TILES_SIZE,Game.TILES_SIZE, null);
           }
  
    }
    
    public void importOutsideSprites(){
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprites = new BufferedImage[48];
        for(int j=0;j<4;j++){
            for (int i = 0; i < 12; i++) {
                int index = j*12+i;
                levelSprites[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }
    public void update(){
        
    }
    public Level getLevel(){
        return levelOne;
    }
}
