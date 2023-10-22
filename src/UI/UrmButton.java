/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {
	private BufferedImage[] imgs;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;
        private String file;
	public UrmButton(int x, int y, int width, int height, int rowIndex, String file) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
                this.file = file;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(file);
		imgs = new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * 25, rowIndex * 24, 25, 24);

	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;

	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
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
