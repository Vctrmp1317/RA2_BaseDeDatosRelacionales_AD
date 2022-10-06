package Classes;

public class Users {
	private String dni,password,rol;

	public Users(String dni, String password, String rol) {
		super();
		this.dni = dni;
		this.password = password;
		this.rol = rol;
	}

	public Users() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Users [dni=" + dni + ", password=" + password + ", rol=" + rol + "]";
	}
	
	
}
