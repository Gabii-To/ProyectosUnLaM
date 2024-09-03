package ar.edu.unlam.pb1.dominio;

public class Tienda {

	private Vendible[] vendibles;
	private Venta[] ventas;

	public Tienda(String codigo, String razonSocial) {
		this.inicializarTienda(); // Esta linea debe ejecutarse en el constructor
	}

	public String getRazonSocial() {
		return null;
	}

	public Vendible[] buscarVendiblesCuyoCodigoIniciaConTexto(String textoABuscar) {
		// TODO: Se debera devolver un array de vendibles cuyo codigo comienza con el
		// texto indicado indicado para buscar.
		return null;
	}

	public Vendible obtenerVendiblePorCodigo(String codigo) {
		// TODO: Se debera encontrar y devolver un vendible en el array de vendibles que
		// cumpla con el codigo suministrado. En caso de no encontrarlo, se debera
		// devolver null.
		return null;
	}

	public boolean crearVentaDeProductosOServicios(String cliente, String vendedor, Vendible[] vendiblesParaVenta) {
		// TODO: Se debera instanciar una venta utilizando los parametros y luego
		// agregar la misma al array de ventas.
		// El metodo debe devolver verdadero en caso de completar la operacion o falso
		// en caso de no lograrlo por alguna razon.
		return false;
	}

	public int obtenerCantidadDeServiciosEnVentas() {
		// TODO: Se debera devolver la cantidad de servicios presentes en todas las
		// ventas.
		// Cada venta contiene los vendibles (PRODUCTO o SERVICIO) que se incluyeron en
		// la misma. De estos, nos interesa saber cuantos vendibles son de tipo
		// "SERVICIO". Es
		// importante mirar los vendibles en todas las ventas de la tienda.
		return 0;
	}

	public Vendible[] obtenerProductosConStockMaximoOrdenadosPorPrecioDescendente() {
		// TODO: Armar y devolver un array de vendibles de tipo producto, cuyo stock sea
		// el maximo admitido por la tienda (Revisar la constante). El array a devolver
		// debera estar ordenado por precio de manera descendente (ver
		// ordenarVendiblesPorPrecioPrescendente()).
		return null;
	}

	private void ordenarVendiblesPorPrecioPrescendente(Vendible[] vendibles) {
		// TODO: Ordenar el array de vendibles suministrado por precio del vendible, de
		// manera descendente.
	}

	private void inicializarTienda() {
		// TODO: Se deberan generar los vendibles que estaran disponibles para que la
		// tienda pueda operar.
		// Es necesario asignar cada posicion del array de vendibles con una instancia
		// de vendible. En las posiciones pares, se debera colocar un vendible de tipo
		// PRODUCTO, mientras que en las posiciones impares, se debera colocar un
		// vendible de tipo SERVICIO.
		// El codigo de los vendibles debe ser la letra 'P' seguido de un numero para
		// los productos. Para los servicios, el codigo del vendible debe ser una letra
		// 'S' seguido de un numero.
		// El precio de los productos se debera generar considerando que no sean todos
		// iguales (nos sirve para ordenar). El precio de los servicios puede ser fijo.
		// Los servicios no tienen cantidad maxima, en este atributo se debera ingresar un cero.
		// Ejemplos: codigos de productos: "P0", "P1", etc.
		// Ejemplos: codigos de servicios: "S0", "S1", etc.
	}

}
