package model;

import java.sql.ResultSet;
import java.util.Vector;

import connect.Connect;

public class RoleModel {
	
	private Integer roleId;
	private String roleName;
	private Connect con;
	
	
	public RoleModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RoleModel(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}


	public Integer getRoleId() {
		return roleId;
	}



	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//Fungsi untuk mengambil semua role yang ada dalam roleDatabase
	public Vector<RoleModel> getAllRole(){
		con = Connect.getConnection();
		ResultSet rs = con.executeQuery("SELECT * FROM role");
		Vector<RoleModel> rm = new Vector<RoleModel>();
		try {
			while(rs.next()) {
				rm.add(new RoleModel(rs.getInt("roleID"), rs.getString("name")));
			}
		} catch (Exception e) {
		}
		return rm;
	}






	

}
