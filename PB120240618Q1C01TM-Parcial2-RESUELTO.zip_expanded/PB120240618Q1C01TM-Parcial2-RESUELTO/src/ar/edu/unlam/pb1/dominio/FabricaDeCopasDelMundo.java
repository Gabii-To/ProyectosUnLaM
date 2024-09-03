package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.Materiales;

public class FabricaDeCopasDelMundo {

	public static final int MAXIMA_CAPACIDAD_DE_PRODUCCION_DE_COPAS_DEL_MUNDO = 5;

	private static int proximoId = 0;
	private String nombre;
	private CopaDelMundo[] copasDelMundo;

	public FabricaDeCopasDelMundo(String nombreDeFabrica) {
		// TODO: Completar el constructor para que el producto funcione correctamente.
		this.nombre = nombreDeFabrica;
		this.copasDelMundo = new CopaDelMundo[Materiales.values().length];
	}

	private static int obtenerProximoId() {
		return ++proximoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
		// TODO: Se debe buscar en el array de copas del mundo por material para saber
		// si ya contamos con copas del mundo de dicho material en stock (considerar el
		// metodo buscarCopaDelMundoPorMaterial()). Si NO tenemos copas del mundo de
		// dicho material en stock, se debe agregar la copa del mundo
		// al array de copas del mundo asignando un nuevo id (considerar el metodo
		// obtenerProximoId()).
		// Si EXISTE una copa del mundo que cumpla con el material, se debe actualizar
		// el stock de la copa del mundo. En caso de actualizar el stock, se debe
		// devolver verdadero.

		CopaDelMundo copaDelMundoBuscada = this.buscarCopaDelMundoPorMaterial(copaDelMundo.getMaterial());

		boolean agregada = false;

		if (copaDelMundoBuscada == null) {

			copaDelMundo.setId(obtenerProximoId());

			int posicion = 0;

			while (posicion < this.copasDelMundo.length && !agregada) {

				if (this.copasDelMundo[posicion] == null) {
					this.copasDelMundo[posicion] = copaDelMundo;
					agregada = true;
				}
				posicion++;
			}

		} 
		else {
			this.actualizarStockDeCopaDelMundo(copaDelMundoBuscada, copaDelMundo.getStock());
			agregada = true;
		}

		return agregada;
	}

	private void actualizarStockDeCopaDelMundo(CopaDelMundo copaDelMundo, int stockAAgregar) {
		// TODO: agregar el stock que llega como parametro, al stock existente de la
		// copa del mundo que llega como parametro.
		copaDelMundo.setStock(copaDelMundo.getStock() + stockAAgregar);
	}

	private CopaDelMundo buscarCopaDelMundoPorMaterial(Materiales material) {
		// TODO: Se debe buscar una copa del mundo por material en el array de copas del
		// mundo y devolverla. En caso de no poseer una copa del mundo que cumpla con el
		// material, se debe devovler null.
		int posicion = 0;
		CopaDelMundo copaDelMundoBuscada = null;

		while (posicion < this.copasDelMundo.length && copaDelMundoBuscada == null) {

			if (this.copasDelMundo[posicion] != null && this.copasDelMundo[posicion].getMaterial().equals(material)) {
				copaDelMundoBuscada = this.copasDelMundo[posicion];
			}
			posicion++;
		}

		return copaDelMundoBuscada;
	}

	public double obtenerPromedioDePreciosDeCopasDelMundo() {
		// TODO: Se debe calcular y devolver el promedio de precios de todas las copas
		// del mundo sin considerar el stock de cada una.
		int cantidadCopasDelMundo = 0;
		double acumuladorDePrecios = 0;
		double promedio = 0;

		for (int i = 0; i < this.copasDelMundo.length; i++) {

			if (this.copasDelMundo[i] != null) {
				cantidadCopasDelMundo++;
				acumuladorDePrecios += this.copasDelMundo[i].getPrecio();
			}

		}

		promedio = acumuladorDePrecios / cantidadCopasDelMundo;

		return promedio;
	}

	public CopaDelMundo[] obtenerCopasDelMundoQueNoSonDeUnMaterial(Materiales material) {
		// TODO: Se debe generar y devolver un nuevo array de copas del mundo que contenga las
		// copas del mundo que NO apliquen al material que llega como parametro.
		int posicion = 0;
		CopaDelMundo[] copasDelMundoFiltradas = new CopaDelMundo[this.copasDelMundo.length];

		for (int i = 0; i < this.copasDelMundo.length; i++) {

			if (this.copasDelMundo[i] != null && !this.copasDelMundo[i].getMaterial().equals(material)) {
				copasDelMundoFiltradas[posicion++] = this.copasDelMundo[i];
			}

		}

		return copasDelMundoFiltradas;

	}

	public CopaDelMundo[] obtenerCopasDelMundoOrdenadasPorStockAscendente() {
		// TODO: Se debe ordenar y devolver el array de copas del mundo ordenandolas por
		// stock ascendente.
		CopaDelMundo auxiliar = null;

		for (int i = 1; i < this.copasDelMundo.length; i++) {

			for (int j = 0; j < this.copasDelMundo.length - 1; j++) {

				if (this.copasDelMundo[j] != null && this.copasDelMundo[j + 1] != null) {

					if (this.copasDelMundo[j].getStock() > this.copasDelMundo[j + 1].getStock()) {
						auxiliar = this.copasDelMundo[j];
						this.copasDelMundo[j] = this.copasDelMundo[j + 1];
						this.copasDelMundo[j + 1] = auxiliar;
					}
				}
			}
		}
		return this.copasDelMundo;
	}

}
