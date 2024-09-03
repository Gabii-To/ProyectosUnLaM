package casita;

public class TestOrdenamientoBurbuja {
	public static void main(String[]args) {
		int array[] = new int[5];
		
		array[0] = 12;
		array[1] = 32;
		array[2] = 6;
		array[4] = 3;
		System.out.println("El contenido de array es:");
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
		System.out.println("El contenido de array ordenado es:");
		for(int i=1;i<array.length;i++) {
			for(int j=0;j<array.length-1;j++) {
				if(array[j]>array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
	}
}
