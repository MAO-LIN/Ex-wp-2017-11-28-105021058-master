package new_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SkyPane extends JPanel implements Runnable{
    private Image image;
    private Timer t1;
    private int x,y=0;
    public SkyPane(){
       init();

    }
    private void init(){
        try{
            image= ImageIO.read(new File("sky.png"));
        }catch(IOException ie){
            ie.printStackTrace();
        }

    }

    @Override
    public void run() {
        t1=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x-=10;
                SkyPane.this.repaint();
            }
        });
        t1.start();
    }
    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
//        g2d.drawImage(image,0,0,null,this);
        if(x<=0){
            g2d.drawImage(image,700+x,y,700, 300,this);
        }
        if(x<=0){
            g2d.drawImage(image,1400+x,y,700, 300,this);
        }
        g2d.drawImage(image,x,y,700, 300,this);
        if(x<=-700){
            x=0;
        }
    }
}
