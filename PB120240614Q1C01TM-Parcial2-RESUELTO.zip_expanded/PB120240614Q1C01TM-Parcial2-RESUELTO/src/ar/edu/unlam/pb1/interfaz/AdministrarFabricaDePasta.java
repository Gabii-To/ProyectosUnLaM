package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Pasta;
import ar.edu.unlam.pb1.dominio.FabricaDePastas;
import ar.edu.unlam.pb1.dominio.enums.TipoDePasta;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class AdministrarFabricaDePasta {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		String nombrePedido = ingresarString("\nIngrese el nombre del pedido:");
		FabricaDePastas fabricaDePastas = new FabricaDePastas(nombrePedido);
		MenuPrincipal opcionMenu = null;

		do {
			System.out.println(
					"\n\n *****************************************\n PEDIDO " + fabricaDePastas.getNombrePedido());

			opcionMenu = ingresarOpcionDeMenuPrincipalValida();

			switch (opcionMenu) {
			case MOSTRAR_PASTA_DE_UN_TIPO_DE_PASTA_CON_MENOR_CANTIDAD_EN_El_PEDIDO:
				mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(fabricaDePastas);
				break;
			case AGREGAR_PASTA_A_PEDIDO:
				agregarPastaAPedido(fabricaDePastas);
				break;
			case MOSTRAR_PEDIDO_ORDENADO_POR_PRECIO_DE_PASTA_DESCENDENTE:
				mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(fabricaDePastas);
				break;
			case MOSTRAR_TOTAL_DEL_PEDIDO:
				mostrarTotalDelPedido(fabricaDePastas);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta pronto!");
				break;
			}
		} while (opcionMenu != MenuPrincipal.SALIR);

	}

	private static void mostrarTotalDelPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el total del pedido desde la fabrica y luego mostrarlo.
		double total = fabricaDePastas.obtenerTotalDelPedido();
		mostrarPorPantalla("El total es:" + total);
	}

	private static void mostrarPedidoOrdenadoPorPrecioDeOPastaDescendente(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe obtener el pedido de la fabrica y mostrar las pastas en el
		// mismo.
		Pasta[] pedido = fabricaDePastas.obtenerPedidoPorPrecioDePastaDescendente();
		mostrarArrayDePastas(pedido);
	}

	private static void mostrarPastaDeUnTipoDePastaConMenorCantidadEnElPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Se debe mostrar los tipos de pasta disponibles, solicitar la opcion
		// deseada como texto y luego obtener desde la fabrica la pasta del TipoDePasta
		// ingresado con menor cantidad en el pedido. Mostrar la pasta
		// obtenida.
		mostrarTiposDePasta();
		String opcion = ingresarString("\nIngrese el tipo de pasta").toUpperCase();
		TipoDePasta tipoDePasta = TipoDePasta.valueOf(opcion);
		Pasta pasta = fabricaDePastas.obtenerPastaDeUnTipoDePastaConMenorCantidadEnElPedido(tipoDePasta);
		mostrarPorPantalla(pasta.toString());
	}

	private static void agregarPastaAPedido(FabricaDePastas fabricaDePastas) {
		// TODO: Debe mostrar las pastas disponibles en la fabrica, solicitar el ingreso
		// del codigo de la pasta deseada, la cantidad que se quiere agregar al pedido
		// (debe ser mayor a cero) y luego agregarla al pedido de la fabrica. Mostrar un
		// mensaje de exito si fue posible agregar al pedido o de error en caso
		// contrario.
		mostrarArrayDePastas(fabricaDePastas.getPastas());
		String codigoPasta = ingresarString("\nIngrese el codigo de la pasta:");
		int cantidad = 0;

		do {
			cantidad = ingresarEntero("\nIngrese la cantidad de pasta:");
		} while (cantidad <= 0);

		boolean agregada = fabricaDePastas.agregarPastaAlPedido(codigoPasta, cantidad);

		if (agregada) {
			mostrarPorPantalla("Pasta agregada!");
		} else {
			mostrarPorPantalla("Pasta NO agregada.");
		}
	}

	private static MenuPrincipal ingresarOpcionDeMenuPrincipalValida() {
		// TODO: Se debe mostrar el menu principal y solicitar el ingreso de la opcion
		// deseada. Se debe validar que la opcion ingresada sea valida para el menu
		// mostrado y luego devolverla.
		int opcion = 0;

		do {
			mostrarMenuPrincipal();
			opcion = ingresarEntero("\nIngrese la opcion deseada: ");
		} while (opcion < 1 || opcion > MenuPrincipal.values().length);

		return MenuPrincipal.values()[opcion - 1];
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n*****Menu Pedido Pastas On line*****\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + "- " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarTiposDePasta() {
		String tiposDePasta = "\n*****Tipos de pasta*****\n";
		for (int i = 0; i < TipoDePasta.values().length; i++) {
			tiposDePasta += TipoDePasta.values()[i] + "\n";
		}
		mostrarPorPantalla(tiposDePasta);
	}

	private static int ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarArrayDePastas(Pasta[] pastas) {
		for (int i = 0; i < pastas.length; i++) {
			if (pastas[i] != null) {
				mostrarPorPantalla(pastas[i].toString());
			}
		}
	}
}
