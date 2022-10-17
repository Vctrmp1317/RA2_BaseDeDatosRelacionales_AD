package Sql_FuctionsAndFuctions;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import Classes.Calification;
import Classes.Enrollment;
import Classes.Ra;
import Classes.Student;
import Classes.Subject;
import Classes.Teacher;
import Classes.Users;

import LoginAndRegister.Login;
import Student.Subjects;

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
		else if(o.getClass()==Subject.class) {
			Subject sub=(Subject)o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("INSERT INTO subjects VALUES (?,?,?,?)");
			stmt.setInt(1, sub.getCod());
			stmt.setString(2, sub.getName());
			stmt.setInt(3, sub.getHours());
			stmt.setString(4, sub.getDniTeacher());
			stmt.executeUpdate();
			stmt.close();
		}
		else if(o.getClass()==Ra.class) {
			Ra r=(Ra)o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("INSERT INTO ra VALUES (?,?,?,?,?)");
			stmt.setInt(1, r.getId());
			stmt.setString(2, r.getName());
			stmt.setString(3, r.getDescription());
			stmt.setFloat(4, r.getPercentage());
			stmt.setInt(5, r.getCodSubject());
			stmt.executeUpdate();
			stmt.close();
		}
		else if(o.getClass()==Enrollment.class) {
			Enrollment en=(Enrollment)o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("INSERT INTO enrollment VALUES (?,?)");
			stmt.setString(1, en.getDniStudent());
			stmt.setInt(2, en.getCodSubject());
			stmt.executeUpdate();
			stmt.close();
		}
		else if(o.getClass()==Calification.class) {
			Calification ca=(Calification)o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("INSERT INTO calification VALUES (?,?,?)");
			stmt.setString(1, ca.getDniStudent());
			stmt.setInt(2, ca.getIdRa());
			stmt.setFloat(3, ca.getMark());
		}
		
		
	}
	
	public static void inserUser(Users s) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt=null;
		stmt=getConn().prepareStatement("INSERT INTO users VALUES (?,?,?)");
		stmt.setString(1, s.getDni());
		stmt.setString(2,s.getPassword());
		stmt.setString(3, s.getRol());
		stmt.executeUpdate();
		stmt.close();
	}
	
	public static ResultSet consultDB(String table) throws ClassNotFoundException, SQLException {
		String sql="SELECT * FROM "+table;
		PreparedStatement stmt=getConn().prepareStatement(sql);
		
		ResultSet rs=stmt.executeQuery();
	
		return rs;
	}
	public <T> ResultSet consultDBSpec(String table,String primarykey,T valor) throws ClassNotFoundException, SQLException {
		String sql="SELECT * FROM "+table+" WHERE "+primarykey+" = ?;";
		PreparedStatement stmt=getConn().prepareStatement(sql);
		if(valor.getClass()==String.class) {
		stmt.setString(1, (String) valor);
		}
		if(valor.getClass()==Integer.class) {
			stmt.setInt(1, (int) valor);
		}
		ResultSet rs=stmt.executeQuery();
	
		return rs;
	}
	
	public static void delete(String table,String primaryKey,String valor) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt=null;
		stmt=getConn().prepareStatement("DELETE FROM "+table+" WHERE "+primaryKey+" = ?");
		stmt.setString(1, valor);
		stmt.executeUpdate();
	}
	public static void Update(Object o) throws ClassNotFoundException, SQLException {
		if(o.getClass()==Student.class) {
			
			Student s=(Student) o;
			PreparedStatement stmt=null;
			stmt=getConn().prepareStatement("UPDATE student SET NAME=?,SECOND_NAME=?,EMAIL=?,ROUTE_IMG=?,BIRTHDATE=? WHERE DNI= ?");
			
			stmt.setString(1,s.getName());
			stmt.setString(2, s.getSecondName());
			stmt.setString(3, s.getEmail());
			stmt.setString(4, s.getRouteImg());
			stmt.setDate(5, s.getBirthdate());
			stmt.setString(6, s.getDni());
			stmt.executeUpdate();
			stmt.close();
			}
			else if(o.getClass()==Teacher.class) {
				Teacher t=(Teacher) o;
				PreparedStatement stmt=null;
				stmt=getConn().prepareStatement("UPDATE teachers SET DNI=?,NAME=?,SECOND_NAME=?,EMAIL=?");
				stmt.setString(1, t.getDni());
				stmt.setString(2,t.getName());
				stmt.setString(3, t.getSecondName());
				stmt.setString(4, t.getEmail());
				stmt.executeUpdate();
				stmt.close();
			}
			else if(o.getClass()==Subject.class) {
				Subject sub=(Subject)o;
				PreparedStatement stmt=null;
				stmt=getConn().prepareStatement("UPDATE  subjects SET COD=?,NAME=?,HOURS=?,DNI_TEACHER=?");
				stmt.setInt(1, sub.getCod());
				stmt.setString(2, sub.getName());
				stmt.setInt(3, sub.getHours());
				stmt.setString(4, sub.getDniTeacher());
				stmt.executeUpdate();
				stmt.close();
			}
			else if(o.getClass()==Ra.class) {
				Ra r=(Ra)o;
				PreparedStatement stmt=null;
				stmt=getConn().prepareStatement("UPDATE ra SET ID=?,NAME=?,DESCRIPTION=?,PERCENTAGE=?,COD_SUBJECT=?");
				stmt.setInt(1, r.getId());
				stmt.setString(2, r.getName());
				stmt.setString(3, r.getDescription());
				stmt.setFloat(4, r.getPercentage());
				stmt.setInt(5, r.getCodSubject());
				stmt.executeUpdate();
				stmt.close();
			}
			else if(o.getClass()==Enrollment.class) {
				Enrollment en=(Enrollment)o;
				PreparedStatement stmt=null;
				stmt=getConn().prepareStatement("UPDATE enrollment SET DNI_STUDENT=?,COND_SUBJECT=?");
				stmt.setString(1, en.getDniStudent());
				stmt.setInt(2, en.getCodSubject());
				stmt.executeUpdate();
				stmt.close();
			}
			else if(o.getClass()==Calification.class) {
				Calification ca=(Calification)o;
				PreparedStatement stmt=null;
				stmt=getConn().prepareStatement("UPDATE calification SET DNI_STUDENT=?,ID_RA=?,MARK=?");
				stmt.setString(1, ca.getDniStudent());
				stmt.setInt(2, ca.getIdRa());
				stmt.setFloat(3, ca.getMark());
			}
			
	}
	public float getMark(String dni, int codSubject) throws SQLException, ClassNotFoundException {
		float mark=0;
		
		ResultSet res = null;
		SlqAndFuctions saf=new SlqAndFuctions();
		
		PreparedStatement stmt = saf.getConn()
				.prepareStatement("SELECT * FROM ra WHERE COD_SUBJECT=?");
		
		stmt.setInt(1, codSubject);
		res = stmt.executeQuery();
		while(res.next()) {
		
		ResultSet rs = null;

		stmt = saf.getConn()
				.prepareStatement("SELECT * FROM calification WHERE DNI_STUDENT =? AND ID_RA=?");
		stmt.setString(1, dni);
		stmt.setInt(2, res.getInt("ID"));
		rs = stmt.executeQuery();
		rs.next();
		ResultSet rst=null;
		PreparedStatement stm = saf.getConn()
				.prepareStatement("SELECT * FROM ra WHERE COD_SUBJECT = ? AND ID = ?");
		
		stm.setInt(1, codSubject);
		stm.setInt(2, res.getInt("ID"));
		rst = stm.executeQuery();
		rst.next();
		mark=rs.getFloat("MARK")*rst.getFloat("PERCENTAGE");
		}
		
		return mark;
		
	}
	
	

}
