import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;// 代表对特定数据库的连接
import java.sql.DriverManager;// 处理驱动的调入并且对产生新数据库连接提供支持
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;// 对特定的数据库执行sql语句
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;

import java.sql.ResultSet;// 从当前执行的sql语句中返回结果数据
import java.sql.ResultSetMetaData;

public class Query implements DocumentListener{

    public static JFrame mainFrame = new JFrame();
    // 定义复选框
    public static JCheckBox cbx_id = new JCheckBox("学号");
    public static JCheckBox cbx_name = new JCheckBox("姓名");
    public static JCheckBox cbx_age = new JCheckBox("年龄区间");
    public static JCheckBox cbx_sex = new JCheckBox("性别");
    public static JCheckBox cbx_class = new JCheckBox("班级");
    public static JCheckBox cbx_dept = new JCheckBox("系别");
    public static JCheckBox cbx_addr = new JCheckBox("地址");
    
    public static int cbx_id_flag = 0;
    public static int cbx_name_flag = 0;
    public static int cbx_age_flag = 0;
    public static int cbx_sex_flag = 0;
    public static int cbx_class_flag = 0;
    public static int cbx_dept_flag = 0;
    public static int cbx_addr_flag = 0;
    
    // 定义文本框
    public static JTextField sle_id = new JTextField();
    public static JTextField sle_name = new JTextField();
    public static JTextField sle_agest = new JTextField();
    public static JTextField sle_ageed = new JTextField();
    public static JTextField sle_sex = new JTextField();
    public static JTextField sle_class = new JTextField();
    public static JTextField sle_dept = new JTextField();
    public static JTextField sle_addr = new JTextField();
    // SQL语句显示
    public static JTextField sle_sqltext = new JTextField();

    // 查询按钮
    public static JButton query = new JButton("查询");

    // 表格
    public JTable table=new JTable();
    public StudentInfoModel stu=new StudentInfoModel("select * from student;");

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/students";
    public static final String URL = "jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";// 链接的mysql
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public static String str_temp = "";
    public static int firstflag = 0;

    public Query() {
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

        // 在主窗体中添加其他组件
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        mainFrame.add(cbx_id);
        mainFrame.add(cbx_name);
        mainFrame.add(cbx_age);
        mainFrame.add(cbx_sex);
        mainFrame.add(cbx_class);
        mainFrame.add(cbx_dept);
        mainFrame.add(cbx_addr);

        mainFrame.add(sle_id);
        mainFrame.add(sle_name);
        mainFrame.add(sle_agest);
        mainFrame.add(sle_ageed);
        mainFrame.add(sle_sex);
        mainFrame.add(sle_class);
        mainFrame.add(sle_dept);
        mainFrame.add(sle_addr);

        mainFrame.add(sle_sqltext);
        mainFrame.add(query);
        mainFrame.add(table);
        table.setModel(stu);

        query.setActionCommand("query");

        cbx_id.setBounds(20, 20, 80, 20);
        sle_id.setBounds(110, 20, 80, 20);
        cbx_name.setBounds(20, 45, 80, 20);
        sle_name.setBounds(110, 45, 80, 20);
        cbx_age.setBounds(20, 70, 80, 20);
        sle_agest.setBounds(110, 70, 40, 20);
        sle_ageed.setBounds(155, 70, 40, 20);
        cbx_sex.setBounds(20, 95, 80, 20);
        sle_sex.setBounds(110, 95, 80, 20);

        cbx_class.setBounds(250, 20, 80, 20);
        sle_class.setBounds(340, 20, 80, 20);
        cbx_dept.setBounds(250, 45, 80, 20);
        sle_dept.setBounds(340, 45, 80, 20);
        cbx_addr.setBounds(250, 70, 80, 20);
        sle_addr.setBounds(340, 70, 80, 20);

        query.setBounds(500, 40, 80, 40);
        sle_sqltext.setBounds(100, 130, 600, 200);

        table.setBounds(100, 350, 600, 200);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Query qy = new Query();

        // 基本语句
        str_temp = "select * from student where";

        // 学号
        cbx_id.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (cbx_id.isSelected()) {                   
                    cbx_id_flag=1;
                }
            }
        });

        // 姓名
        cbx_name.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_name.isSelected()) {
                    cbx_name_flag=1;
                }
            }
        });

        // 性别
        cbx_sex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_sex.isSelected()) {
                    cbx_sex_flag=1;
                }

            }
        });

        // 班级
        cbx_class.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_class.isSelected()) {
                    cbx_class_flag=1;
                }

            }
        });

        // 系别
        cbx_dept.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_dept.isSelected()) {
                    cbx_dept_flag=1;
                }

            }
        });

        // 地址
        cbx_addr.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_addr.isSelected()) {
                    cbx_addr_flag=1;
                }

            }
        });

        // 年龄
        cbx_age.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbx_age.isSelected()) {
                    cbx_age_flag=1;
                }

            }
        });



        // 注册 JDBC 驱动
        // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
        // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
        // Class.forName("com.mysql.jdbc.Driver");

        // 打开链接
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 进行逻辑处理即可
                if(cbx_id_flag==1&&sle_id.getText().trim().length() > 0) {                    
                    str_temp = str_temp + "(Sid like '" + sle_id.getText().trim() + "')";
//                    System.out.println("学号str_temp="+str_temp);
                    firstflag = 1;
//                    System.out.println("学号firstflag="+firstflag);
                }
                
                if(cbx_name_flag==1&&sle_name.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Sname like '" + sle_name.getText().trim() + "')";
                    } else {
                        str_temp = str_temp + "(Sname like '" + sle_name.getText().trim() + "')";
                        firstflag = 1;
                    }
                }
                
                if (cbx_sex_flag==1&&sle_sex.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Ssex like '" + sle_sex.getText().trim() + "')";
                    } else {
                        str_temp = str_temp + "(Ssex like '" + sle_sex.getText().trim() + "')";
                        firstflag = 1;
                    }
                }
                
                if (cbx_class_flag==1&&sle_class.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Sclass like '" + sle_class.getText().trim() + "')";
                    } else {
                        str_temp = str_temp + "(Sclass like '" + sle_class.getText().trim() + "')";
                        firstflag = 1;
                    }
                }
                
                if (cbx_dept_flag==1 && sle_dept.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Sdept like '" + sle_dept.getText().trim() + "')";
                    } else {
                        str_temp = str_temp + "(Sdept like '" + sle_dept.getText().trim() + "')";
                        firstflag = 1;
                    }
                }
                
                if (cbx_addr_flag==1&&sle_addr.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Saddr like '" + sle_addr.getText().trim() + "')";
                    } else {
                        str_temp = str_temp + "(Saddr like '" + sle_addr.getText().trim() + "')";
                        firstflag = 1;
                    }
                }
                
                if (cbx_age_flag==1&&sle_agest.getText().trim().length() > 0) {
                    if (firstflag == 1) {
                        str_temp = str_temp + "and (Sage >= " + sle_agest.getText().trim() + ")";
                        if (sle_ageed.getText().length() > 0
                                && Integer.parseInt(sle_ageed.getText()) > Integer.parseInt(sle_agest.getText())) {
                            str_temp = str_temp + " and (Sage<=" + sle_ageed.getText().trim() + ")";
                        }
                    } else {
                        str_temp = str_temp + "(Sage >= " + sle_agest.getText().trim() + ")";
                        if (sle_ageed.getText().length() > 0
                                && Integer.parseInt(sle_ageed.getText()) > Integer.parseInt(sle_agest.getText())) {
                            str_temp = str_temp + " and (Sage<=" + sle_ageed.getText().trim() + ")";
                        }
                        firstflag = 1;
                    }
                }
                str_temp = str_temp + ";";
                
                StudentInfoModel stu=new StudentInfoModel(str_temp);
                qy.table.setModel(stu);
                qy.sle_sqltext.setText("SQL语句："+str_temp);
            }

        });
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        
    }


}
