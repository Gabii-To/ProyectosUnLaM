package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.Materiales;

public class CopaDelMundo {

	private int id;
	private Materiales material;
	private double precio;
	private int stock;

	// TODO: Completar constructor/es, getters, setters y otros metodos que
	// considere necesarios.

	public CopaDelMundo(int id, Materiales material, double precio, int stock) {
		this.id = id;
		this.material = material;
		this.precio = precio;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Materiales getMaterial() {
		return material;
	}

	public void setMaterial(Materiales material) {
		this.material = material;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "CopaDelMundo [id=" + id + ", material=" + material + ", precio=" + precio + ", stock=" + stock + "]";
	}

}
