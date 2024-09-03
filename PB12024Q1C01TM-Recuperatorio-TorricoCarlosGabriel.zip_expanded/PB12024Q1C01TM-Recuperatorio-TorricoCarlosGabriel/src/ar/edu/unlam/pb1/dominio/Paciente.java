package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePaciente;

public class Paciente {

	private long dni;
	private String apellido;
	private String nombre;
	private TipoDePaciente tipoDePaciente;

	public Paciente(long dni, String apellido, String nombre, TipoDePaciente tipoDePaciente) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.tipoDePaciente = tipoDePaciente;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDePaciente getTipoDePaciente() {
		return tipoDePaciente;
	}

	public void setTipoDePaciente(TipoDePaciente tipoDePaciente) {
		this.tipoDePaciente = tipoDePaciente;
	}

	@Override
	public String toString() {
		return "Paciente [dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", tipoDePaciente="
				+ tipoDePaciente + "]";
	}

}
