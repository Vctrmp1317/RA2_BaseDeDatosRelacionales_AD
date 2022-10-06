package Classes;

public class Enrollment {
	private String dniStudent;
	private int codSubject;
	
	public Enrollment(String dniStudent, int codSubject) {
		super();
		this.dniStudent = dniStudent;
		this.codSubject = codSubject;
	}

	public Enrollment() {
		super();
	}

	public String getDniStudent() {
		return dniStudent;
	}

	public void setDniStudent(String dniStudent) {
		this.dniStudent = dniStudent;
	}

	public int getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(int codSubject) {
		this.codSubject = codSubject;
	}

	@Override
	public String toString() {
		return "Enrollment [dniStudent=" + dniStudent + ", codSubject=" + codSubject + "]";
	}
	
	
	
}
