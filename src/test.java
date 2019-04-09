import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;

public class test {
    public static JCheckBox cbx_id = new JCheckBox("学号");
    public static JFrame mainFrame = new JFrame();
    public static int firstflag = 0;
    public static JTextField sle_id = new JTextField();

    public test() {
        // 设置窗体皮肤
        SubstanceAutumnLookAndFeel.setSkin(new BusinessBlueSteelSkin());
        // 设置主窗体名称
        mainFrame.setTitle("学生信息查询");
        mainFrame.setBounds(150, 100, 800, 600);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        mainFrame.add(cbx_id);
//        mainFrame.add(sle_id);
        
        cbx_id.setBounds(20, 20, 80, 20);
//        sle_id.setBounds(110, 20, 80, 20);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new test();
        // cbx_id.addItemListener(null);;
        // 添加第一个复选框的状态被改变的监听（其他复选框如果需要监听状态改变，则可按此方法添加监听）
        cbx_id.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (cbx_id.isSelected()) {
                    System.out.println("你选中啦");    
                    firstflag = 1;
                    System.out.println("firstflag=" + firstflag);
                } else {
                    System.out.println("你放弃啦");
                    firstflag = 0;
                    System.out.println("firstflag=" + firstflag);
                }
            }
        });

        // 实现ItemListener接口方式监听复选框事件
        class MyItemListener implements ItemListener {
            // 实现ItemListener的方法，只有一个方法。
            public void itemStateChanged(ItemEvent e) {
                // 得到产生的事件，这里只有复选框所以可以强制类型转换。
                JCheckBox jcb = (JCheckBox) e.getItem(); // 得到产生的事件
                // 如果被选中了，则显示正确的图片
                if (jcb.isSelected()) { // 显示打钩
                    System.out.println("你选中啦");
                } else {
                    // 取消图片显示
                    jcb.setIcon(null);
                }
            }
        }
    }

        

                    


}
