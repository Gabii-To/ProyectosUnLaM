package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePintura;

public class LataDePintura {
	
	// TODO: Completar getters, setters, constructor y metodos necesarios para garantizar el correcto funcionamiento.

	private final double PRECIO_BASE = 1000;

	private int codigo;
	private String nombre;
	private TipoDePintura tipoDePintura;
	private int stock;
	private double porcentajeDeGanancia;

	public LataDePintura(int codigo, String nombre, double porcentajeDeGanancia, TipoDePintura tipoDePintura,int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipoDePintura = tipoDePintura;
		this.porcentajeDeGanancia = porcentajeDeGanancia;
		this.stock = stock;
	}

	public double obtenerPrecio() {
		// TODO: Calcular y obtener el precio de la lata de pintura, el cual se calcula
		// segun su TipoDePintura.
		// Todas las latas de pinturas son blancas y tienen un precio base. En caso de
		// ser satinadas, tonalizar la pintura blanca cuesta un 15% extra. En cambio,
		// tonalizar las pinturas mate, cuesta un 5%, pero incluye otro 3% de impuestos
		// (calculado sobre el precio base) que se debe agregar al precio final. No
		// olvidar agregar el porcentaje de ganancia, tambien calculado sobre el precio
		// base.
		
		double precio = 0;
		if(this.tipoDePintura.equals(TipoDePintura.SATINADA))
			precio = PRECIO_BASE * (1.15 + this.porcentajeDeGanancia);
		else if(this.tipoDePintura.equals(TipoDePintura.MATE))
			precio = PRECIO_BASE * (1.08 + this.porcentajeDeGanancia);
		return precio;
	}

	public double getPRECIO_BASE() {
		return PRECIO_BASE;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoDePintura getTipoDePintura() {
		return tipoDePintura;
	}

	public int getStock() {
		return stock;
	}

	public double getPorcentajeDeGanancia() {
		return porcentajeDeGanancia;
	}

}
