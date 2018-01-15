package new_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundFloor extends JLabel implements Runnable{
    private int x,y=0;
    private Image imagea;
//    private String charface;
    private MainFrame mf;

    public BackgroundFloor(MainFrame mf){
        this.mf=mf;
        try{
            imagea= ImageIO.read(new File("floor2.png"));
        }catch(IOException ie){
            ie.printStackTrace();
        }

    }
    @Override
    public void run() {
        while(true){
            if(mf.getNowC().equals("right")){
                if(mf.getMap().getMapDrawStart()+1<200) {
                    mf.getMap().setMapDrawStart(mf.getMap().getMapDrawStart() + 1);
                    x -= 10;
                    System.out.println(mf.getMap().getMapDrawStart());
//                    mf.getMap().printMap();
                }
            }else if(mf.getNowC().equals("left")){
                if(mf.getMap().getMapDrawStart()-1>0) {
                    mf.getMap().setMapDrawStart(mf.getMap().getMapDrawStart() - 1);
                    x += 10;
                    System.out.println(mf.getMap().getMapDrawStart());
//                    mf.getMap().printMap();
                }
            }
            try {
                Thread.sleep(145);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BackgroundFloor.this.repaint();
        }
    }
//    public void setCharface(String charface1){
//        this.charface=charface1;
//    }
    public void paint(Graphics g){
        Graphics2D g2da=(Graphics2D) g;
//        g2d.drawImage(image,0,0,null,this);
        g2da.drawImage(imagea,x-1000,y,1000, 130,this);
        if(x<=0){
            g2da.drawImage(imagea,1000+x,y,1000, 130,this);
        }
        g2da.drawImage(imagea,x,y,1000, 130,this);
        if(x<=-1000){
            x=0;
        }else if(x>=1000){
            x=0;
        }
    }
}
