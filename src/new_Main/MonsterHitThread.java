package new_Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonsterHitThread implements Runnable {
    private Monster msr;
    private MainFrame mf;
    private Timer t1;
    public MonsterHitThread (Monster msr,MainFrame mf){
        this.msr=msr;
        this.mf=mf;
    }
    @Override
    public void run() {
        while(true){
            if(!mf.getMap().setCharCheck(msr.getX()-25,msr.getY(),msr.getSize().height,msr.getSize().width+50)&&mf.getchar().getAtt()){
                msr.gethit();
                System.out.println("Monster_hit");
                msr.setNowHp(mf.getchar().getCharAttack());
                try {
                    Thread.sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(msr.getNowHp()<=0){

                }
            }
//            System.out.println("msrth is running");
            if(!mf.getMap().setCharCheck(msr.getX(),msr.getY(),msr.getSize().height,msr.getSize().width)){
//            if( msr.getX()<mf.getchar().getX()+50&&msr.getX()>mf.getchar().getX()-50) {
//                System.out.println( msr.getX());
//                System.out.println(mf.getchar().getX()+81);
                System.out.println("hit");
                mf.getchar().setNowHp(mf.getchar().getNowHp()-msr.getAttack());
//                System.out.println(mf.getchar().getNowHp());
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
