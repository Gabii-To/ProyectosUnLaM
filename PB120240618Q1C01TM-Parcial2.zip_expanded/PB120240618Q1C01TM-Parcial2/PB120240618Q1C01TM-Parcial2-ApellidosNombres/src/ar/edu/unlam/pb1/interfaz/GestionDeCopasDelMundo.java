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

		String nombreFabrica = ingresarString("Ingrese el nombre de la fabrica");
		mostrarPorPantalla("Fabrica de Copas del Mundo: " + nombreFabrica);
		FabricaDeCopasDelMundo fabricaDeCopasDelMundo = new FabricaDeCopasDelMundo(nombreFabrica);
		do {
			opcionMenu = ingresarOpcionDeMenuPrincipal();
			switch (opcionMenu) {
			case AGREGAR_COPA_DEL_MUNDO:
				agregarCopaDelMundo(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_COPAS_DEL_MUNDO_ORDENADAS_POR_STOCK_ASCENDENTE:
				mostrarCopasDelMundoOrdenadasPorStockAscendente(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_COPAS_DEL_MUNDO_QUE_NO_SON_DE_UN_MATERIAL:
				mostrarCopasDelMundoQueNoSonDeUnMaterial(fabricaDeCopasDelMundo);
				break;
			case MOSTRAR_PROMEDIO_DE_PRECIOS_DE_COPAS_DEL_MUNDO:
				mostrarPromedioDePreciosDeCopasDelMundo(fabricaDeCopasDelMundo);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta luego!");
				break;
			}
		}while(opcionMenu != MenuPrincipal.SALIR);
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
		int stock;
		do {
			stock = ingresarEntero("Ingrese stock (no mayor a " + FabricaDeCopasDelMundo.MAXIMA_CAPACIDAD_DE_PRODUCCION_DE_COPAS_DEL_MUNDO + "):");
			
		}while(stock <= 0 || stock > FabricaDeCopasDelMundo.MAXIMA_CAPACIDAD_DE_PRODUCCION_DE_COPAS_DEL_MUNDO);
		double precio;
		do {
			precio = ingresarDouble("Ingrese precio:");
		} while (precio <= 0);
		CopaDelMundo copaDelMundo = new CopaDelMundo(0, material, precio, stock);
		boolean copaActualizada = fabricaDeCopasDelMundo.agregarCopaDelMundo(copaDelMundo);
		if(copaActualizada)
			mostrarPorPantalla("Stock actualizado!");
		else
			mostrarPorPantalla("Copa agregada!");
	}

	private static void mostrarCopasDelMundoQueNoSonDeUnMaterial(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se debe solicitar al usuario el ingreso del material como texto, para
		// mostrar las copas del mundo que no sean de dicho material (solicitando el
		// array a la fabrica de copas del mundo).
		
		String materialComoTexto = ingresarString("Ingrese material:").toUpperCase();
		Materiales material = Materiales.valueOf(materialComoTexto);
		CopaDelMundo[] nuevoArray = fabricaDeCopasDelMundo.obtenerCopasDelMundoQueNoSonDeUnMaterial(material);
		if(nuevoArray[0] == null)
			mostrarPorPantalla("No existe copas que no sean de " + material );
		else
			mostrarArrayDeCopasDelMundo(nuevoArray);
	}

	private static void mostrarPromedioDePreciosDeCopasDelMundo(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se debe obtener el promedio de precios de las copas del mundo desde la
		// fabrica y mostrarlo.
		
		double promedio = fabricaDeCopasDelMundo.obtenerPromedioDePreciosDeCopasDelMundo();
		if(promedio == -1)
			mostrarPorPantalla("No hay copas ingresadas");
		else
			mostrarPorPantalla("El promedio de precios de las copas es $" + promedio);
	}

	private static void mostrarCopasDelMundoOrdenadasPorStockAscendente(FabricaDeCopasDelMundo fabricaDeCopasDelMundo) {
		// TODO: Se deben obtener las copas del mundo ordenadas por stock ascendente y
		// mostrarlas.
		
		mostrarArrayDeCopasDelMundo(fabricaDeCopasDelMundo.obtenerCopasDelMundoOrdenadasPorStockAscendente());
	}

	private static Materiales ingresarMaterialComoTexto() {
		// TODO: Se debe mostrar los materiales de las copas del mundo, solicitar el
		// ingreso del material deseado como texto y devolver la
		// opcion del enum correspondiente. Considerar el ingreso del dato en mayusculas
		// o minusculas.
		
		mostrarMaterialesDeCopaDelMundo();
		String materialComoTexto;
		materialComoTexto = ingresarString("Ingresar material:").toUpperCase();
		Materiales material = Materiales.valueOf(materialComoTexto);
		return material;
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
		String materialesDeCopasDelMundo = "\n*****Materiales de copas del mundo*****\n";
		for (int i = 0; i < Materiales.values().length; i++) {
			materialesDeCopasDelMundo += Materiales.values()[i] + "\n";
		}
		mostrarPorPantalla(materialesDeCopasDelMundo);
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
