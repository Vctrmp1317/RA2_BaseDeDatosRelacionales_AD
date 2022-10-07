package Sql_FuctionsAndFuctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Student;
import Classes.Teacher;
import Classes.Users;

public class SlqAndFuctions {
	
	static Connection conn = null;
	
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		
		
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ra2_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		
		return conn;
	}
	
	public static void insert(Object o) throws ClassNotFoundException, SQLException {
		if(o.getClass()==Student.class) {
			
		Student s=(Student) o;
		PreparedStatement stmt=null;
		stmt=getConn().prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
		stmt.setString(1, s.getDni());
		stmt.setString(2,s.getName());
		stmt.setString(3, s.getSecondName());
		stmt.setString(4, s.getEmail());
		stmt.setString(5, s.getRouteImg());
		stmt.setDate(6, s.getBirthdate());
		stmt.executeUpdate();
		stmt.close();
		}
		else if(o.getClass()==Teacher.class) {
			Teacher t=(Teacher) o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("INSERT INTO teachers VALUES (?,?,?,?)");
			stmt.setString(1, t.getDni());
			stmt.setString(2,t.getName());
			stmt.setString(3, t.getSecondName());
			stmt.setString(4, t.getEmail());
			stmt.executeUpdate();
			stmt.close();
		}
		
		
	}
	
	public static void inserUser(Users s) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt=null;
		stmt=getConn().prepareStatement("INSERT INTO users VALUES (?,?,?)");
		stmt.setString(1, s.getDni());
		stmt.setString(2,s.getPassword());
		stmt.setString(3, s.getRol());
		stmt.close();
	}
	
	public static ResultSet consultDB(String table,String dataConsult) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt=getConn().prepareStatement("SELECT * FROM "+table+" WHERE"+dataConsult+" = ?");
		ResultSet rs=stmt.executeQuery();
		return rs;
	}

}
