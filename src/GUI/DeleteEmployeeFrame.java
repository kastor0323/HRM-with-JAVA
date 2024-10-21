/*
	클래스 목적:
	HRM_hrm의 클래스에서 삭제 버튼을 눌렀을 경우 나타나는 창이다.
	사원 번호를 입력받고 텍스트 필드를 액션리스너로 받아 
	DB EmployeesDAO에 mysql문에 연결하여 DB에 값을 삭제하는 클래스이다.
*/

package GUI;

import javax.swing.*;

import DB.EmployeesDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteEmployeeFrame extends JFrame implements ActionListener {
	private JLabel labelID;
	private JLabel labelTop;
	private JTextField txtID;
	private JButton buttonDelete;
    public DeleteEmployeeFrame() {
    	
        setTitle("사원 삭제");
        setSize(400, 300);
        EmployeesDAO employeesdao = EmployeesDAO.getInstance();
        setLayout(null);
        
        labelTop = new JLabel("삭제할 사원의 사번을 입력하세요.");
        labelTop.setSize(200, 30);
        labelTop.setLocation(120, 50);
        
        labelID = new JLabel("사번");
        txtID = new JTextField();
        labelID.setSize(100, 30);
        labelID.setLocation(50, 100);
        txtID.setBounds(150, 100, 200, 40);
        
        buttonDelete = new JButton("삭제");
        buttonDelete.setBounds(250, 180, 100, 40);
        
        add(labelTop);
        add(labelID);
        add(txtID);
        add(buttonDelete);
        
        buttonDelete.addActionListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 프레임 표시
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonDelete) {
        	try {
				EmployeesDAO.getInstance().DeleteEmployee(txtID.getText());
				JOptionPane.showMessageDialog(this, "사원 삭제 완료!");
				System.out.println("사원삭제완료");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
    }
}
