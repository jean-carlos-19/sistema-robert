package procesos;

import errores.ErrorClases;

public class DetalleCompra implements EmpleadoFabrica {

	private String producto;
	private int cantidad;
	private int unidad;
	private double pvp;
	private double total;
	private String iva;
	private double descuento;
	private ErrorClases Error = ErrorClases.getErrorClases();

	public DetalleCompra(String producto, int cantidad, int unidad, double pvp, double total, String iva,
			double descuento) {

		this.producto = producto;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.pvp = pvp;
		this.total = total;
		this.iva = iva;
		this.descuento = descuento;
	}

	public boolean validar() {

		if (this.producto.equalsIgnoreCase("")) {
			if (this.producto.length() > 50) {
				Error.Producto();
			} else {
				Error.LongitudProducto();
			}
			return false;
		}
		if (this.cantidad <= 0) {
			Error.Cantidad();
			return false;
		}
		if (this.pvp <= 0.00) {
			Error.Pvp();
			return false;
		}
		if (this.total <= 0.00) {
			Error.Total();
			return false;
		}

		return true;
	}

	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getUnidad() {
		return this.unidad;
	}

	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}

	public double getPvp() {
		return this.pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public double getTotal() {
		this.total =  this.cantidad * this.pvp;
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getIva() {
		return this.iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public double getDescuento() {
		return this.descuento;
	}

	public void getDescuento(double descuento) {
		this.descuento = descuento;
	}

	public void incrementarCantidad() {
		this.cantidad++;
		this.calcularTotal();
	}

	public void calcularTotal() {
		this.total = this.cantidad * this.pvp;
	}
}