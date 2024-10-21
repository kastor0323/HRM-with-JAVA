/*
 	클래스 목적 : 프로그램의 이름과 jtab에 사원정보를 검색, 삭제, 생성, 변경을 할 수 있도록 만든 클래스이다.
 */

package GUI;

import javax.swing.*;
import java.sql.SQLException;

public class MainHRM extends JFrame {
    public HRM_hrm hrm = null;

    public static void main(String[] args) throws SQLException {
        MainHRM mainHRM = new MainHRM();        // 메인 윈도우 창 객체 생성
        mainHRM.setTitle("HRM 프로그램");            // 현재 메인 윈도우 창의 타이틀 설정

        mainHRM.hrm = new HRM_hrm();            // HRM 멤버변수에 JPanel 객체 생성 후 할당

        JTabbedPane jtab = new JTabbedPane();

        // 만들어 놓은 JPanel들을 각각 jtab 그룹홀더에 추가
        jtab.add("인사관리", mainHRM.hrm);

        mainHRM.add(jtab);
        mainHRM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainHRM.setSize(1300, 1000);
        mainHRM.setVisible(true);
    }
}
