package ar.edu.unlam.pb1.dominio;

public class Persona {
	//Atributos
	public String nombre;
	public String genero;
	public long dni;
	public  double peso;
	public  double altura;
	public  int edad;
	public static boolean viva;
	
	//Constructores
	public Persona(String nombre, String genero, long dni, double peso, double altura) {
		this.nombre = nombre;
		this.genero = genero;
		this.dni = dni;
		this.peso = peso;
		this.altura = altura;
	}
	
	//Metodos
	public void alimentar(double kilos) {
		peso += kilos;
	}
	public void crecer(double metros) {
		altura += metros;
	}
	public void cumplirAnios() {
		edad++;
	}
	public void morir() {
		
	}
	public double pesar() {
		
		return peso;
	}
}