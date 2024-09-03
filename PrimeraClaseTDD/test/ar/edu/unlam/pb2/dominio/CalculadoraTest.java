package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculadoraTest {

	//sumar: dado 2 valores, debe devolver la suma de estos
	//restar: dado 2 valores, debe devolver la resta de estos
	//multiplicar: dado 2 valores, debe devolver la multiplicacion de estos
	//dividir: dado 2 valores, debe devolver la division de estos. Si el
	//denominador es cero, debe devolver cero.
	
	@Test
	public void Test() {
		//Preparacion: prerparamos la informacion para que el test cumpla con el
		//objetivo. Instanciar variables, inicializar objetos.
		
		//Ejecucion: invocacion al metodo qu estamos probando
		
		//validacion: verificamos que lo devuelto por el metodo testeado sea correcto
		//con respecto a lo que debe ser
		
		//Nota: Tesdt debe dar rojo inicialmente. Luego agregamos el codigo productivo
		//NECESARIO para que el test de verde. Como ultimo -> refactor
		
	}
	
	@Test
	public void calculadoraQueSuma3y4DaComoResultado7 () {
		//Preparacion: given - dado que
		Calculadora calculadora = new Calculadora();
		//Ejecucucion: when - cuando hago algo
		int valorObtenido = calculadora.sumar(3,4);
		//Validacion: then - entonces se cumplen todas las validaciones
		int valorEsperado = 7;
		assertEquals(valorEsperado, valorObtenido);
	}
}
