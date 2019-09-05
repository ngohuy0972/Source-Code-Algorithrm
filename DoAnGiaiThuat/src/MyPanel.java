import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MyPanel
{   
    private JPanel[] panelArray = new JPanel[3];
    private boolean[] myBools = new boolean[]{false, false, false};
    private int counter = 0; 
    private int prvPanelCounter = 0;
    private Timer timer;
    private ActionListener timerAction = new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
            counter++;
            if (counter > 2)
                counter = 0;
            myBools[counter] = true;
            for (int i = 0; i < 3; i++) 
            {
                if (myBools[i])
                {
                    panelArray[i].setVisible(myBools[i]);                   
                    panelArray[prvPanelCounter].setVisible(myBools[prvPanelCounter]);
                    myBools[i] = false; 
                    prvPanelCounter = i;
                    break;
                }
            }
        }
    };

    private void createAndDisplayGUI()
    {
        JFrame frame = new JFrame("Locate Mouse Position");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel0 = new JPanel();
        panel0.setOpaque(true);
        panel0.setBackground(Color.BLUE);       
        JPanel panel1 = new JPanel();
        panel1.setOpaque(true);
        panel1.setBackground(Color.RED);
        JPanel panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setBackground(Color.DARK_GRAY);

        panelArray[0] = panel0;
        panelArray[1] = panel1;
        panelArray[2] = panel2;

        JComponent contentPane = (JComponent) frame.getContentPane();
        contentPane.setLayout(new GridLayout(0, 1));
        frame.add(panel0);  
        frame.add(panel1);  
        frame.add(panel2);  
        panel0.setVisible(myBools[counter]);
        panel1.setVisible(myBools[counter]);
        panel2.setVisible(myBools[counter]);
        frame.setSize(300, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        timer = new Timer(1000, timerAction);
        timer.start();
    }

    public static void main(String\u005B\u005D args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new MyPanel().createAndDisplayGUI();
            }
        });
    }
}