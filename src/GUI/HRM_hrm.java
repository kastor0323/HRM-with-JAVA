/*
	클래스 목적:
	인사관리 프로그램의 메인 클래스이다.
	사번, 매장명, 이름, 부서명의 검색 조건을 만들었고
	추가, 변경, 삭제, 검색 네 개의 검색 버튼을 만들어 각 버튼을 클릭 하였을 경우 ActionPerformed을 통해 다음 코드를 실행하도록 하였다.
	검색 결과의 경우 검색 결과를 바를 통해 데이터 값을 스크롤바로 내려볼 수 있으며, 값은 EmployeesDAO를 통해 불러오도록 한다.
	
	//HRM_hrm은 팀원 백정현의 개인 프로젝트 초기 단계로 이후 개발된 여러 버튼에 따른 클래스처럼 분리하지 않고 해당 클래스에 같이 코드하였습니다.
*/

package GUI;

import DB.EmployeesVO;
import DB.EmployeesDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class HRM_hrm extends JPanel implements ActionListener {
	private JLabel labelID;
	private JLabel labelStore;
	private JLabel labelName;
	private JLabel labelDepartmentName;
	private JComboBox comboBoxDepartmentName;
	private JTextField txtID;
    private JTextField txtName;
    private JTextField txtStore;
    private JButton buttonCreate;
    private JButton buttonSearch;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JTable jtableEmployees;
    
    public HRM_hrm() throws SQLException {
        EmployeesDAO employeesdao = EmployeesDAO.getInstance();
        
        setLayout(null);
        
        // 검색 패널
        labelID = new JLabel("사번");
        txtID = new JTextField();
        labelID.setSize(100, 30);
        labelID.setLocation(10, 30);
        txtID.setBounds(60, 30, 200, 40);

        labelStore = new JLabel("매장명");
        txtStore = new JTextField();
        labelStore.setSize(100, 30);
        labelStore.setLocation(280, 30);
        txtStore.setBounds(330, 30, 200, 40);

        labelName = new JLabel("이름");
        txtName = new JTextField();
        labelName.setSize(100, 30);
        labelName.setLocation(560, 30);
        txtName.setBounds(610, 30, 200, 40);
        
        labelDepartmentName = new JLabel("부서");
        labelDepartmentName.setSize(100, 30);
        labelDepartmentName.setLocation(830, 30);

        DefaultComboBoxModel combomodel = combo_model_update();
        comboBoxDepartmentName = new JComboBox(combomodel);
        comboBoxDepartmentName.setSize(200, 30);					
        comboBoxDepartmentName.setLocation(880, 30);
        
        // 버튼
        buttonSearch = new JButton("검색");
        buttonSearch.setBounds(980, 80, 100, 40);
        
        buttonCreate = new JButton("추가");
        buttonCreate.setBounds(840, 80, 100, 40);
        
        buttonDelete = new JButton("삭제");
        buttonDelete.setBounds(700, 80, 100, 40);
        
        buttonUpdate = new JButton("변경");
        buttonUpdate.setBounds(560, 80, 100, 40);
        
        add(labelID);
        add(txtID);
        add(labelStore);
        add(txtStore);
        add(labelName);
        add(txtName);
        add(labelDepartmentName);
        add(comboBoxDepartmentName);
        add(buttonSearch);
        add(buttonCreate);
        add(buttonDelete);
        add(buttonUpdate);
        
        
        //결과값
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("사번");
        model.addColumn("매장명");
        model.addColumn("이름");
        model.addColumn("부서명");
        model.addColumn("연락처");
        model.addColumn("이메일");
        model.addColumn("계좌");
        jtableEmployees = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jtableEmployees);
        scrollPane = new JScrollPane(jtableEmployees, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 180, 1080, 600);
        jtableEmployees.setEnabled(false);
        add(scrollPane);
        buttonSearch.addActionListener(this);
        buttonCreate.addActionListener(this);
        buttonUpdate.addActionListener(this);
        buttonDelete.addActionListener(this);

        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        DefaultTableModel model = (DefaultTableModel) jtableEmployees.getModel();
        if (source == buttonSearch) {
        	System.out.println("사원검색");
            // 검색 버튼이 클릭되었을 때의 동작
            try {
            	Vector<EmployeesVO> result = EmployeesDAO.getInstance().getResult(txtID.getText(), txtName.getText(), txtStore.getText(), comboBoxDepartmentName.getSelectedItem());
            	loadDB(model, result);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        else if(source == buttonCreate) {
        	System.out.println("사원등록");
        	NewEmployeeFrame newEmployeeFrame = new NewEmployeeFrame();
            newEmployeeFrame.setVisible(true);
        }
        else if(source == buttonDelete) {
        	System.out.println("사원삭제");
        	DeleteEmployeeFrame deleteemployeeFrame = new DeleteEmployeeFrame();
        	deleteemployeeFrame.setVisible(true);
        	// TODO 삭제하는 화면 구현
        }
        else if(source == buttonUpdate) {
        	System.out.println("사원변경");
        	UpdateEmployeeFrame updateemployeeFrame = new UpdateEmployeeFrame();
        	updateemployeeFrame.setVisible(true);
        	// TODO 변경하는 화면 구현
        }
    }
    
    // JComboBox의 선택값 가져오는 메서드 추가
    public JComboBox getComboBoxDepartmentName() {
        return comboBoxDepartmentName;
    }

    // ActionListener 추가하는 메서드
    public void addSearchButtonListener(ActionListener listener) {
        buttonSearch.addActionListener(listener);
    }
    
 // ActionListener 추가하는 메서드
    public void addCreateButtonListener(ActionListener listener) {
        buttonCreate.addActionListener(listener);
    }
    
 // ActionListener 추가하는 메서드
    public void addDeleteButtonListener(ActionListener listener) {
        buttonDelete.addActionListener(listener);
    }
    
 // ActionListener 추가하는 메서드
    public void addUpdateButtonListener(ActionListener listener) {
        buttonUpdate.addActionListener(listener);
    }
    
 // 데이터베이스 로딩
    private void loadDB(DefaultTableModel model, Vector<EmployeesVO> employeesList) {
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (EmployeesVO employees : employeesList) {
            System.out.println(model.getRowCount());
            String employees_id = String.valueOf(employees.getId());
            String employees_StoreName = String.valueOf(employees.getStoreName());
            String employees_Name = String.valueOf(employees.getName());
            String employees_DepartmentName = String.valueOf(employees.getDepartmentName());
            String employees_Phonenumber = employees.getPhonenumber();
            String employees_Email = employees.getEmail();
            String employees_Account = employees.getAccount();
            
            Vector<Object> rowData = new Vector<>();
            rowData.add(employees_id);
            rowData.add(employees_StoreName);
            rowData.add(employees_Name);
            rowData.add(employees_DepartmentName);
            rowData.add(employees_Phonenumber);
            rowData.add(employees_Email);
            rowData.add(employees_Account);

            model.addRow(rowData);
        }
    }
    
    
    //String을 vector로 변환
    private Vector<String> makeInVector(String[] array) {
        Vector<String> in = new Vector<>();
        for (String data : array) {
            in.add(data);
        }
        return in;
    }

    //table 조회
    private DefaultComboBoxModel combo_model_update() throws SQLException {
        DefaultComboBoxModel combomodel = new DefaultComboBoxModel(EmployeesDAO.getInstance().getEmployees());
        return combomodel;
    }
}
        
