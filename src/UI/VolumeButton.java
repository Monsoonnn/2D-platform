
package UI;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {

	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int buttonX, minX, maxX;

	public VolumeButton(int x, int y, int width, int height) {
		super(x + width / 2, y, VOLUME_WIDTH, height);
		bounds.x -= VOLUME_WIDTH / 2;
		buttonX = x + width / 2;
		this.x = x;
		this.width = width;
		minX = x + VOLUME_WIDTH / 2 + 30;
		maxX = x + width - VOLUME_WIDTH / 2 - 30;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		imgs = new BufferedImage[2];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * 18, 0, 18, 22);

		slider = temp.getSubimage(2 * 18, 0,90, 22);
		
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 0;
		if (mousePressed)
			index = 1;

	}

	public void draw(Graphics g) {

		g.drawImage(slider, x + 20, y, width - 50, height, null);
		g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2 , y, VOLUME_WIDTH, height , null);

	}

	public void changeX(int x) {
		if (x < minX)
			buttonX = minX ;
		else if (x > maxX)
			buttonX = maxX ;
		else
			buttonX = x;

		bounds.x = buttonX - VOLUME_WIDTH / 2 ;

	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
}