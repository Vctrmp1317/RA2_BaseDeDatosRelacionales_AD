package Classes;

public class Ra {
	private int id,codSubject;
	private String name,description;
	private float percentage;
	
	public Ra(int id, int codSubject, String name, String description, float percentage) {
		super();
		this.id = id;
		this.codSubject = codSubject;
		this.name = name;
		this.description = description;
		this.percentage = percentage;
	}

	public Ra() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(int codSubject) {
		this.codSubject = codSubject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Ra [id=" + id + ", codSubject=" + codSubject + ", name=" + name + ", description=" + description
				+ ", percentage=" + percentage + "]";
	}
	
	
	
	
	
}
