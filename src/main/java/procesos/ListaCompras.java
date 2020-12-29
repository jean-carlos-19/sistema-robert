package procesos;

import procesos.DetalleCompra;

public class ListaCompras {

	private nodo inicio, ultimo, aux;

	private double total;
	private int tamano;

	public ListaCompras() {

		this.inicio = null;
		this.ultimo = null;
		this.aux = null;
		this.tamano = 0;
	}

	public int getTamano() {
		return this.tamano;
	}

	public double total() {

		this.aux = this.inicio;

		this.total = 0;
		while (this.aux != null) {
			this.total += this.aux.getCompas().getTotal();
			this.aux = this.aux.getApuntador();
		}
		return this.total;
	}

	public boolean esVacia() {
		return this.inicio == null;
	}

	public boolean existeProducto(String codigo) {
		this.aux = this.inicio;

		while (this.aux != null) {

			if (this.aux.getCompas().getProducto().equalsIgnoreCase(codigo)) {

				this.aux.getCompas().incrementarCantidad();
				return false;
			}
			this.aux = this.aux.getApuntador();

		}
		return true;
	}

	public void insertar(DetalleCompra compras) {

		this.aux = new nodo(compras);

		compras.calcularTotal();

		this.aux = new nodo(compras);
		if (this.esVacia()) {
			this.inicio = this.aux;
			this.ultimo = this.aux;

		} else {
			this.ultimo.setApuntador(this.aux);
			this.ultimo = this.aux;
		}
		this.tamano++;

	}

	public int tamano() {
		return this.tamano;
	}

	public nodo getInicio() {
		return this.inicio;
	}

	public void vaciar() {
		this.inicio = null;
		this.ultimo = null;
		this.tamano = 0;
	}

	public void eliminar(String codigo) {

		this.aux = this.inicio;
		nodo ayuda = null;
		while (this.aux != null) {

			if (this.inicio.getCompas().getProducto().equalsIgnoreCase(codigo)) {
				this.inicio = this.inicio.getApuntador();
				this.tamano--;
				return;
			}
			if (this.aux.getCompas().getProducto().equalsIgnoreCase(codigo)) {
				ayuda.setApuntador(this.aux.getApuntador());
				this.tamano--;
				return;
			}
			ayuda = this.aux;
			this.aux = this.aux.getApuntador();

		}
	}

	public void inicilizaAux() {
		this.aux = this.inicio;
	}

	public nodo getAux() {
		return this.aux;
	}

	public DetalleCompra getCompras() {

		DetalleCompra c = null;

		if (this.aux != null) {

			c = this.aux.getCompas();
			this.aux = this.aux.getApuntador();
			return c;
		}
		return null;
	}
	public void listar() {
		
		this.aux = this.inicio;
		DetalleCompra c = null;
		if (this.aux != null) {

			c = this.aux.getCompas();
			System.out.println(c.getProducto());
			this.aux = this.aux.getApuntador();
		}
		
		
	}

	private class nodo {

		private nodo apuntador;
		private DetalleCompra compas;

		public nodo(DetalleCompra c) {

			this.apuntador = null;
			this.compas = c;
		}

		public nodo getApuntador() {
			return apuntador;
		}

		public void setApuntador(nodo apuntador) {
			this.apuntador = apuntador;
		}

		public DetalleCompra getCompas() {
			return compas;
		}

		public void setCompas(DetalleCompra compas) {
			this.compas = compas;
		}
	}

}
