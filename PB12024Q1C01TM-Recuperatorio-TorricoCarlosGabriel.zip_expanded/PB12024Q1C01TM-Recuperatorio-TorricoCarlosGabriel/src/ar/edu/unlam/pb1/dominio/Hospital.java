package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePaciente;

public class Hospital {
	public final int VALOR_BASE = 1000;
	private Atencion[] atencionesSinDiagnostico;
	private Paciente[] pacientes;
	private static int proximoId = 0;

	// TODO: completar el constructor, getters y setters para el correcto
	// funcionamiento. Considere el uso de constantes.
	
	public Hospital() {
		this.pacientes = new Paciente[100];
		this.atencionesSinDiagnostico = new Atencion[1000];
		this.agregarPacientes(); // Esta linea debe estar presente para cargar los pacientes
	}

	public Paciente[] obtenerPacientesQueEnSuDniIncluyan(String textoABuscar) {
		// TODO: Se deberan devolver los pacientes que en su DNI contengan el
		// textoABuscar facilitado como parametro.
		
		Paciente[] pacientesQueEnSuDniIncluyan= new Paciente[100];
		int posicion = 0;
		for(int i=0;i<this.pacientes.length;i++) {
			if(this.pacientes[i] != null && String.valueOf(this.pacientes[i].getDni()).contains(textoABuscar)) {
				pacientesQueEnSuDniIncluyan[posicion] = this.pacientes[i];
				posicion++;
			}
				
		}
		return pacientesQueEnSuDniIncluyan;
	}
	
	private double obtenerValorDeConsultaPorPaciente(Paciente paciente) {
		// TODO: Debe calcular y devolver el valor de la atencion basandose en el tipo
		// de paciente de un paciente.
		// Cuando el paciente es AFILIADO, se le descuenta el 50% del valor base de la
		// atencion.
		// Cuando el paciente es OBRA_SOCIAL, se le descuentan $350 del valor base de la
		// atencion.
		// Cuando el paciente es particular se le cobra el valor base completo de la
		// atencion.
		// Los pacientes solo tienen un tipo de los 3 posibles.
		
		double valorConsulta = 0;
		if(paciente.getTipoDePaciente() == TipoDePaciente.AFILIADO)
			valorConsulta = VALOR_BASE * 0.5;
		else if(paciente.getTipoDePaciente() == TipoDePaciente.OBRA_SOCIAL)
			valorConsulta = VALOR_BASE - 350;
		else if(paciente.getTipoDePaciente() == TipoDePaciente.AFILIADO)
			valorConsulta = VALOR_BASE;
		return valorConsulta;
	}

	public boolean generarAtencion(long dniPaciente, String motivoDeConsulta) {
		// TODO: Se debe verificar si existe un paciente para el DNI suministrado y
		// agregar una nueva atencion. Es necesario completar el valor de la misma,
		// antes.
		// Si no se encuentra el paciente con el DNI dado, no se puede generar la
		// atencion.
		
		Paciente paciente = obtenerPacientePorDni(dniPaciente);
		boolean encontrado = false;
		double valorConsulta;
		if(paciente != null) {
			valorConsulta = obtenerValorDeConsultaPorPaciente(paciente);
			encontrado = agregarAtencion(paciente, motivoDeConsulta, valorConsulta);
		}
		return encontrado;
	}

	private boolean agregarAtencion(Paciente paciente, String motivoDeConsulta, double valorDeConsulta) {
		// TODO: Debe agregar una atencion al array de atenciones. Devuelve verdadero si
		// es posible realizar la operacion. Falso en caso contrario.
		// Las atenciones no poseen diagnostico al ser agregadas.
		// El hospital provee sus propios identificadores para las atenciones.
		
		boolean agregado = false;
		int posicion = 0;
		while(posicion < this.atencionesSinDiagnostico.length && !agregado) {
			if(this.atencionesSinDiagnostico[posicion] == null) {
				this.atencionesSinDiagnostico[posicion] = new Atencion(this.obtenerProximoId(), paciente, motivoDeConsulta, "", valorDeConsulta);
				agregado = true;
			}
			posicion++;
		}
		return agregado;
	}

	public int obtenerProximoId() {
		return ++proximoId;

	}

	public boolean diagnosticarAtencion(long idAtecion, String diagnostico) {
		// TODO: Debe completar el diagnostico de la atencion que cumpla con el
		// idAtencion suministrado.
		// Devuelve verdadero en caso de actualizar el diagnostico, o falso, en caso
		// contrario.
		
		int posicion = 0;
		boolean idEncontrado = false;
		Atencion [] atencionesSinDiagnostico = obtenerAtencionesSinDiagnostico();
		while(posicion<atencionesSinDiagnostico.length && !idEncontrado) {
			if(atencionesSinDiagnostico[posicion] != null && atencionesSinDiagnostico[posicion].getId() == idAtecion) {
				atencionesSinDiagnostico[posicion].setDiagnostico(diagnostico);
				idEncontrado = true;
			}
			posicion++;
		}
		
		return idEncontrado;
	}

	public Atencion[] getAtenciones() {
		return atencionesSinDiagnostico;
	}

	public Paciente[] getPacientes() {
		return pacientes;
	}

	public Paciente obtenerPacientePorDni(long dni) {
		// TODO: Debe devolver el paciente que cumpla con el DNI proporcionado.
		// En caso de no encontrar uno, devolver null.

		int posicion = 0;
		boolean encontrado = false;
		Paciente pacienteEncontrado = null;
		while(posicion<this.pacientes.length && !encontrado) {
			if(this.pacientes[posicion] != null) {
				if(this.pacientes[posicion].getDni() == dni) {
					pacienteEncontrado = this.pacientes[posicion];
					encontrado = true;
				}
			}
			posicion++;
		}
		return pacienteEncontrado;
	}

	public Atencion[] obtenerAtencionesSinDiagnostico() {
		// TODO: Se deben devolver las atenciones que no tengan completado el
		// diagnostico.
		Atencion [] atencionesSinDiagnostico = new Atencion[1000];
		int posicion = 0;
		for(int i=0;i<this.atencionesSinDiagnostico.length;i++) {
			if(this.atencionesSinDiagnostico[i] != null &&  this.atencionesSinDiagnostico[i].getDiagnostico().isEmpty()) {
				atencionesSinDiagnostico[posicion] = this.atencionesSinDiagnostico[i];
				posicion++;
			}
		}
		
		return atencionesSinDiagnostico;
	}

	public Atencion[] obtenerAtencionesConValorEntreLimites(double valorLimiteInferior, double valorLimiteSuperior) {
		// TODO: Se deben devolver las atenciones cuyo valor se encuentra entre los
		// limites proporcionados (inferior y superior), incluyendolos.
		Atencion[] atencionesEntreValores = new Atencion[1000];
		int posicion = 0;
		for(int i=0;i<this.atencionesSinDiagnostico.length;i++) {
			if(this.atencionesSinDiagnostico[i] != null && this.atencionesSinDiagnostico[i].getValor()>=valorLimiteInferior 
					&& this.atencionesSinDiagnostico[i].getValor()<=valorLimiteSuperior) {
				atencionesEntreValores[posicion] = this.atencionesSinDiagnostico[i];
				posicion++;
			}
		}
		return atencionesEntreValores;
	}

	public double obtenerValorTotalDeAtencionesDiagnosticadas() {
		// TODO: Se debe calcular y devolver el valor total de las atenciones que fueron
		// diagnosticadas (se completo su diagnostico).
		
		double acumulador = 0;
		for(int i=0;i<this.atencionesSinDiagnostico.length;i++) {
			if(this.atencionesSinDiagnostico[i] != null) {
				acumulador += this.atencionesSinDiagnostico[i].getValor();
			}
		}
		return acumulador;
	}

	private void agregarPacientes() {
		int pos = 0;
		this.pacientes[pos++] = new Paciente(11222333, "Perez", "Carlos", TipoDePaciente.AFILIADO);
		this.pacientes[pos++] = new Paciente(22222000, "Pergamino", "Juan", TipoDePaciente.OBRA_SOCIAL);
		this.pacientes[pos++] = new Paciente(33888444, "Lauro", "Laura", TipoDePaciente.PARTICULAR);
		this.pacientes[pos++] = new Paciente(44222333, "Lautin", "Lautaro", TipoDePaciente.OBRA_SOCIAL);
		this.pacientes[pos++] = new Paciente(44333444, "Marco", "Matias", TipoDePaciente.OBRA_SOCIAL);
		this.pacientes[pos++] = new Paciente(22555777, "Arco", "Martin", TipoDePaciente.PARTICULAR);
		this.pacientes[pos++] = new Paciente(33888999, "Pamino", "Pedro", TipoDePaciente.AFILIADO);
	}
}
