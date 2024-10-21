/*
	클래스 목적 :
	제일 먼저 실행되는 창으로
	인사관리 프로그램에 로그인하기 위해 로그인과 아이디를 입력 받도록하고 있다.
	만약 아이디가 없다면 회원가입을 통해 아이디, 비밀번호, 이름을 입력하여 회원가입할 수 있도록 한다.
	
	//로그인 클래스는 개발 도중 팀원 윤남균이 개발하여 합쳐서 로그인에 대한 mysql문이 ManagerDAO가 아닌 해당 클래스에서 진행하였습니다.
 */
package GUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DB.ManagerDAO;

public class LoginFrame extends JFrame implements ActionListener {
   
    private ManagerDAO managerDAO;
    
    private JTextField idTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();
    private JButton loginButton = new JButton("로그인");

    public LoginFrame() {
        super("HRM");
        managerDAO = ManagerDAO.getInstance();
        managerDAO.connect(); // connect 메서드 호출하여 con 변수 초기화
        buildGUI();
        setEvent();
    }

    private void buildGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 320);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelID = new JLabel("아이디  : ");
        idTextField.setColumns(10);  
        labelID.setSize(100, 30);
        labelID.setLocation(100, 50);
        idTextField.setBounds(250, 50, 200, 40);

        JLabel labelPassword = new JLabel("비밀번호  : ");
        passwordTextField.setColumns(10);
        labelPassword.setSize(100, 30);
        labelPassword.setLocation(100, 120);
        passwordTextField.setBounds(250, 120, 200, 40);

        JButton registerButton = new JButton("회원가입");
        registerButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setBounds(350, 220, 100, 40);
        registerButton.setBounds(220, 220, 100, 40);
        
        
        add(labelID);
        add(idTextField);
        add(labelPassword);
        add(passwordTextField);
        add(loginButton);
        add(registerButton);


        // 회원가입 버튼 액션 리스너 추가
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 창을 띄우는 로직
                JFrame signUpFrame = new JFrame("회원가입");
                signUpFrame.setSize(500, 320);
                signUpFrame.setLocationRelativeTo(LoginFrame.this);
                signUpFrame.setLayout(null);

                JLabel labelID = new JLabel("아이디 : ");
                JTextField idTextField = new JTextField(100); // 변수명 수정
                labelID.setSize(100, 30);
                labelID.setLocation(100, 120);
                idTextField.setBounds(250, 120, 200, 40); // 변수명 수정

                JLabel labelPassword = new JLabel("비밀번호 : ");
                JPasswordField passwordTextField = new JPasswordField(50); // 변수명 수정
                labelPassword.setSize(100, 30);
                labelPassword.setLocation(100, 190);
                passwordTextField.setBounds(250, 180, 200, 40); // 변수명 수정

                JLabel labelName = new JLabel("이름 : ");
                JTextField nameTextField = new JTextField(50); // 변수명 수정
                labelName.setSize(100, 30);
                labelName.setLocation(100, 50);
                nameTextField.setBounds(250, 50, 200, 40); // 변수명 수정

                JButton confirmButton = new JButton("확인");
                confirmButton.setPreferredSize(new Dimension(100, 200));
                confirmButton.setBounds(250, 230, 200, 40); // 변수명 수정

                signUpFrame.add(labelID);
                signUpFrame.add(idTextField); // 변수명 수정
                signUpFrame.add(labelName);
                signUpFrame.add(nameTextField); // 변수명 수정
                signUpFrame.add(labelPassword);
                signUpFrame.add(passwordTextField); // 변수명 수정
                signUpFrame.add(confirmButton);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 사용자가 입력한 정보 가져오기
                        String id = idTextField.getText(); // 변수명 수정
                        String name = nameTextField.getText(); // 변수명 수정
                        String password = new String(passwordTextField.getPassword()); // 변수명 수정
                        
                    // DB 연결 정보
                        String url = "jdbc:mysql://localhost/employees?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8"; // DB URL
                        String username = "root"; // DB 사용자명
                        String dbPassword = "1234"; // DB 비밀번호

                        try (Connection conn = DriverManager.getConnection(url, username, dbPassword)) {
                            // DB에 사용자 정보 삽입 쿼리
                            String insertQuery = "INSERT INTO manager VALUES (?, ?, ?)";
                            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                                preparedStatement.setString(1, id); // 'id' 필드에 사용자가 입력한 ID 값을 설정
                                preparedStatement.setString(2, name); // 'name' 필드에 사용자가 입력한 이름 값을 설정
                                preparedStatement.setString(3, password);
                                preparedStatement.executeUpdate();
                            }

                            JOptionPane.showMessageDialog(null, "회원가입 성공!");
                            signUpFrame.dispose(); // 회원가입 창 닫기
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(LoginFrame.this, "회원가입 중 오류가 발생했습니다.");
                        }
                    }
                });

                signUpFrame.setVisible(true);
            }
        });

        // 로그인 버튼 액션 리스너는 이미 위에서 구현되어 있습니다.
        setVisible(true);
    }





    private void setEvent() {
        loginButton.addActionListener(this);
        passwordTextField.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == loginButton || e.getSource() == passwordTextField) {
               String id = idTextField.getText();
               String password = new String(passwordTextField.getPassword());
               
               // 아이디와 비밀번호가 모두 입력되었는지 확인
               if (id.isEmpty() || password.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 모두 입력해주세요.");
                   return; // 아이디 또는 비밀번호가 비어있다면 로그인 시도를 하지 않음
               }

               ManagerDAO managerDAO = ManagerDAO.getInstance();
               int loginResult = managerDAO.login(id, password);
               handleLoginResult(loginResult);
           }
    }
    private void handleLoginResult(int loginResult) {
        switch (loginResult) {
            case 0:
                loginSuccess();
                break;
            case -1:
                passwordError();
                break;
            case -2:
                loginFail();
                break;
            default:
                break;
        }
    }
    

    private void loginSuccess() {
        try {
            JOptionPane.showMessageDialog(this, "로그인 성공!");
            // 로그인 성공 시 MainHRM을 띄웁니다.
            MainHRM mainHRM = new MainHRM();
            mainHRM.setTitle("HRM 프로그램");
            mainHRM.hrm = new HRM_hrm();
            
            JTabbedPane jtab = new JTabbedPane();
            jtab.add("인사관리", mainHRM.hrm);
            
            mainHRM.add(jtab);
            mainHRM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainHRM.setSize(1300, 1200);
            mainHRM.setVisible(true);
            // 현재 로그인 창을 닫습니다.
            this.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void passwordError() {
        JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
        // 비밀번호 오류 시 실행되는 로직
    }

    private void loginFail() {
        JOptionPane.showMessageDialog(this, "등록되지 않은 아이디입니다.");
        // 등록되지 않은 아이디일 경우 실행되는 로직
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}