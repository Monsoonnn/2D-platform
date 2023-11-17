
package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import RPGgame.Game;
import entities.Goblin;
import entities.Skeleton;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.GOBLIN;
import static utilz.Constants.EnemyConstants.SKELETON;
public class LoadSave {
        //MENU BG
        public static final String BACKGROUND = "bgtest.jpeg";
        public static final String GAMEBACKGROUND = "bg_new.png";
	public static final String LEVEL_ATLAS = "tileset.png";
//	public static final String LEVEL_ONE_DATA = "map/stage2.png";
        //PAUSED OVERLAY
        public static final String PLAY_BUTTONS = "play_but.png";
        public static final String SETTING_BUTTONS = "setting_but.png";
        public static final String QUIT_BUTTONS = "quit_but.png";
        public static final String PAUSE_BACKGROUND = "paused_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
        public static final String URM_HOME = "urm_home.png";
        public static final String URM_REPLAY = "urm_replay.png";
	public static final String URM_BACK = "urm_back.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
        //ENEMY
        public static final String GOBLIN_SPRITE = "enemy/goblin_sprite_3.png";
        public static final String SKELETON_SPRITE = "enemy/skeleton_sprite.png";
        //BAR
        public static final String STATUS_BAR = "bar/health_bar.png";
        

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/"+fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
                            is.close();
			} catch (IOException e) {
                            e.printStackTrace();
			} 
		}
		return img;
	}


        
        public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/res/map");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];

			}
		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}

        
}
