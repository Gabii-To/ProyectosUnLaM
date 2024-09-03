package interfaz;

import enums.Armas;
import enums.Habitaciones;
import enums.Menu_Principal;
import enums.Sospechosos;
import enums.SubMenu;

import java.util.Scanner;

import dominio.Sospecha;
import dominio.ListaDeSospechas;

public class Clue {
	static Scanner teclado = new Scanner(System.in);
	static String detective = null;
	// static int id = 1;

	public static void main(String[] args) {

		ListaDeSospechas sospecha = new ListaDeSospechas(10);

		Menu_Principal opcionValidada = null;

		do {
			mostrarMenu();

			opcionValidada = ingresarOpcionPrincipal();

			switch (opcionValidada) {
			case REGLAS:
				mostrarReglas();
				break;
			case EMPEZAR_A_JUGAR:
				mostrarHistoria();
				subMenu(sospecha);
				break;
			case SALIR:
				mostrarPorPantalla("Hasta luego!");
				break;
			}

		} while (!opcionValidada.equals(Menu_Principal.SALIR)); 

		teclado.close();
	}

	private static void subMenu(ListaDeSospechas listaDeSospechas) {

		SubMenu opcionMenu = null;

		do {
			mostrarSubMenu();
			opcionMenu = ingresarOpcionSub();

			switch (opcionMenu) {
			case AGREGAR_SOSPECHA:
				agregarSospecha(listaDeSospechas);
				break;
			case ELIMINAR_SOSPECHA:
				eliminarSospecha(listaDeSospechas);
				break;
			case MODIFICAR_SOSPECHA:
				modificarSospecha(listaDeSospechas);
				break;
			case MOSTRAR_SOSPECHAS_ORDENADAS_POR_SOSPECHOSO:
				mostrarSospechasOrdenadas(listaDeSospechas);
				break;
			case BUSCAR_SOSPECHA_POR_ID:
				buscarSospechaPorId(listaDeSospechas);
				break;
			case SENIALAR_CULPABLE:
				senialarCulpable(listaDeSospechas);
				break;
			case VOLVER:
				break;

			}
		} while (!opcionMenu.equals(SubMenu.VOLVER));

	}

	private static void senialarCulpable(ListaDeSospechas listaDeSospechas) {
		String respuesta; 
		
		do {
		respuesta =	pedirString("Usted tiene tres chances, en caso de no acertar ninguna, pierde el juego\n Desea continuar?");
		if(respuesta.equalsIgnoreCase("si")) {
			validarSospecha(listaDeSospechas);
		} else if (respuesta.equalsIgnoreCase("no")) {
		
		}	
	}	while(!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
		
}
	private static void validarSospecha(ListaDeSospechas listaDeSospechas) {
		boolean asesinoEncontrado = false;
		int intentos = 3;
		int id;
		Sospecha sospecha;
		while (intentos>0 && !asesinoEncontrado) {
			id = pedirNumeroEntero("Ingrese el ID de la sospecha que desea arriesgar");
			sospecha = listaDeSospechas.buscarPorId(id);
			
			if(sospecha.getSospechoso().equals(Sospecha.getAsesino()) && sospecha.getArma().equals(Sospecha.getArmaHomicida()) && 
					sospecha.getHabitacion().equals(Sospecha.getLugar())) {
				mostrarPorPantalla("\nGanaste!\nFin del juego");
				System.exit(0);
			} else if(intentos>1) {
				mostrarPorPantalla("\nRespuesta incorrecta\nTe queda un intento menos");
			} 
			
				intentos--;
		}
		System.err.println("\nPERDISTE\nEL ASESINO HA ESCAPADO!");
		System.exit(0);
	}

	private static void modificarSospecha(ListaDeSospechas listaDeSospechas) {
		Sospecha sospechaModificar = buscarSospechaPorId(listaDeSospechas);

		mostrarSospechosos();
		int opcionSospechoso = pedirOpcionValidada("Elija un sospechoso:", Sospechosos.values().length);
		Sospechosos sospechoso = Sospechosos.values()[opcionSospechoso];
		sospechaModificar.setSospechoso(sospechoso);

		mostrarArmas();
		int opcionArma = pedirOpcionValidada("Elija un arma:", Armas.values().length);
		Armas arma = Armas.values()[opcionArma];
		sospechaModificar.setArma(arma);

		mostrarHabitaciones();
		int opcionHabitacion = pedirOpcionValidada("Ingrese una habitacion:", Habitaciones.values().length);
		Habitaciones habitacion = Habitaciones.values()[opcionHabitacion];
		sospechaModificar.setHabitacion(habitacion);

		mostrarPorPantalla(sospechaModificar.toString());
		mostrarPorPantalla("¡Sospecha modificada! ");

	}

	private static Sospecha buscarSospechaPorId(ListaDeSospechas listaDeSospechas) {
		int id;
		do {
			id = pedirNumeroEntero("Ingrese el id de la sospecha: ");
		} while (id <= 0 || id > ListaDeSospechas.getId()); //
		Sospecha encontradaPorId = listaDeSospechas.buscarPorId(id);
		if(encontradaPorId != null) {
			mostrarPorPantalla(encontradaPorId.toString());
		} else {
			mostrarPorPantalla("Esta sospecha fue eliminada");
			
		}
		return encontradaPorId;
	}

	private static void mostrarSospechasOrdenadas(ListaDeSospechas listaDeSospechas) {
		// TODO Auto-generated method stub
		Sospecha[] ordenado = listaDeSospechas.obtenerSospechasOrdenadas();
		for (int i = 0; i < ordenado.length; i++) {
			if (ordenado[i] != null) {
				mostrarPorPantalla(ordenado[i].toString());
			}
		}
	}

	private static void agregarSospecha(ListaDeSospechas listaDeSospechas) {
		Sospecha sospechaGenerada = generarSospecha();
		boolean sospechaAgregada = listaDeSospechas.agregarSospecha(sospechaGenerada);

		if (sospechaAgregada) {
			mostrarPorPantalla("Se agrego!");
		} else {
			mostrarPorPantalla("No se pudo agregar, intente de nuevo");
		}
		compararSospecha(sospechaGenerada);

	}

	private static void compararSospecha(Sospecha sospechaGenerada) {
		compararSospechoso(sospechaGenerada);
		compararArma(sospechaGenerada);
		compararHabitacion(sospechaGenerada);
	}

	private static void compararHabitacion(Sospecha sospechaGenerada) {
		if (sospechaGenerada.getHabitacion().equals(Sospecha.getLugar())) {
			mostrarPorPantalla("En el " + Sospecha.getLugar() + " había rastros de sangre\n");
		} else if (sospechaGenerada.getHabitacion().equals(Habitaciones.COCINA)) {
			mostrarPorPantalla("La " + Habitaciones.COCINA + " parece estar desacomodada\n");
		} else {
			mostrarPorPantalla("Todo parece estar en órden en este lugar\n");
		}
		
	}

	private static void compararArma(Sospecha sospechaGenerada) {
		if (sospechaGenerada.getArma().equals(Sospecha.getArmaHomicida())) {
			mostrarPorPantalla("El " + Sospecha.getArmaHomicida() + " no está un su lugar\n");
		} else if (sospechaGenerada.getArma().equals(Armas.VENENO)) {
			mostrarPorPantalla("El " + Armas.VENENO + " está abierto\n");
		} else {
			mostrarPorPantalla("El objeto: " + sospechaGenerada.getArma().toString() + " no tiene signos de haber sido utilizada\n");
		}
		
	}

	private static void compararSospechoso(Sospecha sospechaGenerada) {
		if (sospechaGenerada.getSospechoso().equals(Sospecha.getAsesino())) {
			mostrarPorPantalla("A la " + Sospecha.getAsesino() + " se la nota nerviosa, como si intentara esconder algo\n");
		} else if (sospechaGenerada.getSospechoso().equals(Sospechosos.SRA_ESCARLATA)) {
			mostrarPorPantalla("A la " + Sospechosos.SRA_ESCARLATA + " se la nota indiferente\n");
		} else {
			mostrarPorPantalla("La persona " + sospechaGenerada.getSospechoso().toString() + " no muestra indicios de ser sospechoso\n");
		}

	}

	private static void eliminarSospecha(ListaDeSospechas listaDeSospechas) {
		int sospechaElegida = pedirNumeroEntero("Ingrese el Id de la sospecha a eliminar:");
		Sospecha sospecha = listaDeSospechas.buscarPorId(sospechaElegida);
		boolean eliminado = listaDeSospechas.sospechaEliminada(sospecha);
		if (eliminado) {
			mostrarPorPantalla("Sospecha eliminada.");
		} else
			mostrarPorPantalla("Error en eliminar.");
	}

	private static Sospecha generarSospecha() {
		mostrarSospechosos();
		int opcionSospechoso = pedirOpcionValidada("Elija un sospechoso:", Sospechosos.values().length);
		Sospechosos sospechoso = Sospechosos.values()[opcionSospechoso];

		mostrarArmas();
		int opcionArma = pedirOpcionValidada("Elija un arma:", Armas.values().length);
		Armas arma = Armas.values()[opcionArma];

		mostrarHabitaciones();
		int opcionHabitacion = pedirOpcionValidada("Ingrese una habitacion:", Habitaciones.values().length);
		Habitaciones habitacion = Habitaciones.values()[opcionHabitacion];

		Sospecha sospecha = new Sospecha(sospechoso, habitacion, arma);
		// id++;
		return sospecha;
	}

	private static void mostrarSubMenu() {
		mostrarPorPantalla("\nIngrese que desea hacer\n");
		for (int i = 0; i < SubMenu.values().length; i++) {
			mostrarPorPantalla(i + 1 + ". " + SubMenu.values()[i].getDescripcion());
		}
		mostrarPorPantalla("");
	}

	private static void mostrarHabitaciones() {
		mostrarPorPantalla("\nHABITACIONES:");

		for (int i = 0; i < Habitaciones.values().length; i++) {
			mostrarPorPantalla(i + 1 + ". " + Habitaciones.values()[i]);
		}
		mostrarPorPantalla("");
	}

	private static void mostrarArmas() {
		mostrarPorPantalla("\nARMAS:");

		for (int i = 0; i < Armas.values().length; i++) {
			mostrarPorPantalla(i + 1 + ". " + Armas.values()[i]);
		}
		mostrarPorPantalla("");
	}

	private static void mostrarSospechosos() {
		mostrarPorPantalla("\nSOSPECHOSOS:");

		for (int i = 0; i < Sospechosos.values().length; i++) {
			mostrarPorPantalla(i + 1 + ". " + Sospechosos.values()[i]);
		}
		mostrarPorPantalla("");
	}

	private static void mostrarHistoria() {
		mostrarPorPantalla("La historia...\r\n" + "\r\n"
				+ "Esta noche Samuel Black, un solitario y triste millonario, ha invitado a seis huéspedes a su mansión.\r\n"
				+ "\r\n"
				+ "¿Su objetivo? Revelar una sorprendente verdad: cada uno de los seis está destinado a servir a una Casa CLUE para proteger los mayores secretos del mundo.\r\n"
				+ "\r\n"
				+ "Pero antes de que Black pudiera desvelar nada, ¡ha sido asesinado! Solo los seis invitados estaban en la mansión en ese momento.\r\n");

		detective = pedirString("Ingrese su nombre valeroso y encantador detective:");

		mostrarPorPantalla("¡Ahora depende de ti " + detective.toUpperCase() + " resolver el crimen!");

	}

	private static void mostrarReglas() {
		mostrarPorPantalla("Reglas:\r\n"
				+ "Al comenzar a jugar se debe ingresar el nombre del jugador, quien sera el detective en este caso.\r\n"
				+ "´El detective debera comenzar a sospechar de entre una lista de personas , armas y habitaciones que se encuentran en la mansion.\r\n´"
				+ "Con las funciones Agregar sospechoso, Modificar sospechoso y Eliminar sospechoso gestionaremos nuestras sospechas.\r\n"
				+ "Con las funciones de busqueda podremos analizar nuestras sospechas una por una en una lista.\r\n"
				+ "Al final con la funcion SeñalarCulpable podremos elegir entre nuestras sospechas cual nos parece que es un posible culpable hasta un numero de 3 veces, si el jugador acierta habra\r\n"
				+ "detenido al culpable y ganara el juego, sino el culpable escapara y el jugador perdera la partida.");

	}

	static public void mostrarMenu() {
		mostrarPorPantalla(
				"--------------------------------------------------\nMenu Principal\n--------------------------------------------------");
		for (int i = 0; i < Menu_Principal.values().length; i++) {
			mostrarPorPantalla(i + 1 + ". " + Menu_Principal.values()[i].getDescripcion());
		}
		mostrarPorPantalla("--------------------------------------------------");
	}

	static public Menu_Principal ingresarOpcionPrincipal() {
		int opcionMenu = pedirOpcionValidada("Ingrese su opcion:", Menu_Principal.values().length);
		return Menu_Principal.values()[opcionMenu];
	}

	static public SubMenu ingresarOpcionSub() {
		int opcionMenu = pedirOpcionValidada("Ingrese su opcion:", SubMenu.values().length);
		return SubMenu.values()[opcionMenu];
	}

	private static int pedirOpcionValidada(String mensaje, int largoDelEnum) {
		int opcion;
		do {
			opcion = pedirNumeroEntero(mensaje);
			if (opcion < 1 || opcion > largoDelEnum)
				mostrarPorPantalla("Opcion invalida.");
		} while (opcion < 1 || opcion > largoDelEnum);
		return opcion - 1;
	}

	static private String pedirString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next().trim();
	}

	static private int pedirNumeroEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	static private void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
