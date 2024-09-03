package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;

public class FabricaDePastas {

	private String nombrePedido;
	private Pasta[] pastas;
	private Pasta[] pedido;

	public FabricaDePastas(String nombrePedido) {
		// TODO: Completar el constructor para que el producto funcione correctamente.
		this.nombrePedido = nombrePedido;
		this.pastas = new Pasta[TipoDePasta.values().length];
		this.inicializarFabricaDePastas();
		this.pedido = new Pasta[100];
	}

	// TODO: Completar los getters y setters que considere necesarios.

	public String getNombrePedido() {
		return nombrePedido;
	}

	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}

	public Pasta[] getPastas() {
		return this.pastas;
	}

	public boolean agregarPastaAlPedido(String codigoPasta, int cantidad) {
		// TODO: Se debe buscar la pasta por su codigo y agregarla al pedido solo si hay
		// stock suficiente de la pasta solicitada. Luego de agregarla, se debe
		// actualizar la cantidad de la pasta agregada en el array de pastas (considerar
		// el
		// metodo descontarCantidadDePastaDisponible()). Devuelve verdadero en caso de completar la
		// operacion o falso en caso de no poder realizar la operacion por el motivo que
		// sea.
		Pasta pasta = this.obtenerPastaPorCodigo(codigoPasta);
		boolean agregado = false;

		if (pasta != null && pasta.getCantidad() >= cantidad) {

			int posicion = 0;

			while (posicion < this.pedido.length && !agregado) {

				if (this.pedido[posicion] == null) {
					this.pedido[posicion] = this.agregarPasta(pasta.getCodigo(), pasta.getTipoDePasta(), pasta.isEsRellena(),
							pasta.getPrecio(), cantidad);
					agregado = true;
					this.descontarCantidadDePastaDisponible(codigoPasta, cantidad);
				}
				posicion++;
			}
		}

		return agregado;
	}

	private Pasta obtenerPastaPorCodigo(String codigoPasta) {
		// TODO: Se debe buscar en el array de pastas y devolver la pasta que coincida
		// con el codigoPasta
		// suministrado, o null en caso de que no exista una pasta con ese codigoPasta.
		Pasta pasta = null;

		int posicion = 0;
		boolean encontrada = false;

		while (posicion < this.pastas.length && !encontrada) {

			if (this.pastas[posicion] != null && this.pastas[posicion].getCodigo().equals(codigoPasta)) {
				pasta = this.pastas[posicion];
				encontrada = true;
			}
			posicion++;
		}

		return pasta;
	}

	private void descontarCantidadDePastaDisponible(String codigoPasta, int cantidadADescontar) {
		// TODO: Actualiza la cantidad de pasta en el array de pastas.
		Pasta pasta = this.obtenerPastaPorCodigo(codigoPasta);
		int stockActual = pasta.getCantidad();
		pasta.setCantidad(stockActual - cantidadADescontar);
	}

	public double obtenerTotalDelPedido() {
		// TODO: Calcula y devuelve el total del pedido considerando el precio de la
		// pasta y la cantidad solicitada en el pedido.
		double totalPedidoAAbonar = 0.0;
		for (int i = 0; i < this.pedido.length; i++) {

			if (this.pedido[i] != null) {
				totalPedidoAAbonar += this.pedido[i].getPrecio() * this.pedido[i].getCantidad();
			}
		}
		return totalPedidoAAbonar;
	}

	public Pasta obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(TipoDePasta tipoDePasta) {
		// TODO: Devuelve la pasta que aplique al tipoDePasta indicado, que posea menor
		// cantidad en el pedido.
		Pasta pasta = null;

		for (int i = 0; i < this.pedido.length; i++) {

			if (this.pedido[i] != null && this.pedido[i].getTipoDePasta().equals(tipoDePasta)) {

				if (pasta == null || this.pedido[i].getCantidad() < pasta.getCantidad()) {

					pasta = this.pedido[i];
				}
			}
		}

		return pasta;
	}

	public Pasta[] obtenerPedidoPorPrecioDePastaDescendente() {
		// TODO: Ordena las pastas en el pedido de manera descendente, debiendo quedar
		// en la primera posicion la pasta de mayor precio.
		Pasta pastaAuxiliar = null;

		for (int i = 1; i < this.pedido.length; i++) {
			for (int j = 0; j < this.pedido.length - 1; j++) {
				if (this.pedido[j] != null && this.pedido[j + 1] != null
						&& this.pedido[j].getPrecio() < this.pedido[j + 1].getPrecio()) {
					pastaAuxiliar = this.pedido[j];
					this.pedido[j] = this.pedido[j + 1];
					this.pedido[j + 1] = pastaAuxiliar;
				}
			}
		}
		
		return this.pedido;
	}

	private Pasta agregarPasta(String codigo, TipoDePasta tipoDePasta, boolean esRellena, double precio,
			int cantidadEnStock) {
		return new Pasta(codigo, tipoDePasta, esRellena, precio, cantidadEnStock);
	}

	private void inicializarFabricaDePastas() {
		int codigo = 100;
		for (int i = 0; i < TipoDePasta.values().length; i++) {

			this.pastas[i] = this.agregarPasta("" + codigo, TipoDePasta.values()[i], i != 0, ((i * 7) + 115.5),
					(i * 7 + 15));
			codigo += 100;
		}

	}
}
