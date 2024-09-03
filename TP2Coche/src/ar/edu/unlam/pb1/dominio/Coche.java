package ar.edu.unlam.pb1.dominio;

public class Coche {
	
	//Atributos
	private final int CERO_KM = 0;
	private final int ANIO_ACTUAL = 2024;
	private static int cantidadCoches;
	private String marca;
	private String modelo;
	private int kilometros;
	private int anio;
	private double precio;
	
	//Constructores
	public Coche(String marca, String modelo, double precio) {
	}
	public Coche(String marca, String modelo, int kilometros, int anio, double precio) {
		this.marca = marca;
		this.modelo = modelo;
		this.kilometros = kilometros;
		this.anio = anio;
		this.precio = precio;
	}
	
	//Metodos
	public static int getCantidadCoches() {
		cantidadCoches +=4;
		cantidadCoches++;
		return cantidadCoches;
	}
	public void setKilometros(int kilometros) {
		this.kilometros += kilometros;
	}
	public void setPrecio(int precio) {
		this.precio += precio;
	}
	public double getPrecio() {
		return precio;
	}
	public int calcularAntiguedad() {
		return (ANIO_ACTUAL-anio);
	}
	public int getKilometros() {
		return kilometros;
	}
	public boolean esCeroKM() {
		boolean sensor = kilometros == this.CERO_KM;
		return sensor;
	}
}