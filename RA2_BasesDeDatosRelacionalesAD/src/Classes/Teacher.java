package Classes;

public class Teacher {
	private String dni,name,secondName,email;

	public Teacher(String dni, String name, String secondName, String email) {
		super();
		this.dni = dni;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
	}

	public Teacher() {
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

	@Override
	public String toString() {
		return "Teacher [dni=" + dni + ", name=" + name + ", secondName=" + secondName + ", email=" + email + "]";
	}
	
	
}
