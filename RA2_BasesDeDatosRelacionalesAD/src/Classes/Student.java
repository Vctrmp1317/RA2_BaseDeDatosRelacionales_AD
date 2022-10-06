package Classes;

import java.sql.Date;
;

public class Student {
	private String dni,name,secondName,email,routeImg;
	private Date birthdate;
	
	public Student(String dni, String name, String secondName, String email, String routeImg, Date birthdate) {
		super();
		this.dni = dni;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.routeImg = routeImg;
		this.birthdate = birthdate;
	}

	public Student() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRouteImg() {
		return routeImg;
	}

	public void setRouteImg(String routeImg) {
		this.routeImg = routeImg;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Student [dni=" + dni + ", name=" + name + ", secondName=" + secondName + ", email=" + email
				+ ", routeImg=" + routeImg + ", birthdate=" + birthdate + "]";
	}
	
	
	
	
	
}
