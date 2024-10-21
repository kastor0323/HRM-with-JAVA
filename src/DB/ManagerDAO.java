/*
 	클래스 목적 :
 	LoginFrame클래스에서 아이디와 비밀번호를 받아와 DB의 아이디와 비밀번호가 일치하는 지 확인하고 성공, 실패, 아이디가 없는 경우를 출력하는 클래스입니다.
 */

package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
    private static ManagerDAO instance = new ManagerDAO();

    private ManagerDAO() {}

    public static ManagerDAO getInstance() {
        return instance;
    }

    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

    // DB 연결
    public static Connection connect() {
        String databaseUrl = "jdbc:mysql://localhost/employees?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8";
        String databaseUser = "root";
        String databasePassword = "1234"; 

        try {
            con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            if (con != null) {
                System.out.println("Database Connected");
            } else {
                System.out.println("Failed to connect to Database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 아이디로 찾아오기
    public ManagerVO selectById(String id) {
        String sql = "select * from manager where id = ? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            ManagerVO manager = new ManagerVO();
            // id 확인
            if (rs.next()) {
                manager.setM_Id(rs.getString("id"));
                manager.setM_Password(rs.getString("name"));
                manager.setM_Name(rs.getString("name"));
                return manager;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    // 로그인
    // 성공 : 0 실패 : 비밀번호오류 : -1, 등록되지않은 아이디 : -2 | 데이터베이스 오류 : -3;
    public int login(String id, String password) {
        String sql = "select * from manager where id = ? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            // id 확인
            if (rs.next()) {
                System.out.println("id가 확인됨. ");
                String pwd = rs.getString("password"); // 실제 비밀번호

                // 1) 비밀번호가 맞는 경우.
                if (password.equals(pwd)) {
                    System.out.println("로그인 완료");
                    return 0;
                }
                // 2) 비밀번호가 틀린 경우
                else {
                    return -1;
                }
            }
            // 없는 id
            else {
                return -2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -3; // 데이터베이스 오류
    }
}
