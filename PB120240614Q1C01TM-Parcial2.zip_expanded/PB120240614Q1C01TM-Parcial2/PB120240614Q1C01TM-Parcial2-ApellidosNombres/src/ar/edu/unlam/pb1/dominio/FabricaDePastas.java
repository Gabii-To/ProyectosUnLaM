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

	public Pasta[] getPastas() {
		return pastas;
	}

	public String getNombrePedido() {
		return nombrePedido;
	}


	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}


	public boolean agregarPastaAlPedido(String codigoPasta, int cantidad) {                             //HECHO
		// TODO: Se debe buscar la pasta por su codigo y agregarla al pedido solo si hay
		// cantidad suficiente de la pasta solicitada. Luego de agregarla, se debe
		// actualizar la cantidad de la pasta agregada en el array de pastas (considerar
		// el
		// metodo descontarCantidadDePastaDisponible()). Devuelve verdadero en caso de
		// completar la
		// operacion o falso en caso de no poder realizar la operacion por el motivo que
		// sea.
		boolean pastaAgregada = false;
		Pasta pasta = obtenerPastaPorCodigo(codigoPasta);
		int posicion = 0;
		if(pasta != null && cantidad<=pasta.getCantidad()) {
			while (posicion<pedido.length && !pastaAgregada) {
				if(pedido[posicion] == null) {
					pedido [posicion] = agregarPasta(pasta.getCodigo(), pasta.getTipoDePasta(), pasta.getEsRellena(), pasta.getPrecio(), cantidad);
					descontarCantidadDePastaDisponible(codigoPasta, cantidad);
					pastaAgregada = true;
				}
				posicion++;
			}
		}
		
		return pastaAgregada;
	}

	private Pasta obtenerPastaPorCodigo(String codigoPasta) {                             //HECHO
		// TODO: Se debe buscar en el array de pastas y devolver la pasta que coincida
		// con el codigoPasta
		// suministrado, o null en caso de que no exista una pasta con ese codigoPasta.
		Pasta pasta = null;
		boolean encontrada = false;
		int posicion = 0;
		while(posicion<pastas.length && !encontrada) {
			if(pastas[posicion].getCodigo().equals(codigoPasta)) {
				pasta = pastas[posicion];
				encontrada = true;
			}
			posicion++;
		}
		return pasta;
	}

	private void descontarCantidadDePastaDisponible(String codigoPasta, int cantidadADescontar) {
		// TODO: Actualiza la cantidad de pasta en el array de pastas.
		
		int cantidadPastas = this.pastas[Integer.parseInt(codigoPasta)/100 -1].getCantidad();
		this.pastas[Integer.parseInt(codigoPasta)/100 -1].setCantidad(cantidadPastas-cantidadADescontar);
	}

	public double obtenerTotalDelPedido() {                                                 //HECHO
		// TODO: Calcula y devuelve el total del pedido considerando el precio de la
		// pasta y la cantidad solicitada en el pedido.
		double totalPedido = 0;
		for (int i=0;i<pedido.length;i++) {
			if(pedido[i] != null) {
				totalPedido += pedido[i].getPrecio() * pedido[i].getCantidad();
			}
		}
		return totalPedido;
	}

	public Pasta obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(TipoDePasta tipoDePasta) {
		// TODO: Devuelve la pasta que aplique al tipoDePasta indicado, que posea menor
		// cantidad en el pedido.
		int posicion = 0;
		Pasta menor = null;
		while(pedido[posicion] != null && posicion<pedido.length) {
			
			if(menor == null || (tipoDePasta.equals(pedido[posicion].getTipoDePasta()) && pedido[posicion].getCantidad() < menor.getCantidad()))
				menor = pedido[posicion];
			posicion++;
		}
		return menor;
	}

	public Pasta[] obtenerPedidoPorPrecioDePastaDescendente() {
		// TODO: Debe ordenar y devolver las pastas en el pedido de manera descendente,
		// debiendo quedar en la primera posicion la pasta de mayor precio.
		
		Pasta temp;
		for(int i=1;i<pedido.length;i++) {
			for(int j=0;j<pedido.length-1;j++) {
				if(pedido[j] != null && pedido[j+1] != null && pedido[j].getPrecio()<pedido[j+1].getPrecio()) {
					temp = pedido[j];
					pedido[j] = pedido[j+1];
					pedido[j+1] = temp;
				}
			}
		}
		return pedido;
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
