package ar.edu.unlam.pb1.interfaz;

import ar.edu.unlam.pb1.dominio.Coche;

public class EjemploDeCoches {

	public static void main(String[] args) {
		Coche fitito = new Coche("Fito","Paez",1000,1993,1200000);
		System.out.println(fitito.getPrecio());
		System.out.println(fitito.getKilometros());
		fitito.setKilometros(43000);
		System.out.println(fitito.getKilometros());
		System.out.println(Coche.getCantidadCoches());
		fitito.setPrecio(44000);
		System.out.println(fitito.getPrecio());
		System.out.println(fitito.calcularAntiguedad());
		System.out.println(fitito.esCeroKM());
	}

}
