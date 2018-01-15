package LayeredPane_Test;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private JLayeredPane jlyPane =new JLayeredPane();
    private JLabel[] label=new JLabel[7];
    private Point position=new Point(10,10);
    Color[] colors={
            Color.red,Color.blue,Color.magenta,Color.cyan,Color.yellow,
            Color.green,Color.pink
    };
    public MainFrame(){
        initComp();
    }
    private void initComp(){
        this.setTitle("Ex_JLayeredPane");
        this.setBounds(100,200,700,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jlyPane=getLayeredPane();
        for(int i=0;i<7;i++){
            label[i]=new JLabel(Integer.toString(i),JLabel.CENTER);
            position.x=position.x+20;
            position.y=position.y+20;
            label[i].setBounds(position.x,position.y,100,100);
            label[i].setOpaque(true);
            label[i].setBackground(colors[i]);
//            jlyPane
            jlyPane.add(label[i],new Integer(100+i));
        }
    }
    //Reference
    //http://blog.csdn.net/levelmini/article/details/26692205
    //https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
}
