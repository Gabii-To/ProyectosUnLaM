package casita;

import java.util.Scanner;
public class TestEnum {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Enum opcion = null;
		do {
			int a = teclado.nextInt();
			if (a>=0&&a<=5) {
			opcion = Enum.values()[a-1];
			}
			switch (opcion) {
		case UNO: 
			System.out.println("1");
			break;
		case DOS: 
			System.out.println("2");
			break;
		case TRES: 
			System.out.println("3");
			break;
		case CATRO: 
			System.out.println("4");
			break;
		default:
			System.out.println("GIL");
			break;
		}}while(opcion!=Enum.SALIR);
		teclado.close();
	}

}
