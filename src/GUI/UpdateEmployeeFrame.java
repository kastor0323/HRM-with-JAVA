/*
	클래스 목적:
	HRM_hrm의 클래스에서 삭제 버튼을 눌렀을 경우 나타나는 창이다.
	사원 번호, 이름, 매장명, 부서명, 전화번호, 이메일, 계좌번호를 입력받고
	DB EmployeesDAO에 mysql문에 연결하여 사번은 기본키이기 때문에 이를 매칭하여 DB에 있는 사번이면
	이름, 매장명, 부서명, 전화번호, 이메일, 계좌번호를 입력받도록 하는 클래스이다.
*/
package GUI;

import javax.swing.*;
import javax.swing.JOptionPane;

import DB.EmployeesDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateEmployeeFrame extends JFrame implements ActionListener {
	private JLabel labelTop;
	private JLabel labelBottom;
	private JLabel labelID;
    private JLabel labelStore;
    private JLabel labelName;
    private JLabel labelDepartmentName;
    private JLabel labelPhoneNumber;
    private JLabel labelEmail;
    private JLabel labelAccount;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtStore;
    private JTextField txtDepartmentName;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;
    private JTextField txtAcccount;
    private JButton buttonUpdate;
    public UpdateEmployeeFrame() {
        setTitle("사원 변경");
        setSize(500, 900);
        
        EmployeesDAO employeesdao = EmployeesDAO.getInstance();
        setLayout(null);
        
        labelTop = new JLabel("수정할 사원의 사번을 입력하세요");
        labelTop.setSize(200, 30);
        labelTop.setLocation(150, 0);
        
        labelID = new JLabel("사번");
        txtID = new JTextField();
        labelID.setSize(100, 30);
        labelID.setLocation(70, 30);
        txtID.setBounds(250, 30, 200, 40);
        
        labelBottom = new JLabel("수정할 내용을 입력하세요");
        labelBottom.setSize(200, 30);
        labelBottom.setLocation(150, 80);
       
        labelStore = new JLabel("매장명");
        txtStore = new JTextField();
        labelStore.setSize(100, 30);
        labelStore.setLocation(70, 130);
        txtStore.setBounds(250, 130, 200, 40);
        
        labelName = new JLabel("이름");
        txtName = new JTextField();
        labelName.setSize(100, 30);
        labelName.setLocation(70, 230);
        txtName.setBounds(250, 230, 200, 40);
        
        labelDepartmentName = new JLabel("부서");
        txtDepartmentName = new JTextField();
        labelDepartmentName.setSize(100, 30);
        labelDepartmentName.setLocation(70, 330);
        txtDepartmentName.setBounds(250, 330, 200, 40);
        
        labelPhoneNumber = new JLabel("전화번호");
        txtPhoneNumber = new JTextField();
        labelPhoneNumber.setSize(100, 30);
        labelPhoneNumber.setLocation(70, 430);
        txtPhoneNumber.setBounds(250, 430, 200, 40);
        
        labelEmail = new JLabel("이메일");
        txtEmail = new JTextField();
        labelEmail.setSize(100, 30);
        labelEmail.setLocation(70, 530);
        txtEmail.setBounds(250, 530, 200, 40);
        
        labelAccount = new JLabel("계좌");
        txtAcccount = new JTextField();
        labelAccount.setSize(100, 30);
        labelAccount.setLocation(70, 630);
        txtAcccount.setBounds(250, 630, 200, 40);
        
        buttonUpdate = new JButton("수정");
        buttonUpdate.setBounds(350, 730, 100, 40);
        
        add(labelTop);
        add(labelBottom);
        add(labelID);
        add(txtID);
        add(labelStore);
        add(txtStore);
        add(labelName);
        add(txtName);
        add(labelDepartmentName);
        add(txtDepartmentName);
        add(labelPhoneNumber);
        add(txtPhoneNumber);
        add(labelEmail);
        add(txtEmail);
        add(labelAccount);
        add(txtAcccount);
        add(buttonUpdate);
        
        buttonUpdate.addActionListener(this);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 프레임 표시
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonUpdate) {
        	try {
				EmployeesDAO.getInstance().UpdateEmployee(txtID.getText(), txtStore.getText(), txtName.getText(), txtDepartmentName.getText(), 
						txtPhoneNumber.getText(), txtEmail.getText(), txtAcccount.getText());
				JOptionPane.showMessageDialog(this, "사원 수정 완료!");
				dispose();
				System.out.println("사원수정완료");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }
    }
   }
