package ar.edu.unlam.pb1.interfaz.enums;

public enum MenuPrincipal {
	MOSTRAR_PACIENTES_QUE_EN_SU_DNI_INCLUYAN("Obtener pacientes que en su DNI incluyan (Ej: 333)."),
	GENERAR_ATENCION("Generar una atencion para un paciente."),
	DIAGNOSTICAR_ATENCION("Diagnosticar una atencion."),
	MOSTRAR_PACIENTE_POR_SU_DNI("Mostrar datos del paciente buscandolo por su DNI."),
	MOSTRAR_ATENCIONES_CON_VALOR_ENTRE_LIMITES("Mostrar atenciones cuyo valor se encuentra entre limites."),
	MOSTRAR_VALOR_TOTAL_DE_ATENCIONES_DIAGNOSTICADAS("Mostrar el valor total de las atenciones que fueron diagnosticadas."), 
	SALIR("Salir");
	
	private String descripcion;

	MenuPrincipal(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
