
package RPGgame;

import RPGgame.GamePanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;


public class GameWindow extends JFrame{
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setTitle("BTL JAVA");
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        
        
   
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
