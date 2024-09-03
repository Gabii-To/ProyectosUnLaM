package ar.edu.unlam.pb1.interfaz.enums;

public enum MenuPrincipal {
	AGREGAR_COPA_DEL_MUNDO("Agregar copa del mundo"), 
	MOSTRAR_PROMEDIO_DE_PRECIOS_DE_COPAS_DEL_MUNDO("Mostrar promedio de precios de copas del mundo"),
	MOSTRAR_COPAS_DEL_MUNDO_QUE_NO_SON_DE_UN_MATERIAL(
			"Mostrar copas del mundo que NO son de un material"),
	MOSTRAR_COPAS_DEL_MUNDO_ORDENADAS_POR_STOCK_ASCENDENTE("Mostrar copas del mundo ordenadas por stock ascendente"),
	SALIR("Salir");

	private String descripcion;

	MenuPrincipal(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
