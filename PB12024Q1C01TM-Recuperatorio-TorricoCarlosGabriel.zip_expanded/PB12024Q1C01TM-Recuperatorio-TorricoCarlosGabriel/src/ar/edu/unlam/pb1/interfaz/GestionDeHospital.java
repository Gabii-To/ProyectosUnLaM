package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Atencion;
import ar.edu.unlam.pb1.dominio.Hospital;
import ar.edu.unlam.pb1.dominio.Paciente;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class GestionDeHospital {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		Hospital hospital = new Hospital();
		MenuPrincipal menuIngresado = null;

		do {
			menuIngresado = ingresarOpcionDeMenuPrincipalValida();

			switch (menuIngresado) {
			case MOSTRAR_PACIENTES_QUE_EN_SU_DNI_INCLUYAN:
				mostrarPacientesQueEnSuDniIncluyan(hospital);
				break;
			case GENERAR_ATENCION:
				generarAtencion(hospital);
				break;
			case DIAGNOSTICAR_ATENCION:
				diagnosticarUnaAtencion(hospital);
				break;
			case MOSTRAR_PACIENTE_POR_SU_DNI:
				mostrarUnPacientePorSuDni(hospital);
				break;
			case MOSTRAR_ATENCIONES_CON_VALOR_ENTRE_LIMITES:
				mostrarAtencionesCuyoValorSeEncuentraEntreLimites(hospital);
				break;
			case MOSTRAR_VALOR_TOTAL_DE_ATENCIONES_DIAGNOSTICADAS:
				mostrarValorTotalDeAtencionesDiagnosticadas(hospital);
				break;
			case SALIR:
				mostrarPorPantalla("\nGracias por utilizar el sistema.");
				break;
			}

		} while (!menuIngresado.equals(MenuPrincipal.SALIR));

	}

	private static void mostrarPacientesQueEnSuDniIncluyan(Hospital hospital) {
		// TODO: Se debe solicitar el ingreso de numeros y mostrar los pacientes que
		// incluyen estos numeros en su DNI.
		// Ejemplo de ingreso: 333. Solo se deben mostrar pacientes que en su DNI
		// incluyan 333.
		
		String buscarNumeros = ingresarString("Ingresar numeros para buscarlos en los DNI:");
		Paciente[] pacientesEncontrados = hospital.obtenerPacientesQueEnSuDniIncluyan(buscarNumeros);
		mostrarPacientes(pacientesEncontrados);
	}

	private static void generarAtencion(Hospital hospital) {
		// TODO: Se debe solicitar el ingreso del DNI del paciente y el motivo de la
		// consulta para generar una atencion. Se debe mostrar un mensaje de exito o
		// error segun corresponda.
		
		long dni = ingresarEnteroLargo("Ingrese su DNI:");
		String motivoConsulta = ingresarString("Ingrese el motivo de su consulta:");
		boolean atencionGenerada = hospital.generarAtencion(dni, motivoConsulta);
		if(atencionGenerada)
			mostrarPorPantalla("La atencion fue generada exitosamente.");
		else
			mostrarPorPantalla("No se pudo generar la atencion.");
		
	}

	private static void diagnosticarUnaAtencion(Hospital hospital) {
		// TODO: Se deben mostrar las atenciones sin diagnosticar y solicitar el ingreso
		// del ID de la atencion que se quiere diagnosticar. Tambien se debe ingresar el
		// diagnostico para dicha atencion y realizar el diagnostico.
		// El diagnostico ingresado no puede ser vacio. Se debe continuar solicitando el
		// dato si asi fuera.
		// Mostrar un mensaje de exito o error segun el resultado de la operacion.
		
		
		Atencion [] atenciones = hospital.obtenerAtencionesSinDiagnostico();
		mostrarAtenciones(atenciones);
		int id = ingresarEntero("Ingrese el ID:");
		String diagnostico;
		do{
			diagnostico = ingresarString("Ingrese el diagnostico:");
		}while(diagnostico == null);
		boolean diagnosticoActualizado = hospital.diagnosticarAtencion(id, diagnostico);
		if(diagnosticoActualizado)
			mostrarPorPantalla("Diagnostico generado.");
		else
			mostrarPorPantalla("Error generando el diagnostico.");
		
	}

	private static void mostrarUnPacientePorSuDni(Hospital hospital) {
		// TODO: Se debe solicitar el ingreso del DNI del paciente, para luego
		// consultarlo y mostrarlo.
		// Si no existe algun paciente para ese DNI, mostrar un mensaje acorde.
		
		long dni = ingresarEnteroLargo("Ingrese DNI del paciente:");
		Paciente paciente = hospital.obtenerPacientePorDni(dni);
		if(paciente != null)
			mostrarPorPantalla(paciente.toString());
		else
			mostrarPorPantalla("Paciente no encontrado.");
		
	}

	private static void mostrarAtencionesCuyoValorSeEncuentraEntreLimites(Hospital hospital) {
		// TODO: Se debe solicitar el ingreso de dos valores, uno inferior y otro
		// superior. Dichos valores se corresponden con el valor de una atencion.
		// El valor inferior no puede ser cero y tampoco menor a cero.
		// El valor superior debe ser mayor al valor inferior. En ambos casos se debe
		// continuar solicitando el ingreso de los valores si no son correctos.
		double valorInferior;
		do {
			valorInferior = ingresarDouble("Ingrese un valor limite inferior:");
		}while(valorInferior<=0);
		double valorSuperior;
		do {
			valorSuperior = ingresarDouble("Ingrese un valor limite superior:");
		}while(valorSuperior<=valorInferior);
		Atencion atencionesEntreValores [] = hospital.obtenerAtencionesConValorEntreLimites(valorInferior, valorSuperior);
		mostrarAtenciones(atencionesEntreValores);
		
	}

	private static void mostrarValorTotalDeAtencionesDiagnosticadas(Hospital hospital) {
		// TODO: Se debe obtener y mostrar el total de las atenciones que ya fueron
		// diagnosticadas.
		
		double valorTotalDiagnosticadas = hospital.obtenerValorTotalDeAtencionesDiagnosticadas();
		mostrarPorPantalla("El valor total de las atenciones diagnosticadas es:" + valorTotalDiagnosticadas);
		
	}

	private static MenuPrincipal ingresarOpcionDeMenuPrincipalValida() {
		int opcion = 0;
		do {
			mostrarMenuPrincipal();
			opcion = ingresarEntero("\nIngrese la opcion deseada: ");
		} while (opcion < 1 || opcion > MenuPrincipal.values().length);

		return MenuPrincipal.values()[opcion - 1];
	}

	private static int ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static double ingresarDouble(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextDouble();
	}

	private static long ingresarEnteroLargo(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextLong();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n### Menu principal ###\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + "- " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarPacientes(Paciente[] pacientes) {
		for (int i = 0; i < pacientes.length; i++) {
			if (pacientes[i] != null) {
				mostrarPorPantalla(pacientes[i].toString());
			}
		}
	}

	private static void mostrarAtenciones(Atencion[] atenciones) {
		for (int i = 0; i < atenciones.length; i++) {
			if (atenciones[i] != null) {
				mostrarPorPantalla(atenciones[i].toString());
			}
		}
	}
}
