
package RPGgame;

import Input.KeyboardIp;
import Input.MouseIp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;
import static RPGgame.Game.GAME_HEIGHT;
import static RPGgame.Game.GAME_WIDTH;

public class GamePanel extends JPanel{
    
    
    private MouseIp mouseIP;
    private Game game;
    public GamePanel(Game game) {

        mouseIP = new MouseIp(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardIp(this));
        addMouseListener(mouseIP);
        addMouseMotionListener(mouseIP);
        
    }
    public void importImg(){    
    }
    public void setPanelSize(){
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println(GAME_WIDTH+"x"+GAME_HEIGHT);
    }
    public Game getGame(){
        return game;
    }

    
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        game.render(g);
        
    }

    

    
    
}
