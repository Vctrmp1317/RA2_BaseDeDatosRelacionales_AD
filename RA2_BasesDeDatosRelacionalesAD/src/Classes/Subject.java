package Classes;

public class Subject {
	private int cod,hours;
	private String name,dniTeacher;
	
	public Subject(int cod, int hours, String name, String dniTeacher) {
		super();
		this.cod = cod;
		this.hours = hours;
		this.name = name;
		this.dniTeacher = dniTeacher;
	}

	public Subject() {
		super();
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDniTeacher() {
		return dniTeacher;
	}

	public void setDniTeacher(String dniTeacher) {
		this.dniTeacher = dniTeacher;
	}

	@Override
	public String toString() {
		return "Subject [cod=" + cod + ", hours=" + hours + ", name=" + name + ", dniTeacher=" + dniTeacher + "]";
	}
	
	
}
