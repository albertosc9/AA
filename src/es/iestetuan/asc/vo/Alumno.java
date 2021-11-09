package es.iestetuan.asc.vo;

public class Alumno extends Persona {

	private Long nia;


	public Alumno() {
		
	}

	
	

	public Alumno(Long nia) {
		this.nia=nia;
	}

	public Long getNia() {
		return nia;
	}

	public void setNia(Long nia) {
		this.nia = nia;
	}
	
}
