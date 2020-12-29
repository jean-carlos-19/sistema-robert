package procesos;

import errores.ErrorClases;

public class Detalle implements EmpleadoFabrica {

	private String numFactura;
	private String producto;
	private int cantidad;
	private double pvp;
	private double total;
	private ErrorClases Error = ErrorClases.getErrorClases();

	public Detalle(String numFactura, String producto, int cantidad, double pvp, double total) {

		this.numFactura = numFactura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.pvp = pvp;
		this.total = total;
	}

	public boolean validar() {
		if (this.numFactura.equalsIgnoreCase("")) {
			Error.NumeroFactura();
		}
		if (this.producto.equalsIgnoreCase("")) {
			if (this.producto.length() > 50) {
				Error.LongitudProducto();
			} else {
				Error.Producto();
			}
			return false;
		}
		if (this.cantidad <= 0) {
			Error.Cantidad();
			return false;
		}

		return true;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void incrementarCantidad() {
		this.cantidad++;
		this.calcularTotal();
	}

	public void calcularTotal() {
		this.total = this.cantidad * this.pvp;
	}

}