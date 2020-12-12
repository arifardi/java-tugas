package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;



public class EmployeeModel {
	
	private String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private Integer employeeId;
	private Integer roleId;
	private String employeeName;
	private String username;
	private String DOB;
	private Integer salary;
	private String status;
	private String password;
	private Integer index;
	private ResultSet rs;
	private Integer roletemp;
	private Integer idtemp;
	
	private Connect con;

	public EmployeeModel() {
		// TODO Auto-generated constructor stub
	}


	public EmployeeModel(Integer employeeId, Integer roleId, String employeeName, String username, String dOB,
			Integer salary, String status, String password) {
		this.employeeId = employeeId;
		this.roleId = roleId;
		this.employeeName = employeeName;
		this.username = username;
		DOB = dOB;
		this.salary = salary;
		this.status = status;
		this.password = password;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//Fungsi untuk mengambil semua data employee yang ada dalam database
	public Vector<EmployeeModel> getAllEmployee(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM employee");
		Vector<EmployeeModel> em = new Vector<EmployeeModel>();
		index = 1;
		try {
			while(rs.next()) {
				em.add(new EmployeeModel(rs.getInt("employeeID"),rs.getInt("roleID"),rs.getString("name"),rs.getString("username"), rs.getString("DOB"), rs.getInt("salary"), rs.getString("status"),rs.getString("password")));
				index++;
			}
		} catch (Exception e) {
		}
		return em;
	}
	
	//Fungsi untuk menggenerate password sehabis insertEmployee
	public String generatePassword(String employeeName, String DOB) {
		String password;
		String firstName;
		String year;
	
		int space = 0;
		for (int i = 0; i < employeeName.length() ; i++) {
			if(employeeName.charAt(i) == ' '){
				space = i;
				break;
			}
		}
		
		firstName = employeeName.substring(0,space).toLowerCase();
		year = DOB.substring(0,4);
		password = firstName + year;
		
		return password;
	}
	
	//Fungsi untuk menggenerate password ketika password di reset
	public static String generateResetPassword(Integer count, String ALPHA_NUMERIC_STRING) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	//Fungsi untuk memasukan data employee baru ke dalam database
	public void insertEmployee(String employeeName, String username, String DOB, Integer salary, Integer roleId) {
		con = Connect.getConnection(); //koneksi ke database
		getAllEmployee(); //untuk menghitung index (id)
		PreparedStatement ps = con.prepareStatement("INSERT INTO employee VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
		try {
			ps.setInt(1, index);
			ps.setInt(2,roleId);
			ps.setString(3, employeeName);
			ps.setString(4, username);
			ps.setString(5, DOB);
			ps.setInt(6, salary);
			ps.setString(7, "Hired");
			ps.setString(8, generatePassword(employeeName,DOB));
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	//Fungsi untuk memecat employee
	public void fireEmployee(Integer employeeId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE employee SET status = ? WHERE employeeID = ?");
		try {
			ps.setString(1, "Fired");
			ps.setInt(2, employeeId);
			ps.execute(); //buat jalanin query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mereset password
	public void resetPassword(Integer employeeId) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE employee SET password = ? WHERE employeeID = ?");
		try {
			ps.setString(1, generateResetPassword(10,ALPHA_NUMERIC_STRING));
			ps.setInt(2, employeeId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mengupdate employee dalam database
	public void updateEmployee(Integer employeeId, String employeeName, String username, String DOB, Integer salary) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE employee SET name = ?, username = ? , DOB = ? , salary = ? WHERE employeeID = ?");
		try {
			ps.setString(1, employeeName);
			ps.setString(2, username);
			ps.setString(3, DOB);
			ps.setInt(4, salary);
			ps.setInt(5, employeeId);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fungsi untuk mendapatkan roleID berdasar username && password
	public Integer getRoleID(String username, String password) {
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT roleID FROM employee WHERE username = ? AND password = ?");
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				roletemp = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roletemp;
	}
	
	//Fungsi untuk mendapatkan employeeID berdasar username && password
	public Integer getEmployee(String username, String password){
		con = Connect.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT employeeID FROM employee WHERE username = ? AND password = ?");
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				idtemp = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idtemp;
	}
}
