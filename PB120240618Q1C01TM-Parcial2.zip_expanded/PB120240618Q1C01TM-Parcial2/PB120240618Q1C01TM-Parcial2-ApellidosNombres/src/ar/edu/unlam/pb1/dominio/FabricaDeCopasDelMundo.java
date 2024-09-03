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

	public String getNombre() {
		return nombre;
	}

	public CopaDelMundo[] getCopasDelMundo() {
		return copasDelMundo;
	}

	private static int obtenerProximoId() {
		return ++proximoId;
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

		boolean actualizado = false;
		CopaDelMundo copaBuscada = buscarCopaDelMundoPorMaterial(copaDelMundo.getMaterial());
		if (copaBuscada == null) {
			copasDelMundo[proximoId] = copaDelMundo;
			copasDelMundo[proximoId].setId(obtenerProximoId());
		}
		else {
			actualizarStockDeCopaDelMundo(copaBuscada,copaDelMundo.getStock());
			actualizado = true;
		}
			
		return actualizado;
	}

	private CopaDelMundo buscarCopaDelMundoPorMaterial(Materiales material) {
		// TODO: Se debe buscar una copa del mundo por material en el array de copas del
		// mundo y devolverla. En caso de no poseer una copa del mundo que cumpla con el
		// material, se debe devovler null.
		
		CopaDelMundo copaDelMundo = null;
		for(int i=0;i<copasDelMundo.length;i++) {
			if(copasDelMundo[i] != null)
				if(copasDelMundo[i].getMaterial() == material)
					copaDelMundo = copasDelMundo[i];
		}
		
		return copaDelMundo;
	}
	
	private void actualizarStockDeCopaDelMundo(CopaDelMundo copaDelMundo, int stockAAgregar) {
		// TODO: agregar el stock que llega como parametro, al stock existente de la
		// copa del mundo que llega como parametro.
		
		copaDelMundo.setStock(copaDelMundo.getStock() + stockAAgregar);
	}

	public double obtenerPromedioDePreciosDeCopasDelMundo() {
		// TODO: Se debe calcular y devolver el promedio de precios de todas las copas
		// del mundo sin considerar el stock de cada una.
		
		double promedio = -1, suma = 0;
		int divisor = 0;
		for(int i=0;i<copasDelMundo.length;i++) {
			if(copasDelMundo[i] != null) {
				suma += copasDelMundo[i].getPrecio();
				divisor++;
			}
		}
		if(divisor != 0)
			promedio = suma / divisor;
		return promedio;
	}

	public CopaDelMundo[] obtenerCopasDelMundoQueNoSonDeUnMaterial(Materiales material) {
		// TODO: Se debe generar y devolver un nuevo array de copas del mundo que contenga las
		// copas del mundo que NO apliquen al material que llega como parametro.
		
		CopaDelMundo[] nuevoArray = new CopaDelMundo[Materiales.values().length-1];
		int i, j=0;
		for(i=0;i<copasDelMundo.length;i++) {
			if(copasDelMundo[i] != null && !(copasDelMundo[i].getMaterial().equals(material))) {
				nuevoArray[j] = copasDelMundo[i];
				j++;
			}
		}
		return nuevoArray;
	}

	public CopaDelMundo[] obtenerCopasDelMundoOrdenadasPorStockAscendente() {
		// TODO: Se debe ordenar y devolver el array de copas del mundo ordenandolas por
		// stock ascendente.
		
		CopaDelMundo temp = null;
		for(int i=1;i<this.copasDelMundo.length;i++) {
			for(int j=0;j<this.copasDelMundo.length-1;j++) {
				if(this.copasDelMundo[j] != null && this.copasDelMundo[j+1] != null && this.copasDelMundo[j].getStock()>this.copasDelMundo[j+1].getStock()) {
					temp = this.copasDelMundo[j];
					this.copasDelMundo[j] = this.copasDelMundo[j+1];
					this.copasDelMundo[j+1] = temp;
				}
			}
		}
		return this.copasDelMundo;
	}

}
