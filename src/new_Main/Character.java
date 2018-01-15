package new_Main;

import LayeredPane_Test.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Character extends JLabel implements Runnable{
    private ImageIcon alert[]=new ImageIcon[6];
    private ImageIcon jump[]=new ImageIcon[2];
    private ImageIcon walk[]=new ImageIcon[8];
    private ImageIcon stand[]=new ImageIcon[6];
    private ImageIcon attack[]=new ImageIcon[8];
    //deffault
    private String id;
    private int nowHp,maxHp,nowMp,maxMp;
    private boolean charface=false;
    private Point local;
    private int charType;
    private int lv=1;
    private int charAttack=5;
    private String nowC="stand";


    //Animal Timer
//    private Timer walkT;
//    private Timer standT;
//    private Timer jumpT;
//    private Timer jumpRLT;
//    private Timer attackT;
//    private Timer dropT;
    //Animal Timer tmp
    private int t1Tmp;
    private new_Main.MainFrame mf;

    private boolean right ,left,up,down,att,skill=false;

    public Character(String id, int nowHp, int maxHp, int charType,new_Main.MainFrame mf){
        this.id=id;
        this.nowHp=nowHp;
        this.maxHp=maxHp;
//        this.charface=charface;
        //角色面方向
//        this.local=local;
        //位置
        this.charType=charType;
        //哪隻角色
//        this.nowC=nowC;
        //正在使用的指令
        setCharAnimal(charType);
        setIcon(stand[3]);
        this.mf =mf;
        maxHp=100;
        nowHp=100;
        maxMp=50;
        nowMp=50;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getid(){
        return id;
    }
    public void setNowHp(int nowHp){
        this.nowHp=nowHp;
    }

    public int getNowHp(){
        return nowHp;
    }
    public void setMaxHp(int maxHp){
        this.maxHp=maxHp;
    }

    public int getMaxHp(){
        return maxHp;
    }
    public void setNowMp(int nowMp){
        this.nowHp=nowMp;
    }
    public int getNowMp(){
        return nowMp;
    }
    public void setMaxMp(int nowMp){
        this.nowHp=nowMp;
    }
    public int getMaxMp(){
        return maxMp;
    }

    public void setCharface(boolean charface){
        this.charface=charface;
    }

    public boolean getCharface(){
        return charface;
    }
    public void setLocal(Point local){
        this.local=local;
    }
    public Point getLocal(){
        return local;
    }
    public void setCharType(int charType){
        this.charType=charType;
    }
    public int getCharType(){
        return charType;
    }
    public String getNowC(){
        return nowC;
    }

    public void setUp(boolean upFlag){
        this.up=upFlag;
    }
    public boolean getUp(){
        return up;
    }
    public void setDown(boolean downFlag){
        this.down=downFlag;
    }
    public boolean getDown(){
        return down;
    }
    public void setRight(boolean rightFlag){
        this.right=rightFlag;
    }
    public boolean getRight(){
        return right;
    }
    public void setLeft(boolean leftFlag){
        this.left=leftFlag;
    }
    public boolean getLeft(){
        return left;
    }
    public void setLv(int lv){this.lv = lv;}
    public int getLv(){return lv;}
    public void setCharAttack(int att){
        this.charAttack=att;
    }
    public int getCharAttack(){
        return charAttack;
    }
    public boolean getAtt(){
        return att;
    }
    public void setAtt(boolean attFlag){
        att=attFlag;
    }
    public boolean getSkill(){
        return skill;
    }
    public void setSkill(boolean skillFlag){
        skill=skillFlag;
    }

    @Override
    public void run() {
//        addMpT.start();
        while(true){
            if(up==true){
//                System.out.println("up");
                mf.setNowC("up");
            }else if(down==true){
//                System.out.println("down");
                mf.setNowC("down");
                nowC="down";
            }else if(right==true){
//                System.out.println("right");
                mf.setNowC("right");
                charface=false;
                nowC="right";
            }else if(left==true){
//                System.out.println("left");
                charface=true;
                mf.setNowC("left");
                nowC="left";
            }else if(att==true){
//                System.out.printf("att");
                mf.setNowC("att");
                nowC="att";
            }else if(skill==true){
                mf.setNowC("skill");
                nowC="skill";
            }else{
                mf.setNowC("stand");
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void setCharAnimal(int charType) {
        for (int i = 0; i < 3; i++) {
            alert[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/alert/left/alert_" + Integer.toString(i) + ".png");
//            System.out.println("NewCharacter/Character0"+Integer.toString(charType)+"/alert/left/alert_"+Integer.toString(i)+".png");
        }
//        System.out.println("NewCharacter/Character01/alert/left");
        for (int i = 3; i < 6; i++) {
            alert[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/alert/right/alert_" + Integer.toString(i - 3) + ".png");
        }

        for (int i = 0; i < 4; i++) {
            walk[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/walk/left/walk1_" + Integer.toString(i) + ".png");
        }

        for (int i = 4; i < 8; i++) {
            walk[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/walk/right/walk1_" + Integer.toString(i - 4) + ".png");
        }
        jump[0] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/jump/left/jump_" + Integer.toString(0) + ".png");
        jump[1] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/jump/right/jump_" + Integer.toString(0) + ".png");

        for (int i = 0; i < 3; i++) {
            stand[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/stand/left/stand1_" + Integer.toString(i) + ".png");
//            System.out.println("NewCharacter/Character0"+Integer.toString(charType)+"/stand/left/stand1_"+Integer.toString(i)+".png");
        }
//        System.out.println("NewCharacter/Character01/stand/left");
        for (int i = 3; i < 6; i++) {
            stand[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/stand/right/stand1_" + Integer.toString(i - 3) + ".png");
        }
        for (int i = 0; i < 2; i++) {
            attack[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/attack/left/stabO1_" + Integer.toString(i) + ".png");
        }
        for (int i = 2; i < 4; i++) {
            attack[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/attack/left/stabO2_" + Integer.toString(i - 2) + ".png");
        }
        for (int i = 4; i < 6; i++) {
            attack[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/attack/right/stabO1_" + Integer.toString(i - 4) + ".png");
        }
        for (int i = 6; i < 8; i++) {
            attack[i] = new ImageIcon("NewCharacter/Character0" + Integer.toString(charType) + "/attack/right/stabO2_" + Integer.toString(i - 6) + ".png");
        }
    }
}
