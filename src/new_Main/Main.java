package new_Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Main{
    public static void main(String args[]) throws Exception{
//        StartFrame sf=new StartFrame();
//        sf.setVisible(true);
        MainFrame mf=new MainFrame();
        mf.setVisible(true);

        AudioInputStream inputStream= AudioSystem.getAudioInputStream(new File("Maplestory - Henesys Floral Life.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep(10000);
    }
}
