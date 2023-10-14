
package Input;

import RPGgame.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Direction.*;
public class KeyboardIp implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardIp(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } 
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                         gamePanel.getGame().getPlayer().setJump(true);
                        break;    
                    case KeyEvent.VK_A:
                         gamePanel.getGame().getPlayer().setLeft(true);
                        break;    
                    case KeyEvent.VK_S:
                         gamePanel.getGame().getPlayer().setDown(true);
                        break;    
                    case KeyEvent.VK_D:
                        gamePanel.getGame().getPlayer().setRight(true);
                        break;   
                    case KeyEvent.VK_J:
                        gamePanel.getGame().getPlayer().setAttack_1(true);
                    case KeyEvent.VK_K:
                        gamePanel.getGame().getPlayer().setAttack_2(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_W:
                         gamePanel.getGame().getPlayer().setJump(false);
                        break;    
                    case KeyEvent.VK_A:
                         gamePanel.getGame().getPlayer().setLeft(false);
                        break;    
                    case KeyEvent.VK_S:
                         gamePanel.getGame().getPlayer().setDown(false);
                        break;    
                    case KeyEvent.VK_D:
                        gamePanel.getGame().getPlayer().setRight(false);
                        break;                    
                }
            }
}
