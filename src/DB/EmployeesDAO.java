/*
 	
 	클래스 목적 : 
 	GUI패키지의 클래스(LoginFrame 제외)들의 액션 리스너를 받아 mysql문을 실행하거나 HRM_hrm 메인 창에 필요한 검색조건을 만들기 위한 클래스이다.
 */

package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class EmployeesDAO {			// DAO(Data Access Object), EmployeesDAO는 데이터베이스의 데이터에 접근하기 위한 객체
    private EmployeesDAO() {
    }

    private static EmployeesDAO instance = new EmployeesDAO();

    public static EmployeesDAO getInstance() {
        return instance;
    }
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
  //모든 emplpoyees 정보 가져오기
    public Vector<EmployeesVO> getAllEmployees() throws SQLException {
        Vector<EmployeesVO> list = new Vector<>();
        
        String sql = "SELECT * FROM Employees";

        try {
            conn = DBconnect.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	EmployeesVO employees = new EmployeesVO();
            	employees.setId(rs.getString("id"));
            	employees.setStoreName(rs.getString("store"));
            	employees.setName(rs.getString("name"));
            	employees.setDepartmentName(rs.getString("department_name"));
            	employees.setPhonenumber(rs.getString("phonenumber"));
            	employees.setEmail(rs.getString("email"));
            	employees.setAccount(rs.getString("account"));
            	list.add(employees);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBconnect.close();

        }
        return list;
    }

    // Employees중에서 부서이름만 가져와서 목록화
    public Vector<String> getEmployees() throws SQLException {
        Vector<EmployeesVO> dbEmployeeList = getAllEmployees(); // 데이터베이스에서 모든 Employees 정보를 가져오는 메서드
        Set<String> departmentNameSet = new LinkedHashSet<>(); // 중복을 제거할 부서이름을 담을 Set
        departmentNameSet.add("전체");
        for (EmployeesVO employee : dbEmployeeList) {
            departmentNameSet.add(employee.getDepartmentName());
        }

        // Set을 Vector로 변환하여 반환
        return new Vector<>(departmentNameSet);
    }
    
    //사원 정보 검색하기
    public Vector<EmployeesVO> getResult(String txtId, String txtName, String txtStore, Object departmentName) throws SQLException {
    	Vector<EmployeesVO> list = new Vector<>();
    	String selectQuery = "SELECT * FROM Employees";
    	boolean istxtIdIsEmpyt = txtId.isEmpty(); // 사번
    	boolean istxtNameIsEmpty = txtName.isEmpty(); // 이름
    	boolean istxtStoreEmpty = txtStore.isEmpty(); // 
    	boolean isDepartmentNameIsEmpty = departmentName == null;	// 부서
    	boolean andCheck = false;
    	
    	
 
    	if(istxtIdIsEmpyt && istxtNameIsEmpty && istxtStoreEmpty && departmentName == "전체") {
    		selectQuery = "SELECT * FROM Employees";
    	} else if(!(istxtIdIsEmpyt && istxtNameIsEmpty && istxtStoreEmpty && isDepartmentNameIsEmpty)){
    		selectQuery += " WHERE";
			if(!istxtIdIsEmpyt) {
    			selectQuery += " ID="+txtId;
    			andCheck = true;
    		}
    		if(!istxtNameIsEmpty) {
    			if(andCheck) {
    				selectQuery += " AND";
    			}
    			selectQuery += " NAME="+ "'" + txtName + "'";
    			andCheck = true;
    		}
    		if(!istxtStoreEmpty) {
    			if(andCheck) {
    				selectQuery += " AND";
    			}
    			selectQuery += " STORE="+ "'" +txtStore + "'";
    			andCheck = true;
    		}
    		if(departmentName != "전체") {
    			if(!isDepartmentNameIsEmpty) {
        			if(andCheck) {
        				selectQuery += " AND";
        			}
        			selectQuery += " DEPARTMENT_NAME="+ "'" + (String)departmentName + "'";
        			
        			andCheck = true;
        		}
			}
    	}
    		
    	
    	System.out.println(selectQuery);
    	
    	try {
    		conn = DBconnect.connect();
            pstmt = conn.prepareStatement(selectQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                EmployeesVO employees = new EmployeesVO();
                employees.setId(rs.getString("id"));
                employees.setStoreName(rs.getString("store"));
                employees.setName(rs.getString("name"));
                employees.setDepartmentName(rs.getString("department_name"));
                employees.setPhonenumber(rs.getString("phonenumber"));
                employees.setEmail(rs.getString("email"));
                employees.setAccount(rs.getString("account"));
                list.add(employees);
            }
    	} finally {
    		DBconnect.close();
		}

    	return list;
    }
    
    //신규사원 등록 명령어 
    public void createEmployee(String txtId, String txtName, String txtStore, String txtdepartmentName, String txtPhoneNumber,
    		String txtEmail, String txtAccount) throws SQLException {
        String createQuery = "insert into Employees values (?,?,?,?,?,?,?)";
        
        System.out.println(createQuery);
        try {
            conn = DBconnect.connect();
            pstmt = conn.prepareStatement(createQuery);
            pstmt.setString(1, txtId);
            pstmt.setString(2, txtName);
            pstmt.setString(3, txtStore);
            pstmt.setString(4, txtdepartmentName);
            pstmt.setString(5, txtPhoneNumber);
            pstmt.setString(6, txtEmail);
            pstmt.setString(7, txtAccount);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBconnect.close();

        }
    }
    
    //사원 정보 수정
    public void UpdateEmployee(String txtId, String txtStore, String txtName,String txtDepartmentName, String txtPhoneNumber, 
    		String txtEmail, String txtAccount) throws SQLException {
        String updateQuery = "UPDATE Employees SET";
        boolean andCheck = false;

        if (!txtId.isEmpty()) {
        	if (!txtStore.isEmpty()) {
                updateQuery += " STORE='" + txtStore + "'";
                andCheck = true;
            }
            if (!txtName.isEmpty()) {
            	if (andCheck) {
                    updateQuery += ",";
                }
                updateQuery += " NAME='" + txtName + "'";
                andCheck = true;
            }
            
            if (!txtDepartmentName.isEmpty()) {
                if (andCheck) {
                    updateQuery += ",";
                }
                updateQuery += " DEPARTMENT_NAME='" + txtDepartmentName + "'";
                andCheck = true;
            }
            if (!txtPhoneNumber.isEmpty()) {
                if (andCheck) {
                    updateQuery += ",";
                }
                updateQuery += " PhoneNumber='" + txtPhoneNumber + "'";
                andCheck = true;
            }
            if (!txtEmail.isEmpty()) {
                if (andCheck) {
                    updateQuery += ",";
                }
                updateQuery += " Email='" + txtEmail + "'";
                andCheck = true;
            }
            if (!txtAccount.isEmpty()) {
                if (andCheck) {
                    updateQuery += ",";
                }
                updateQuery += " Account='" + txtAccount + "'";
                andCheck = true;
            }

            updateQuery += " WHERE id='" + txtId + "'";

            try (Connection conn = DBconnect.connect();
                 PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.executeUpdate();
                System.out.println("사원 정보가 성공적으로 수정되었습니다!");
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("수정할 사원 번호를 입력하세요!");
        }
    }

    
    //사원 삭제
    
    public void DeleteEmployee(String txtId) throws SQLException {
    	String DeleteQuery = "DELETE FROM Employees where id = (?)" ;
    	System.out.println(DeleteQuery);
    	
    	try { //삭제를 시키는 코드
    		conn = DBconnect.connect();
            pstmt = conn.prepareStatement(DeleteQuery);
            pstmt.setString(1, txtId);
            pstmt.executeUpdate();
    	} catch(Exception e) {
    		System.out.println(e.toString());
    	} finally {
    		DBconnect.close();
		}

    }
    
}