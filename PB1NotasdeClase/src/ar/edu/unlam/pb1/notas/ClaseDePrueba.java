package ar.edu.unlam.pb1.notas;

import java.util.Scanner;

public class ClaseDePrueba {
	public static void main(String[]args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese nota de primer parcial: ");
		int nota1 = teclado.nextInt();
		System.out.println("Ingrese nota de segundo parcial: ");
		int nota2 = teclado.nextInt();
		
		System.out.println("Promociono: " + (nota1>=7 && nota2>=7));
		System.out.println("Aprobo: " + ((nota1>=4 && nota2>=4)&&(nota1<7||nota2<7)));
		System.out.println("Desaprobo: " + (nota1<4 || nota2<4));
		teclado.close();
	}
}
	
