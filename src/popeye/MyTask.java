/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popeye;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tahnoon
 */
public class MyTask extends TimerTask {
    String hour,mins, note;

    public MyTask(String hour, String mins, String note) {
        this.hour = hour;
        this.mins = mins;
        this.note = note;
    }
    
    @Override
    public void run() {
        String time = hour+":"+mins;
        JFrame frame=new JFrame("Reminder");
        frame.setBounds(400,400, 700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyCanvas m=new MyCanvas();
        
        frame.add(m);
        frame.setVisible(true);
        System.out.println("REMINDER FOR "+note);
    }
}

class MyCanvas extends Canvas {

    public void paint(Graphics g) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("clock.png");
        g.drawImage(i, 0, 0, this);

    }

}