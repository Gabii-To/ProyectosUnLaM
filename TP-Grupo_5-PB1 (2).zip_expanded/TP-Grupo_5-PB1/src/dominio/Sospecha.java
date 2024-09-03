package dominio;

import enums.Armas;
import enums.Habitaciones;
import enums.Sospechosos;

public class Sospecha {
	
	private Sospechosos sospechoso;
	private Habitaciones habitacion;
	private Armas arma;
	private  int id;
	private static final Sospechosos ASESINO = Sospechosos.STA_BLANCO;
	private static final Armas ARMA_HOMICIDA = Armas.CANDELABRO;
	private static final Habitaciones LUGAR = Habitaciones.PATIO;
	
	
	public int getId() {
		return id;
	}

	public Sospecha(Sospechosos sospechoso, Habitaciones habitacion, Armas arma) {
		super();
		this.sospechoso = sospechoso;
		this.habitacion = habitacion;
		this.arma = arma;
		this.id = ListaDeSospechas.obtenerProximoId();
		
	}

	
	public Sospechosos getSospechoso() {
		return sospechoso;
	}


	public void setSospechoso(Sospechosos sospechoso) {
		this.sospechoso = sospechoso;
	}


	public Habitaciones getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitaciones habitacion) {
		this.habitacion = habitacion;
	}


	public Armas getArma() {
		return arma;
	}


	public void setArma(Armas arma) {
		this.arma = arma;
	}


	public static Sospechosos getAsesino() {
		return ASESINO;
	}

	public static Armas getArmaHomicida() {
		return ARMA_HOMICIDA;
	}

	public static Habitaciones getLugar() {
		return LUGAR;
	}

	@Override
	public String toString() {
		return "\nSospecha:  id= " + id + "  sospechoso= " + sospechoso + "  habitacion= " + habitacion + "  arma= " + arma;
	}
	
	
}
