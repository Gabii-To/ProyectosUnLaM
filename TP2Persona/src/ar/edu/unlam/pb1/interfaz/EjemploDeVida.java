package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.Persona;

public class EjemploDeVida {
	public static void main (String[]args) {
		Persona sofia = new Persona("Sofia","Mujer",38432153,2.8,0.50);
		System.out.println(sofia.nombre);
		System.out.println(sofia.peso + " Kg.");
		System.out.println(sofia.altura + " mts.");
		sofia.alimentar(1.0);
		sofia.crecer(0.5);
		sofia.cumplirAnios();
		sofia.cumplirAnios();
		sofia.cumplirAnios();
		sofia.cumplirAnios();
		System.out.println(sofia.peso);
		System.out.println(sofia.altura);
		System.out.println(sofia.edad);
		Persona nadia = new Persona("Nadia","Mujer",36295667,55,1.55);
		
		System.out.println(nadia.nombre);
		System.out.println(nadia.peso);
		System.out.println(nadia.altura);
		System.out.println(nadia.edad);
	}
}
