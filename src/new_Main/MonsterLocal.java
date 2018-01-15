package new_Main;

public class MonsterLocal implements Runnable{
    private MainFrame mf;
    private Monster msr;
    public MonsterLocal(Monster msr, MainFrame mf){
        this.mf=mf;
        this.msr=msr;
    }

    @Override
    public void run() {
        while(true){
//            int all=(msr.getMapstart()-mf.getMap().getMapDrawStart())*10;
//            msr.setMapstart(mf.getMap().getMapDrawStart());
//            msr.setLocation(msr.getX()+all,msr.getY());
//            try {
//                Thread.sleep(145);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
