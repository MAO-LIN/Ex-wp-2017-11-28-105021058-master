package new_Main;

//import javafx.scene.layout.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class  MainFrame extends JFrame {
    private ImageIcon alert[]=new ImageIcon[6];
    private ImageIcon jump[]=new ImageIcon[2];
    private ImageIcon walk[]=new ImageIcon[8];
    private ImageIcon stand[]=new ImageIcon[6];
    private ImageIcon attack[]=new ImageIcon[8];
    private ImageIcon skillatk[]=new ImageIcon[6];
    private Container cp;
    private JPanel centerP=new JPanel(new BorderLayout(5,5));
    private JLayeredPane jlyPane = new JLayeredPane();
    private UIpanel UIpane;
    private Thread uipaneThread;
    private ArrayList<Character> charList = new ArrayList<Character>();
    private ArrayList<Thread> threadList = new ArrayList<Thread>();
    private JPanel backgroundP = new JPanel(new BorderLayout(0, 0));
    private JLabel backgroundImg = new JLabel();
    private Thread charThread;
    private String nowC="stand";
    private boolean right ,left,up,down,att=false;
//    public JLabel character[] = new JLabel[2];
    private Timer walkT;
    private Timer alertT;
    private Timer standT;
    private Timer jumpT;
    private Timer jumpRLT;
    private Timer attackT;
    private Timer dropT;
    private Timer skillatkT;
    private Map map;
    private boolean timeFlag=false;
    private boolean walkFlag=false;
//    private boolean up,down,right,left,att=false;
    //鎖住按鍵事件 防止重複觸發or觸發事件重疊
    private boolean keyFlag =false;
    private JLabel testlb=new JLabel("石頭",JLabel.CENTER);
    private JLabel floorlb=new JLabel();

    //平台
    private Obj lb=new Obj(MainFrame.this);
    private Thread lbThread;

    //平台2
    private Obj lb2=new Obj(MainFrame.this);
    private Thread lbThread2;

//    private JPanel testPane=new JPanel();
    //背景
    private BackgroundFloor backgroundLabel=new BackgroundFloor(MainFrame.this);
    private Thread backgroundThread;
//    private JPanel background=new JPanel();
//    private CharacterT CharacterT=new CharacterT(alert,jump,walk,stand,"alert",0);

    //建立 Monster
    private ArrayList<Monster> monsterList=new ArrayList<Monster>();
    private ArrayList<Thread> monsterThreadList=new ArrayList<Thread>();
    private int monsterTota=0;

    //建立 Boss
    private ArrayList<Mob> MobList=new ArrayList<Mob>();
    private ArrayList<Thread> MobThreadList=new ArrayList<Thread>();

    //建立 技能彈
//    private ArrayList<>

//    private ArrayList<MonsterLocal> monsterLocalList =new ArrayList<MonsterLocal>();
//    private ArrayList<Thread> monsterLocalThreadList =new ArrayList<Thread>();
    //Monster startList

    public MainFrame() {
        initComp();
    }

    private void initComp() {
        this.setTitle("菜逼八的遊戲世界");
        charList.add(new Character("菜逼八",100,100,1,MainFrame.this));
        setCharAnimal(charList.get(0).getCharType());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(200, 200, 1600, 700);
        //物件碰撞判斷陣列
        map=new Map(3000,600);
        //test
//        map.setObj(100,0,90,81);
//        System.out.println(map.setChar(100,0,10,10));
//        map.printMap();
        cp=this.getContentPane();
        cp.add(centerP);
        centerP.add(jlyPane,BorderLayout.CENTER);

        //重新focus this
//        this.requestFocusInWindow();

        ImageIcon imgTmp =new ImageIcon("backgroundCG.png");
        imgTmp.setImage(imgTmp.getImage().getScaledInstance(1600,600,Image.SCALE_DEFAULT));
        backgroundImg.setIcon(imgTmp);
        jlyPane = getLayeredPane();
        backgroundP.add(backgroundImg);
//        backgroundImg.setBackground(new Color(239, 255, 175));
        backgroundImg.setOpaque(true);
        backgroundImg.setBounds(0,0,1600,600);
//        character[0]=new JLabel(stand[3]);
        //deffault w:59 h:81

        //建立角色
//        character[0].setBounds((450-59),420-300,84,81);
//        map.setChar((450-59),420-300,84,81);
//        character[0].setBackground(new Color(102, 107,255));
//        character[0].setOpaque(true);
//        charList.get(0).setIcon(stand[3]);
        charList.get(0).setBounds((450-59),420-300,84,81);
        jlyPane.add( charList.get(0) , new Integer(121));
        jlyPane.add(backgroundImg, JLayeredPane.DEFAULT_LAYER);
        map.setChar((450-59),(420-300),84,81);


        //建立石頭(物件)test
//        jlyPane.add(testlb, JLayeredPane.PALETTE_LAYER,new Integer(100));
//        testlb.setBounds((450-59+120),420,50,80);
//        testlb.setBackground(new Color(255, 213, 175));
//        testlb.setOpaque(true);
//        map.setObj((450-59+120),420,80,50);

        //建立平台
        jlyPane.add(lb, JLayeredPane.PALETTE_LAYER,new Integer(101));
        lb.setBounds(200,300,400,50);
        lb.setBackground(new Color(164, 94, 26));
        lb.setOpaque(true);
//        lbThread=new Thread(lb);
//        lbThread.start();
        map.setObj(200,300,50,400);

        //建立平台
        jlyPane.add(lb2, JLayeredPane.PALETTE_LAYER,new Integer(101));
        lb2.setBounds(900,250,400,50);
        lb2.setBackground(new Color(84, 164, 126));
        lb2.setOpaque(true);
//        lbThread=new Thread(lb2);
//        lbThread.start();
        map.setObj(200,300,50,400);

        //建立地板
        jlyPane.add(backgroundLabel, JLayeredPane.PALETTE_LAYER,102);
//        floorlb.setBounds(0,500,1000,100);
//        floorlb.setBackground(new Color(164, 94, 26));
//        floorlb.setIcon(new ImageIcon("floor2.png"));
//        floorlb.setOpaque(true);
        backgroundLabel.setOpaque(false);
        backgroundLabel.setBounds(0,470,1600,130);
//        Thread bgThread=new Thread(backgroundLabel);
//        bgThread.start();

        //建立Sky
//        testPane.setBounds(0,0,100,600);
//        testPane.setLayout(new BorderLayout(5,5));
//        testPane.add(new JLabel(new ImageIcon("skybackgroundRe.png")));

        SkyPane skyp=new SkyPane();
        threadList.add(new Thread(skyp));
        threadList.get(0).start();
        skyp.setOpaque(false);
        skyp.setBounds(0,0,1600,300);
//        testPane.setBackground(new Color(255, 69, 154));
        jlyPane.add(skyp, JLayeredPane.PALETTE_LAYER,101);

        //角色執行續
        charThread=new Thread(charList.get(0));
        charThread.start();

        //UI介面
        UIpane=new UIpanel(MainFrame.this);
        centerP.add(UIpane,BorderLayout.SOUTH);
        UIpane.setPreferredSize(new Dimension(1000,50));
        uipaneThread=new Thread(UIpane);
        uipaneThread.start();

        //建立 Monster



        //建立 boss
        MobList.add(new Mob(1000,800,false , MainFrame.this));
        jlyPane.add(MobList.get(0),new Integer(121));
        jlyPane.repaint();
        monsterThreadList.add(new Thread(MobList.get(0)));
        monsterThreadList.get(0).start();

        UIpane.getJbtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monsterList.add(new Monster(700,1600,false,MainFrame.this));
                jlyPane.add(monsterList.get(monsterList.size()-1), new Integer(120));
                monsterThreadList.add(new Thread(monsterList.get(monsterList.size()-1)));
                monsterThreadList.get(monsterThreadList.size()-1).start();

//                monsterLocalList.add(new MonsterLocal(monsterList.get(monsterList.size()-1),MainFrame.this));
//                monsterLocalThreadList.add(new Thread(monsterLocalList.get(monsterLocalList.size()-1)));
//                monsterLocalThreadList.get(monsterLocalThreadList.size()-1).start();
            }
        });

//        jlyPane.add(characterlb[1], JLayeredPane.PALETTE_LAYER,new Integer(102));
        //test
        map.printMap();
//        UIpane.getJbtn1().getAction();


        walkT=new Timer(150, new ActionListener() {
            int t1Tmp=1;
            @Override
            public void actionPerformed(ActionEvent e) {
//                getMap().printMap();
                walkFlag=true;
//                if(charList.get(0).getCharface()){
//                    if(map.setCharWalk(charList.get(0).getX(),charList.get(0).getY(),-10)) {
//                        charList.get(0).setIcon(walk[t1Tmp % 4]);
//                        System.out.println(t1Tmp % 4);
//                        t1Tmp++;
////                        charList.get(0).setLocation(charList.get(0).getX() - 10, charList.get(0).getY());
//                    }
//                }else if(!charList.get(0).getCharface()){
//                    if(map.setCharWalk(charList.get(0).getX(),charList.get(0).getY(),10)) {
//                        charList.get(0).setIcon(walk[t1Tmp % 4 + 4]);
//                        System.out.println(t1Tmp % 4+4);
//                        t1Tmp++;
////                        charList.get(0).setLocation(charList.get(0).getX() + 10,charList.get(0).getY());
//                    }
//                }
                if(map.charDropCheck(charList.get(0).getX(),charList.get(0).getY())){
                    dropT.start();
                    walkT.stop();
                }else {
                    if (charList.get(0).getCharface()) {
                        if (map.setWalkCheck(charList.get(0).getX(), charList.get(0).getY(), 81, 84, charList.get(0).getCharface())) {
                            map.removeChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                            charList.get(0).setIcon(walk[t1Tmp % 4]);
//                            System.out.println(t1Tmp % 4);
                            t1Tmp++;
                            charList.get(0).setLocation(charList.get(0).getX() - 10, charList.get(0).getY());
                            map.setChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
//                            map.printMap();
                        }

                    } else if (!charList.get(0).getCharface()) {
                        if (map.setWalkCheck(charList.get(0).getX(), charList.get(0).getY(), 81, 84, charList.get(0).getCharface())) {
                            map.removeChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                            charList.get(0).setIcon(walk[t1Tmp % 4 + 4]);
//                            System.out.println(t1Tmp % 4+4);
                            t1Tmp++;
                            charList.get(0).setLocation(charList.get(0).getX() + 10, charList.get(0).getY());
                            map.setChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
//                            map.printMap();
                        }
                    }
                }


            }
        });
        alertT=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        attackT=new Timer(125, new ActionListener() {
            int t1Tmp=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charList.get(0).getCharface()==true){
                    charList.get(0).setIcon(attack[t1Tmp%4]);
//                    if(t1Tmp==1) {
//                        charList.get(0).setLocation(getchar().getX() - 20, getchar().getY());
//                        try {
//                            Thread.sleep(25);
//                        } catch (InterruptedException e1) {
//                            e1.printStackTrace();
//                        }
//                        charList.get(0).setLocation(getchar().getX() + 20, getchar().getY());
//                    }
                    t1Tmp++;
                }else{
                    charList.get(0).setIcon(attack[t1Tmp%4+4]);
                    t1Tmp++;
                }
                if(t1Tmp==3) {
                    if(charList.get(0).getCharface()==true) {
                        charList.get(0).setIcon(stand[0]);
                    }else{
                        charList.get(0).setIcon(stand[3]);
                    }
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    t1Tmp=0;
                    attackT.stop();
                    standT.restart();
                    keyFlag = false;
                }
            }
        });
        standT=new Timer(500, new ActionListener() {
            int t1Tmp=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charList.get(0).getCharface()==true){
                    charList.get(0).setIcon(stand[t1Tmp%3]);
                    t1Tmp++;
                }else if(charList.get(0).getCharface()==false){
                    charList.get(0).setIcon(stand[t1Tmp%3+3]);
                    t1Tmp++;
                }
            }
        });
        jumpT=new Timer(30, new ActionListener() {
            boolean first=true;
            int x,y;
            boolean isTop=false;
            @Override
            public void actionPerformed(ActionEvent e) {
                timeFlag=true;
                if(first==true){
                    x= charList.get(0).getX();
                    y= charList.get(0).getY();
                    first=false;
                }
//                System.out.println("("+x+","+y+")");
                if(charList.get(0).getY()>y-250&&isTop==false) {
                    charList.get(0).setLocation(charList.get(0).getX(), charList.get(0).getY() - 10);

                }else if(charList.get(0).getY()==y&&isTop==true||!getMap().charDropCheck(charList.get(0).getX(),charList.get(0).getY())&&isTop&&getMap().charDropCheck(charList.get(0).getX(),charList.get(0).getY()-10)){
                    if( charList.get(0).getCharface()==true){
                        charList.get(0).setIcon(stand[0]);
                    }else{
                        charList.get(0).setIcon(stand[3]);
                    }
                    //數值初始化
                    first=true;
                    isTop=false;
                    //解鎖按鍵事件
                    timeFlag=false;
                    keyFlag=false;
                    jumpT.stop();
                    standT.restart();
                }else if(charList.get(0).getY()==y-250&&isTop==false){
                    isTop=true;
                    charList.get(0).setLocation(charList.get(0).getX(), charList.get(0).getY() + 10);
                }else if(isTop==true){
                    charList.get(0).setLocation(charList.get(0).getX(), charList.get(0).getY() + 10);
                }
            }
        });
        jumpRLT=new Timer(30, new ActionListener() {
            boolean first=true;
            int x,y;
            boolean isTop=false;
            @Override
            public void actionPerformed(ActionEvent e) {
                standT.stop();
                System.out.println("");
                timeFlag=true;
                if(first==true){
                    x= charList.get(0).getX();
                    y= charList.get(0).getY();
                    first=false;
                }
                if(charList.get(0).getY()>y-100&&isTop==false) {
                   if( map.setObjCheck(charList.get(0).getX()+5, charList.get(0).getY()-10, 84, 81) ){
                        map.removeChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                       charList.get(0).setLocation(charList.get(0).getX() + 5, charList.get(0).getY() - 10);
                        map.setChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                    }else{
                       jumpRLT.stop();
                       dropT.start();
                   }
                }else if(charList.get(0).getY()==y&&isTop==true){
                    if( charList.get(0).getCharface()==true){
                        charList.get(0).setIcon(stand[0]);
                    }else{
                        charList.get(0).setIcon(stand[3]);
                    }
                    //數值初始化
                    first=true;
                    isTop=false;
                    //解鎖按鍵事件
                    timeFlag=false;
                    keyFlag=false;
                    jumpRLT.stop();
                    standT.restart();
                }else if(charList.get(0).getY()==y-100&&isTop==false){
                    if( map.setObjCheck(charList.get(0).getX()+5, charList.get(0).getY()-10, 84, 81) ) {
                        map.removeChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                        isTop = true;
                        charList.get(0).setLocation(charList.get(0).getX() + 5, charList.get(0).getY() + 10);
                        map.setChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                    }else{
                        jumpRLT.stop();
                        dropT.start();
                    }
                }else if(isTop==true){
                    if( map.setObjCheck(charList.get(0).getX()+5, charList.get(0).getY()+10, 84, 81)) {
                        map.removeChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                        charList.get(0).setLocation(charList.get(0).getX() + 5, charList.get(0).getY() + 10);
                        map.setChar(charList.get(0).getX(), charList.get(0).getY(), 84, 81);
                    }else{
                        jumpRLT.stop();
                        dropT.start();
                    }
                }
            }

        });
        dropT=new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeFlag=true;
//                keyFlag=false;
                if(map.charDropCheck(charList.get(0).getX(),charList.get(0).getY())==true){
                    map.charDrop(charList.get(0).getX(),charList.get(0).getY());
                    charList.get(0).setLocation(charList.get(0).getX(),charList.get(0).getY()+10);
                }else{
                    dropT.stop();
                    map.printMap();
                    if(charList.get(0).getCharface()==true) {
                        charList.get(0).setIcon(stand[0]);
                        timeFlag=false;
                    }else{
                        charList.get(0).setIcon(stand[3]);
                        timeFlag=false;
                    }
//                    keyFlag=true;
                    System.out.println("("+charList.get(0).getX()+","+charList.get(0).getY()+")");
                    standT.start();
                }
            }
        });
        skillatkT=new Timer(125, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



       // 遊戲一開始的配置動畫
        standT.start();
        if(map.charDropCheck(charList.get(0).getX(),charList.get(0).getY())){
            standT.stop();
            dropT.start();
            if(charList.get(0).getCharface()){
                charList.get(0).setIcon(jump[0]);
            }else {
                charList.get(0).setIcon(jump[1]);
            }
        }

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DOWN) {
                    charList.get(0).setDown(true);
                }else if(key==KeyEvent.VK_SPACE){
                    charList.get(0).setUp(true);
                    if(!keyFlag){
                        System.out.println("Space");
                        if (charList.get(0).getCharface()) {
                            charList.get(0).setIcon(jump[0]);
                        } else {
                            charList.get(0).setIcon(jump[1]);
                        }
                        standT.stop();
                        jumpT.restart();
                    }
                }else if(key==KeyEvent.VK_LEFT){
                    charList.get(0).setLeft(true);
                    if(!keyFlag){
                        if (map.setWalkCheck(charList.get(0).getX(), charList.get(0).getY(), 84,81,true)) {
                            charList.get(0).setCharface(true);
                            map.removeChar(charList.get(0).getX(),charList.get(0).getY(),84,81);
//                            charList.get(0).setLocation(charList.get(0).getX() + 10, charList.get(0).getY());
                            map.setChar(charList.get(0).getX(),charList.get(0).getY(),84,81);
                            charList.get(0).setIcon(walk[0]);
                            standT.stop();
                            if(!map.charDropCheck(charList.get(0).getX(),charList.get(0).getY())) {
                                walkT.start();
                            }else{
                                dropT.start();
                            }
                            keyFlag = true;
                        }
                    }
//                    backgroundLabel.setCharface("left");
                }else if(key==KeyEvent.VK_RIGHT){
                    charList.get(0).setRight(true);
                    if(!keyFlag){
                        if (map.setWalkCheck(charList.get(0).getX(),charList.get(0).getY(), 84,81,false)) {
                            charList.get(0).setCharface(false);
                            map.removeChar(charList.get(0).getX(),charList.get(0).getY(),84,81);
//                            charList.get(0).setLocation(charList.get(0).getX() + 10, charList.get(0).getY());
                            map.setChar(charList.get(0).getX(),charList.get(0).getY(),84,81);
                            charList.get(0).setIcon(walk[4]);
                            standT.stop();
                            if(!map.charDropCheck(charList.get(0).getX(),charList.get(0).getY())) {
                                walkT.start();
                            }else{
                                dropT.start();
                            }
                            keyFlag = true;
                        }
                    }
//                    backgroundLabel.setCharface("right");
                }else if(key==KeyEvent.VK_Z){
                    charList.get(0).setAtt(true);
                    if(!keyFlag) {
                        standT.stop();
                        attackT.start();
                        if (charList.get(0).getCharface() == true) {
                            charList.get(0).setIcon(attack[0]);
                        } else {
                            charList.get(0).setIcon(attack[4]);
                        }
                        keyFlag = true;
                    }
                }else if(key==KeyEvent.VK_X){
                    charList.get(0).setSkill(true);
                    if(!keyFlag) {
                        standT.stop();
//                        skillT.start();
                        if (charList.get(0).getCharface() == true) {
                            charList.get(0).setIcon(attack[0]);
                        } else {
                            charList.get(0).setIcon(attack[4]);
                        }
                        keyFlag = true;
                    }

                }
//                if(!keyFlag){
//                    keyFlag = true;
//                }

            }
//            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DOWN) {
                    charList.get(0).setDown(false);
                }else if(key==KeyEvent.VK_SPACE){
                    charList.get(0).setUp(false);
                }else if(key==KeyEvent.VK_LEFT){
                    if (keyFlag) {
                        charList.get(0).setIcon(stand[0]);
                        standT.restart();
                        walkT.stop();
                        keyFlag = false;
//                        System.out.println("Left_false");
                    }
                    charList.get(0).setLeft(false);
                }else if(key==KeyEvent.VK_RIGHT){
                    charList.get(0).setRight(false);
                    if (keyFlag) {
                        charList.get(0).setIcon(stand[3]);
                        standT.restart();
                        walkT.stop();
                        keyFlag = false;
                    }
                }else if(key==KeyEvent.VK_Z){
                    charList.get(0).setAtt(false);
                }else if(key==KeyEvent.VK_X){
                    charList.get(0).setSkill(false);
                }
//                if(keyFlag){
//                    keyFlag = false;
//                }

            }
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int key = e.getKeyCode();
//                if(timeFlag==false) {
//                    if (right == true && key == KeyEvent.VK_SPACE || left == true && key == KeyEvent.VK_SPACE) {
//                        if (right == true) {
//                            System.out.println("right jump");
//                            if(walkFlag==true){
//                                walkT.stop();
//                                walkFlag=false;
//                            }
//                            character[0].setIcon(jump[1]);
//                            jumpRLT.start();
//
//                        } else {
//                            System.out.println("left jump");
//                        }
//                        if (keyFlag == false) {
//                            keyFlag = true;
//                        }
//                    } else {
//
//                        if (key == KeyEvent.VK_DOWN) {
//
//                            down = true;
//
//                        } else if (key == KeyEvent.VK_SPACE) {
//                            up = true;
//                            if (keyFlag == false) {
////                            System.out.println("Space");
//                                if (charList.get(0).getCharface() == true) {
//                                    character[0].setIcon(jump[0]);
//                                } else {
//                                    character[0].setIcon(jump[1]);
//                                }
//                                standT.stop();
//                                jumpT.restart();
//                                keyFlag = true;
//                            }
//                        } else if (key == KeyEvent.VK_RIGHT) {
//                            right = true;
//                            if (keyFlag == false) {
//                                if (map.setWalkCheck(character[0].getX(), character[0].getY(), 84,81,false) == true) {
//                                    charList.get(0).setCharface(false);
//                                    map.removeChar(character[0].getX(),character[0].getY(),84,81);
//                                    character[0].setLocation(character[0].getX() + 10, character[0].getY());
//                                    map.setChar(character[0].getX(),character[0].getY(),84,81);
//                                    character[0].setIcon(walk[4]);
//                                    standT.stop();
//                                    if(map.charDropCheck(character[0].getX(),character[0].getY())==false) {
//                                        walkT.start();
//                                    }else{
//                                        dropT.start();
//                                    }
//                                    keyFlag = true;
//                                }
//                            }
//                        } else if (key == KeyEvent.VK_LEFT) {
//                            left = true;
//                            if (keyFlag == false) {
//                                if (map.setWalkCheck(character[0].getX(), character[0].getY(), 84,81,true) == true) {
//                                    charList.get(0).setCharface(true);
//                                    map.removeChar(character[0].getX(),character[0].getY(),84,81);
//                                    character[0].setLocation(character[0].getX() - 10, character[0].getY());
//                                    map.setChar(character[0].getX(),character[0].getY(),84,81);
//                                    character[0].setIcon(walk[0]);
//                                    standT.stop();
//                                    if(map.charDropCheck(character[0].getX(),character[0].getY())==false) {
//                                        walkT.start();
//                                    }else{
//                                        dropT.start();
//                                    }
//                                    keyFlag = true;
//                                }
//                            }
//                        } else if (key == KeyEvent.VK_Z) {
//                            att = true;
//                            if (keyFlag == false) {
//                                standT.stop();
//                                attackT.start();
//                                keyFlag = true;
//                            }
//                        }
//                    }
//                }
//            }

//            @Override
//            public void keyReleased(KeyEvent e) {
//                int key = e.getKeyCode();
//                if (timeFlag==false) {
//                    if (key == KeyEvent.VK_DOWN) {
//                        down = false;
//                        if (keyFlag == true) {
//
//                            keyFlag = false;
//                        }
//                    } else if (key == KeyEvent.VK_SPACE) {
//                        up = false;
//                        if (keyFlag == true) {
////                        if(charList.get(0).getCharface()==true){
////                            character[0].setIcon(stand[0]);
////                        }else{
////                            character[0].setIcon(stand[3]);
////                        }
////                        jumpT.stop();
////                        standT.start();
////                            System.out.println("SpaceEnd");
//                        }
//                    } else if (key == KeyEvent.VK_RIGHT) {
//                        right = false;
//                        walkFlag=false;
//                        if (keyFlag == true) {
//                            character[0].setIcon(stand[3]);
//                            standT.restart();
//                            walkT.stop();
//                            keyFlag = false;
//                        }
//                    } else if (key == KeyEvent.VK_LEFT) {
//                        left = false;
//                        timeFlag=false;
//                        if (keyFlag == true) {
//                            character[0].setIcon(stand[0]);
//                            standT.restart();
//                            walkT.stop();
//                            keyFlag = false;
//                        }
//                    } else if (key == KeyEvent.VK_Z) {
//                        att = false;
//                        if (keyFlag == true) {
////                        character[0].setIcon(stand[0]);
////                        standT.restart();
////                        keyFlag=false;
//                        }
//                    }
//                }
//            }
//
        });
//        jlyPane.add(backgroundP, JLayeredPane.PALETTE_LAYER,new Integer(102));
//        jlyPane.add();
    }
    private void setCharAnimal(int charType){
        for(int i=0;i<3;i++){
            alert[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/alert/left/alert_"+Integer.toString(i)+".png");
//            System.out.println("NewCharacter/Character0"+Integer.toString(charType)+"/alert/left/alert_"+Integer.toString(i)+".png");
        }
//        System.out.println("NewCharacter/Character01/alert/left");
        for(int i=3;i<6;i++){
            alert[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/alert/right/alert_"+Integer.toString(i-3)+".png");
        }

        for(int i=0;i<4;i++){
            walk[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/walk/left/walk1_"+Integer.toString(i)+".png");
        }

        for(int i=4;i<8;i++){
            walk[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/walk/right/walk1_"+Integer.toString(i-4)+".png");
        }
        jump[0]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/jump/left/jump_"+Integer.toString(0)+".png");
        jump[1]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/jump/right/jump_"+Integer.toString(0)+".png");

        for(int i=0;i<3;i++){
            stand[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/stand/left/stand1_"+Integer.toString(i)+".png");
//            System.out.println("NewCharacter/Character0"+Integer.toString(charType)+"/stand/left/stand1_"+Integer.toString(i)+".png");
        }
//        System.out.println("NewCharacter/Character01/stand/left");
        for(int i=3;i<6;i++){
            stand[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/stand/right/stand1_"+Integer.toString(i-3)+".png");
        }
        for(int i=0;i<2;i++){
            attack[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/attack/left/stabO1_"+Integer.toString(i)+".png");
        }
        for(int i=2;i<4;i++){
            attack[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/attack/left/stabO2_"+Integer.toString(i-2)+".png");
        }
        for(int i=4;i<6;i++){
            attack[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/attack/right/stabO1_"+Integer.toString(i-4)+".png");
        }
        for(int i=6;i<8;i++){
            attack[i]=new ImageIcon("NewCharacter/Character0"+Integer.toString(charType)+"/attack/right/stabO2_"+Integer.toString(i-6)+".png");
        }
        for(int i=12;i<15;i++){

        }
        for(int i=15;i<18;i++){

        }
    }
//    public void imgAminal(int start,int end,boolean charface){
//        for(){}
//
//    }
    public BackgroundFloor getBackgroundLabel(){
        return backgroundLabel;
    }
    public String getNowC(){
        return nowC;
    }
    public void setNowC(String nowC1){
        this.nowC=nowC1;
    }
    public boolean getRight(){
        return charList.get(0).getRight();
    }
    public boolean getLeft(){
        return charList.get(0).getLeft();
    }
    public Character getchar(){
        return charList.get(0);
    }
    public Map getMap(){
        return  this.map;
    }
    public JLayeredPane getJlyPane(){
        return jlyPane;
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public ArrayList<Thread> getMonsterThreadList() {
        return monsterThreadList;
    }
}
