package new_Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterT implements Runnable {
    private ImageIcon alert[]=new ImageIcon[6];
    private ImageIcon jump[]=new ImageIcon[2];
    private ImageIcon walk[]=new ImageIcon[8];
    private ImageIcon stand[]=new ImageIcon[6];
    private Timer walkT;
    private Timer alertT;
    private Timer standT;
    private Timer jumpT;
    private String now;
    private boolean right ,left,up,down,att=false;
    public  CharacterT(ImageIcon alert[], ImageIcon jump[], ImageIcon walk[], ImageIcon stand[], String now,int noChar){
        this.alert=alert;
        this.jump=jump;
        this.walk=walk;
        this.stand=stand;
        this.now=now;
        walkT=new Timer(125, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alertT=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        standT=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jumpT=new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void setUp(boolean upFlag){
        this.up=upFlag;
    }
    private void setDown(boolean downFlag){
        this.down=downFlag;
    }
    private void setRight(boolean rightFlag){
        this.right=rightFlag;
    }
    private void setLeft(boolean leftFlag){
        this.left=leftFlag;
    }
    private void setAtt(boolean attFlag){
        this.att=attFlag;
    }
    @Override
    public void run() {
        while(true){
            if(up==true){

            }else if(down==true){

            }else if(right==true){

            }else if(left==true){

            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
