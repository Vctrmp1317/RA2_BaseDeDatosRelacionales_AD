package Classes;

public class Calification {
	private String dniStudent;
	private int idRa;
	private float mark;
	
	public Calification(String dniStudent, int idRa, float mark) {
		super();
		this.dniStudent = dniStudent;
		this.idRa = idRa;
		this.mark = mark;
	}

	public Calification() {
		super();
	}

	public String getDniStudent() {
		return dniStudent;
	}

	public void setDniStudent(String dniStudent) {
		this.dniStudent = dniStudent;
	}

	public int getIdRa() {
		return idRa;
	}

	public void setIdRa(int idRa) {
		this.idRa = idRa;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Calification [dniStudent=" + dniStudent + ", idRa=" + idRa + ", mark=" + mark + "]";
	}
	
	
	
}
