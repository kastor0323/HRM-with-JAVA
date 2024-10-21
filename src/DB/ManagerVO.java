/*
 	클래스 목적 :
 	ManagerDAO에 연결하기 위해 GUI에서 사용할 함수들을 미리 생성해 놓은 클래스이다.
 */

package DB;

public class ManagerVO {
	private String M_id;
	private String M_password;
    private String M_name;
    private String M_departmentName;
    
	public String getM_Id() {
		return M_id;
	}
	public void setM_Id(String id) {
		this.M_id = id;
	}
	public String getM_Password() {
		return M_password;
	}
	public void setM_Password(String password) {
		this.M_password = password;
	}
	public String getM_Name() {
		return M_name;
	}
	public void setM_Name(String name) {
		this.M_name = name;
	}
	public String getM_DepartmentName() {
		return M_departmentName;
	}
}
