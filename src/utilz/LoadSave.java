
package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import RPGgame.Game;

public class LoadSave {
        //MENU BG
        public static final String BACKGROUND = "bg_new.png";
	public static final String LEVEL_ATLAS = "tileset.png";
	public static final String LEVEL_ONE_DATA = "map/stage2.png";
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
        
        
        
//        public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
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

	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}
