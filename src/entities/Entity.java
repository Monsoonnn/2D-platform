
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import utilz.HelpMethods.*;


public abstract class Entity {
    protected float x, y;
    protected  int width, height;
    protected  Rectangle2D.Float hitbox;
    protected int state;
    protected int aniTick, aniIndex;
    public int point;

    public Entity(float x, float y, int witdh, int height) {
        this.x = x;
        this.y = y;
        this.width = witdh;
        this.height = height;
    }
    protected void drawHitBox(Graphics g, int xLvlOffset) {
	g.setColor(Color.PINK);
	g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

    protected void initHitBox(float x, float y , float width, float height){
        hitbox = new Rectangle2D.Float(x,y,width, height);
    }
//    public void updateHitBox(){
//        hitbox.x = (int)x;
//        hitbox.y = (int)y;
//    }
    public Rectangle2D.Float getHitBox(){
        return hitbox;
    }
    public int getState() {
		return state;
	}

	public int getAniIndex() {
		return aniIndex;
	}

    protected void newState(int state) {
		this.state = state;
		aniTick = 0;
		aniIndex = 0;
	}
}

