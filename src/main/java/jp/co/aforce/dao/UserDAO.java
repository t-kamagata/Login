package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.UserBean;

public class UserDAO extends DAO{
	public UserBean getUser(String id, String password) throws Exception {
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"select * from User where id = ? and password = ?"
			);
		st.setString(1, id);
		st.setString(2, password);
		
		ResultSet rs = st.executeQuery();
		
		UserBean user = null;
		if(rs.next()) {
			user = new UserBean();
			user.setId(rs.getString("id"));
			user.setPassword(rs.getString("password"));
		}
		
		st.close();
		con.close();
		
		return user;
	}
	
	public int createUser(String id, String password) throws Exception {
Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"insert into user values (?, ?)"
			);
		st.setString(1, id);
		st.setString(2, password);
		int line = st.executeUpdate();
		
		st.close();
		con.close();
		
		return line;
	}
}
