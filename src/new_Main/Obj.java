package new_Main;

import javax.swing.*;

public class Obj extends JLabel implements Runnable{
    private MainFrame mf;
    private int mapstart=0;
    public Obj(MainFrame mf){
        this.mf=mf;
    }

    @Override
    public void run() {
        while(true){
//            if(mf.getNowC().equals("right")){
//                this.setLocation(this.getX()-10,this.getY());
//            }else if(mf.getNowC().equals("left")){
//                this.setLocation(this.getX()+10,this.getY());
//            }
           int all=(mapstart-mf.getMap().getMapDrawStart())*10;
           mapstart=mf.getMap().getMapDrawStart();
            this.setLocation(this.getX()+all,this.getY());
            try {
                Thread.sleep(145);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
