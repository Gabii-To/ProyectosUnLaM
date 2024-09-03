package dominio;

public class ListaDeSospechas {
	
	
	private Sospecha[] listaDeSospechas;
	private static int proximoId= 0;
	


	public ListaDeSospechas(int cantidaDeSospechas) {
		this.listaDeSospechas = new Sospecha[cantidaDeSospechas];
		
	}

	public static int getId() {
		return proximoId;
	}
	
	public static int obtenerProximoId() {
		return ++proximoId;
	}

	public Sospecha[] getListaDeSospechas() {
		return listaDeSospechas;
	}



	public void setListaDeSospechas(Sospecha[] listaDeSospechas) {
		this.listaDeSospechas = listaDeSospechas;
	}



	public boolean agregarSospecha(Sospecha sospecha) {
		boolean agregada= false;
		int posicion = 0;
		
		while(posicion<this.listaDeSospechas.length && !agregada) {
			if (this.listaDeSospechas[posicion] == null) {
				 this.listaDeSospechas[posicion] = sospecha;
				agregada = true;
			}
			posicion++;
		}
		
		
		return agregada;
		
		
	}

	public void mostrarSospechas() {
		for (int i = 0; i < this.listaDeSospechas.length; i++) {
			if(this.listaDeSospechas[i] != null) {
				System.out.println(this.listaDeSospechas[i]);
			}
		}
	}
	
	public Sospecha[] obtenerSospechasOrdenadas() {
		Sospecha [] ordenado = this.listaDeSospechas;
		Sospecha temp;
		for(int i=1;i<ordenado.length;i++) {
			for(int j=0;j<ordenado.length-1;j++) {
				if(ordenado[j]!= null&&ordenado[j+1]!= null) {
					if(ordenado[j].getSospechoso().compareTo(ordenado[j+1].getSospechoso())<0) {
						temp = ordenado[j];
						ordenado[j] = ordenado[j+1];
						ordenado[j+1] = temp;
					}
				}
			}
			
		}
		
		return ordenado;
	}



	public boolean sospechaEliminada(Sospecha sospecha) {
		boolean eliminada = false;
		int posicion = 0;
		
		while(eliminada == false && posicion <this.listaDeSospechas.length) {
			if(this.listaDeSospechas[posicion]!=null && this.listaDeSospechas[posicion].equals(sospecha)) {
				this.listaDeSospechas[posicion] = null;	
				eliminada = true;
			}
			
			posicion++;
		}
		return eliminada;
	}

	public Sospecha buscarPorId(int id) {
		Sospecha sospecha = null;
		boolean encontrado = false;
		int posicion = 0;
		while(posicion<this.listaDeSospechas.length && !encontrado) {
			if(this.listaDeSospechas[posicion]!=null && this.listaDeSospechas[posicion].getId()== id) {
				sospecha = this.listaDeSospechas[posicion];
				encontrado = true;
			}
			posicion++;
		}
		return sospecha;
	}
	
	
}
