
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import RPGgame.*;
import States.Playing;
import entities.Entity.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.Direction.UP;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import utilz.LoadSave;


public class Player extends Entity{
    private BufferedImage imgIdle, imgRun, imgAtk_1, imgAtk_2, imgJump;
    private BufferedImage[][] ani;
    private int aniTick, aniIndex , aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean left, up, right, down, jump ;
    private boolean moving = false, attack_1=false, attack_2=false;
    private float playerSpeed = 2.0f;
    
    private float xDrawOffSet = 34* Game.SCALE;
    private float yDrawOffSet = 50* Game.SCALE;
    
    //Jump /Gravity;
    private float airSpeed = 0f;
    private float gravity = 0.02f * Game.SCALE;
    private float JumpSpeed = -2.25f *Game.SCALE;
    private float fallSpeedAC = 0.3f *Game.SCALE;
    private boolean inAir = true;
    
    //health bar
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (70 * 3 * Game.SCALE);
    private int statusBarHeight = (int) (7 * 3 * Game.SCALE);
    private int statusBarX = (int) (10 * 3 * Game.SCALE);
    private int statusBarY = (int) (10 * 3 * Game.SCALE);

    private int healthBarWidth = (int) (68 * 3 * Game.SCALE);
    private int healthBarHeight = (int) (5 * 3  * Game.SCALE);
    private int healthBarXStart = (int) (3 * Game.SCALE);
    private int healthBarYStart = (int) (3 * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;
        
    // AttackBox
    private Rectangle2D.Float attackBox1;
    private Rectangle2D.Float attackBox2;

    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    private Playing playing;
    
    //Lvl data
    private  int[][] lvlData;
    
    private static int score;
    Font font = new Font("Time News Roman",0, 16);
    public Player(float x, float y,int width, int height, Playing playing) {
        super(x,y,width,height);
        LoadAnimation();
        this.currentHealth = maxHealth;
        this.playing = playing;
        initHitBox(x,y, 18 * Game.SCALE, 32 * Game.SCALE);
        initAttackBox();
    }
        private void initAttackBox() {
            attackBox1 = new Rectangle2D.Float(x, y, (int) (40 * Game.SCALE ), (int) (30 * Game.SCALE));
            attackBox2 = new Rectangle2D.Float(x, y, (int) (100 * Game.SCALE ), (int) (30 * Game.SCALE));
	}
        
        public static int addScore(){
            return score += 1;
        }
	public void update() {
            updateHealthBar();
		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
            updateAttackBox();
            updatePos();
		if (attack_1 || attack_2)
			checkAttack();
            updateAnimationTick();
            setAnimation();
	}    
        
        private void checkAttack() {
            if(attack_1){
		if (attackChecked || aniIndex != 1)
			return;
		attackChecked = true; 
                    playing.checkEnemyHit(attackBox1);
                }
            if(attack_2){
                if (attackChecked || aniIndex != 5)
                    	return;
		attackChecked = true; 
                    playing.checkEnemyHit(attackBox2);
                }
	}
        
        
        private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}

        
        private void updateAttackBox() {
		if (right){
                    attackBox1.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
                    attackBox2.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
                }
		else if (left){
                    attackBox1.x = hitbox.x - hitbox.width*2 - (int) (Game.SCALE * 10);
                    attackBox2.x = hitbox.x - hitbox.width*3 - (int) (Game.SCALE * 10*5);
                }
		attackBox1.y = hitbox.y + (Game.SCALE);
                attackBox2.y = hitbox.y + (Game.SCALE);
	}
        
        public void changeHealth(int value) {
            currentHealth +=value;
            currentHealth = Math.max(Math.min(currentHealth, maxHealth), 0);
	}
        
        
    	public void render(Graphics g, int lvlOffset) {
            if(playerAction==ATTACK_2 && aniIndex==6)
             g.drawImage(ani[playerAction][aniIndex], (int) (hitbox.x - xDrawOffSet) - lvlOffset+ flipX, (int) (hitbox.y - yDrawOffSet), (width+128)*flipW, height, null);
            else     
             g.drawImage(ani[playerAction][aniIndex], (int) (hitbox.x - xDrawOffSet) - lvlOffset + flipX, (int) (hitbox.y - yDrawOffSet), width * flipW, height, null);
//          drawHitBox(g,lvlOffset);
//            drawAttackBox(g, lvlOffset);
             drawUI(g);
	}
         private void drawUI(Graphics g) {
            g.drawImage(statusBarImg, statusBarX, statusBarY,statusBarWidth,statusBarHeight,null);
            g.setColor(Color.red);
            g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
             drawScore(g);
        }
        
        private void drawScore(Graphics g){
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("GIẾT HẾT QUÁI ĐỂ QUA MÀN",50, 120);
            g.drawString("ĐIỂM HIỆN TẠI: " + score ,50, 100);
            g.drawString("AWSD ĐỂ DI CHUYỂN",50, 140);
            g.drawString("J ĐÁNH NHANH, K ĐÁNH XA",50, 160);
           
        }
        
         
        private void drawAttackBox(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		g.drawRect((int) attackBox1.x - lvlOffsetX, (int) attackBox1.y, (int) attackBox1.width, (int) attackBox1.height);
                g.drawRect((int) attackBox2.x - lvlOffsetX, (int) attackBox2.y, (int) attackBox2.width, (int) attackBox2.height);

	}
    private void LoadAnimation() {
        InputStream idle = getClass().getResourceAsStream("/res/Idle.png");
        InputStream run = getClass().getResourceAsStream("/res/Run.png");
        InputStream atk_1 = getClass().getResourceAsStream("/res/Attack_2.png");
        InputStream atk_2 = getClass().getResourceAsStream("/res/Attack_3_1.png");
        InputStream jump = getClass().getResourceAsStream("/res/Jump.png");
        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
        try {
           imgIdle = ImageIO.read(idle);
           imgRun = ImageIO.read(run);
           imgAtk_1 = ImageIO.read(atk_1);
           imgAtk_2 = ImageIO.read(atk_2);
           imgJump = ImageIO.read(jump);
           
           //Idle
           ani = new BufferedImage[10][12];
            for(int i = 0 ; i < 6; i++){
                ani[IDLE][i] = imgIdle.getSubimage(i*128,0,128,128);
            }
            //End idle
            
            //Running

            for(int i = 0 ; i < 7; i++){
                ani[RUN][i] = imgRun.getSubimage(i*128,0,128,128);
            }
            //End running
            //Attack
            for(int i = 0 ; i < 4; i++){
                ani[ATTACK_1][i] = imgAtk_1.getSubimage(i*128,0,128,128);
            }
            for(int i = 0 ; i < 6; i++){
                ani[ATTACK_2][i] = imgAtk_2.getSubimage(i*128,0,128,128);
            }
             ani[ATTACK_2][6] = imgAtk_2.getSubimage(6*128,0,128+128,128);
            //End Attack
            //Jump
            for(int i = 0 ; i < 6; i++){
                ani[JUMP][i] = imgJump.getSubimage(i*128,0,128,128);
                ani[FALL][i] = imgJump.getSubimage((i+5)*128,0,128,128);
            }
            for(int i = 5 ; i < 11; i++){
                
            }
            //End Jump
        } catch (IOException e) {
                e.printStackTrace();
        }finally{
            try {
                idle.close();
                run.close();
                atk_1.close();
                atk_2.close();
                jump.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    public void loadLevelData(int[][] lvlData){
        this.lvlData = lvlData;
        if(!IsEntityOnFloor(hitbox, lvlData) && (moving)){
            inAir=true;
        }
    }
    private void updatePos() {
		moving = false;

		if (jump && !attack_2 ){
                    jump();
                    jump = false;
                }
			
		if (!left && !right && !inAir)
			return;
                if (left && right && !inAir)
			return;
		float xSpeed = 0;

		if (left && !attack_2 ){
                        xSpeed -= playerSpeed;
			flipX = width;
			flipW = -1;
                }
		if (right && !attack_2 ){
                        xSpeed += playerSpeed;
			flipX = 0;
			flipW = 1;

                }

		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAC;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}
    
    
    public void resetPosBoolean() {
        left=false;
        right=false;
        up=false;
        down=false;
    }
    
    private void jump() {
        if(inAir)
            return;
        inAir = true;
        airSpeed = JumpSpeed;
    }
    
    private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosWall(hitbox, xSpeed);
		}

	}
    
    
    public void setAttack_1(boolean attacking){
        this.attack_1=attacking;
    }
    public void setAttack_2(boolean attacking){
        this.attack_2=attacking;

    }
    
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    private void updateAnimationTick() {
        aniTick++;

        if(aniTick >= aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attack_1 = false;
                attack_2 = false;
                attackChecked = false;
            }
        }
    }

    
    private void setAnimation() {
        int startAni = playerAction;
        if(moving && !inAir)
            playerAction = RUN;
        else 
            playerAction = IDLE;
        if(inAir){
              if(airSpeed<0)
                  playerAction = JUMP;
              else 
                  playerAction = FALL;
        } 
            
        if(attack_1){
            playerAction = ATTACK_1;
        }
        else if(attack_2 && !inAir){
            playerAction = ATTACK_2;
        }
        if(startAni != playerAction){
            resetAniTick();
        }
    }

    public void setSpawn(int x1, int y1) {
		this.x = x1;
		this.y = y1;
		hitbox.x = x;
		hitbox.y = y;
	}
    
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
        jump=false;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
        jump=false;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    

    public void setJump(boolean b) {
        this.jump = b;
    }
    
    public void resetSpawn(){
                resetPosBoolean();
		inAir = false;
		attack_1 = false;
                attack_2 = false;
		moving = false;

                flipX = 0;
                flipW = 1;
		hitbox.x = x;
		hitbox.y = y;

		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
    }
   public void resetAll() {
		resetPosBoolean();
		inAir = false;
		attack_1 = false;
                attack_2 = false;
		moving = false;
                playerAction = IDLE;
                currentHealth = maxHealth;

                flipX = 0;
                flipW = 1;
		hitbox.x = x;
		hitbox.y = y;

		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
   }

   
   
  
    
}
