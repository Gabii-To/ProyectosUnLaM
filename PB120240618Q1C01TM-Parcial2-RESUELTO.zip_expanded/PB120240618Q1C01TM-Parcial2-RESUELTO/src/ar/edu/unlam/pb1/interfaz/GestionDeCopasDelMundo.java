package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.CopaDelMundo;
import ar.edu.unlam.pb1.dominio.FabricaDeCopasDelMundo;
import ar.edu.unlam.pb1.dominio.enums.Materiales;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class GestionDeCopasDelMundo {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		MenuPrincipal opcionMenu = null;
		// TODO: Completar lo necesario para mostrar el nombre de la fabrica y el menu
		// principal para que el usuario pueda utilizar el sistema.
		// Considerar los metodos propuestos para cada opcion del menu.
		String nombre = ingresarString("\nIngrese el nombre de la fabrica:");
		FabricaDeCopasDelMundo fabricaDeCopasDelMundo = new FabricaDeCopasDelMundo(nombre);

		do {
			mostrarPorPantalla(
					"\n\n *****************************************\n Fabrica de copas del mundo: " + nombre);

			opcionMenu = ingresarOpcionDeMenuPrincipal();

			switch (opcionMenu) {
			case AGREGAR_COPA_DEL_MUNDO:
				agregarCopaDelMundo(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_PROMEDIO_DE_PRECIOS_DE_COPAS_DEL_MUNDO:
				mostrarPromedioDePreciosDeCopasDelMundo(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_COPAS_DEL_MUNDO_QUE_NO_SON_DE_UN_MATERIAL:
				mostrarCopasDelMundoQueNoSonDeUnMaterial(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_COPAS_DEL_MUNDO_ORDENADAS_POR_STOCK_ASCENDENTE:
				mostrarCopasDelMundoOrdenadasPorStockAscendente(fabricaDeCopasDelMundo);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta pronto!");
				break;
			}
		} while (opcionMenu != MenuPrincipal.SALIR);

	}

	private static void agregarCopaDelMundo(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se debe solicitar el ingreso de los datos para crear una copa del
		// mundo: material como texto, stock (debe ser mayor que cero y menor o igual a
		// la maxima capacidad de produccion que posee la fabrica), precio (debe ser
		// mayor que cero). Para el id, utilizar cero, ya que, la fabrica le asignara un
		// valor de id en base a si ya existe una copa del mundo de dicho material.
		// Se debe ingresar la copa del mundo en la fabrica e indicar si se pudo
		// realizar la operacion mostrando un mensaje de exito, o uno de error en caso
		// de
		// no poder agregar.
		Materiales material = ingresarMaterialComoTexto();
		double precio = 0;
		int stock = 0;

		do {
			precio = ingresarDouble("\nIngrese el precio de la copa del mundo:");
		} while (precio <= 0);

		do {
			stock = ingresarEntero("\nIngrese el stock de la copa del mundo:");
		} while (stock < 1 || stock > FabricaDeCopasDelMundo.MAXIMA_CAPACIDAD_DE_PRODUCCION_DE_COPAS_DEL_MUNDO);

		CopaDelMundo copaDelMundo = new CopaDelMundo(0, material, precio, stock);
		boolean agregada = fabricaDeCopasDelMundo.agregarCopaDelMundo(copaDelMundo);

		if (agregada) {
			mostrarPorPantalla("\nCopa agregada correctamente");
		} else {
			mostrarPorPantalla("\nNo fue posible agregar la copa del mundo");
		}
	}

	private static void mostrarCopasDelMundoQueNoSonDeUnMaterial(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se debe solicitar al usuario el ingreso del material como texto, para
		// mostrar las copas del mundo que no sean de dicho material (solicitando el
		// array a la fabrica de copas del mundo).

		Materiales material = ingresarMaterialComoTexto();

		CopaDelMundo[] copasDelMundoObtenidas = fabricaDeCopasDelMundo
				.obtenerCopasDelMundoQueNoSonDeUnMaterial(material);
		mostrarArrayDeCopasDelMundo(copasDelMundoObtenidas);
	}

	private static void mostrarPromedioDePreciosDeCopasDelMundo(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se debe obtener el promedio de precios de las copas del mundo desde la
		// fabrica y mostrarlo.
		double promedio = fabricaDeCopasDelMundo.obtenerPromedioDePreciosDeCopasDelMundo();
		mostrarPorPantalla("\nPromedio de precios: " + promedio);
	}

	private static void mostrarCopasDelMundoOrdenadasPorStockAscendente(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se deben obtener las copas del mundo ordenadas por stock ascendente y
		// mostrarlas.
		CopaDelMundo[] copasDelMundo = fabricaDeCopasDelMundo.obtenerCopasDelMundoOrdenadasPorStockAscendente();
		mostrarArrayDeCopasDelMundo(copasDelMundo);
	}

	private static Materiales ingresarMaterialComoTexto() {
		// TODO: Se debe mostrar los materiales de las copas del mundo, solicitar el
		// ingreso del material deseado como texto y devolver la
		// opcion del enum correspondiente. Considerar el ingreso del dato en mayusculas
		// o minusculas.
		mostrarMaterialesDeCopaDelMundo();
		String materialComoTexto = ingresarString("Ingrese el material de la copa del mundo:");
		return Materiales.valueOf(materialComoTexto.toUpperCase());
	}

	private static MenuPrincipal ingresarOpcionDeMenuPrincipal() {
		int opcion = 0;
		mostrarMenuPrincipal();
		opcion = ingresarEntero("\nIngrese la opcion deseada:");
		return MenuPrincipal.values()[opcion - 1];
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n*****Menu de copas del mundo*****\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + "- " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarMaterialesDeCopaDelMundo() {
		String tiposDePasta = "\n*****Materiales de copas del mundo*****\n";
		for (int i = 0; i < Materiales.values().length; i++) {
			tiposDePasta += Materiales.values()[i] + "\n";
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

	private static double ingresarDouble(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextDouble();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarArrayDeCopasDelMundo(CopaDelMundo[] copasDelMundo) {
		for (int i = 0; i < copasDelMundo.length; i++) {
			if (copasDelMundo[i] != null) {
				mostrarPorPantalla(copasDelMundo[i].toString());
			}
		}
	}
}
