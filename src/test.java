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
    public static JCheckBox cbx_id = new JCheckBox("ѧ��");
    public static JFrame mainFrame = new JFrame();
    public static int firstflag = 0;
    public static JTextField sle_id = new JTextField();

    public test() {
        // ���ô���Ƥ��
        SubstanceAutumnLookAndFeel.setSkin(new BusinessBlueSteelSkin());
        // ��������������
        mainFrame.setTitle("ѧ����Ϣ��ѯ");
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
        // ��ӵ�һ����ѡ���״̬���ı�ļ�����������ѡ�������Ҫ����״̬�ı䣬��ɰ��˷�����Ӽ�����
        cbx_id.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (cbx_id.isSelected()) {
                    System.out.println("��ѡ����");    
                    firstflag = 1;
                    System.out.println("firstflag=" + firstflag);
                } else {
                    System.out.println("�������");
                    firstflag = 0;
                    System.out.println("firstflag=" + firstflag);
                }
            }
        });

        // ʵ��ItemListener�ӿڷ�ʽ������ѡ���¼�
        class MyItemListener implements ItemListener {
            // ʵ��ItemListener�ķ�����ֻ��һ��������
            public void itemStateChanged(ItemEvent e) {
                // �õ��������¼�������ֻ�и�ѡ�����Կ���ǿ������ת����
                JCheckBox jcb = (JCheckBox) e.getItem(); // �õ��������¼�
                // �����ѡ���ˣ�����ʾ��ȷ��ͼƬ
                if (jcb.isSelected()) { // ��ʾ��
                    System.out.println("��ѡ����");
                } else {
                    // ȡ��ͼƬ��ʾ
                    jcb.setIcon(null);
                }
            }
        }
    }

        

                    


}
