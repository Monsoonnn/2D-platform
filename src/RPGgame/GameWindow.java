
package RPGgame;

import RPGgame.GamePanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;


public class GameWindow extends JFrame{
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocation(150,75);
        jframe.setResizable(false);
        jframe.pack();
   
        jframe.addWindowFocusListener(new WindowFocusListener(){
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }
                

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
                
            
        });
        
    }
}
