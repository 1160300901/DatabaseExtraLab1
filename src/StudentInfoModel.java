import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class StudentInfoModel extends AbstractTableModel {

        Vector rowData, columnNames;

        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;

        public StudentInfoModel(String sql) {
            columnNames = new Vector<String>();
            columnNames.add("Sid");
            columnNames.add("Sname");
            columnNames.add("Sage");
            columnNames.add("Ssex");
            columnNames.add("Sclass");
            columnNames.add("Sdept");
            columnNames.add("Saddr");

            rowData = new Vector<Vector<String>>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                ct = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false",
                        "root", "123456");
                ps = ct.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Vector hang = new Vector<String>();
                    hang.add(rs.getString(1));
                    hang.add(rs.getString(2));
                    hang.add(rs.getInt(3));
                    hang.add(rs.getString(4));
                    hang.add(rs.getString(5));
                    hang.add(rs.getString(6));
                    hang.add(rs.getString(7));
                    rowData.add(hang);

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (ps != null)
                        ps.close();
                    if (ct != null)
                        ct.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        // public StuModel(){
        // String s = "select * from stu";
        // StuModel(s);
        //
        // }

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return this.columnNames.size();
        }

        @Override
        public String getColumnName(int column) {
            // TODO Auto-generated method stub
            return (String) this.columnNames.get(column);
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return this.rowData.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // TODO Auto-generated method stub
            return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
        }
    }