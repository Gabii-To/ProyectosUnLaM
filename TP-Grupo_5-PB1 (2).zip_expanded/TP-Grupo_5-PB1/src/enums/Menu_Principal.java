package enums;

public enum Menu_Principal {
	REGLAS("Reglas"),
	EMPEZAR_A_JUGAR("Empezar a Jugar"), 
	SALIR("Salir");

	private String descripcion;
	
	
	Menu_Principal(String descripcion) {
		// TODO Auto-generated constructor stub
		this.descripcion = descripcion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
