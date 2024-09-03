package enums;

public enum SubMenu {
	
	AGREGAR_SOSPECHA("Agregar sospecha"), 
	MODIFICAR_SOSPECHA("Modificar sospecha"),
	ELIMINAR_SOSPECHA("Eliminar sospecha"), 
	MOSTRAR_SOSPECHAS_ORDENADAS_POR_SOSPECHOSO("Mostrar sospechas ordenadas por sospechoso"), 
	BUSCAR_SOSPECHA_POR_ID("Buscar sospecha por ID"), 
	SENIALAR_CULPABLE("Señalar culpable"),
	VOLVER("Volver");

	private String descripcion;
	
	SubMenu (String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
