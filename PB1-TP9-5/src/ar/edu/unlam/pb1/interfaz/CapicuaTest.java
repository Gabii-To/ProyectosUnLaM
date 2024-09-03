package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

public class CapicuaTest {
	public static void main(String[]args) {
		Scanner teclado= new Scanner(System.in);
		System.out.println("Ingrese un numero: ");
		String cadena = teclado.nextLine();
		char[] array = cadena.toCharArray();
		
		for(int i=0;i<cadena.length();i++) {
			System.out.println(array[i]);
		}
		
		
		teclado.close();
		
		}
}
