package procesos;

public class FacturaVenta implements EmpleadoFabrica {

	private String CedulaEmpleado;
	private String CedulaCliente;
	private String caja;
	private float ingreso;
	private float vuelto;

	public FacturaVenta(String cedulaEmpleado, String cedulaCliente, String caja, float ingreso, float vuelto) {
		super();
		CedulaEmpleado = cedulaEmpleado;
		CedulaCliente = cedulaCliente;
		this.caja = caja;
		this.ingreso = ingreso;
		this.vuelto = vuelto;
	}

	public boolean validar() {

		if (this.CedulaCliente.equalsIgnoreCase("")) {

			if (this.CedulaCliente.length() == 0 || this.CedulaCliente.length() > 10) {

			}
			return false;
		}
		if (this.CedulaEmpleado.equalsIgnoreCase("")) {

			if (this.CedulaEmpleado.length() == 0 || this.CedulaEmpleado.length() > 10) {

			}
			return false;
		}
		if (this.caja.equalsIgnoreCase("")) {

			return false;
		}
		if (this.ingreso <= 0) {

			return false;
		}
		if (this.vuelto <= 0) {

			return false;
		}

		return true;
	}

	public String getCedulaEmpleado() {
		return CedulaEmpleado;
	}

	public void setCedulaEmpleado(String cedulaEmpleado) {
		CedulaEmpleado = cedulaEmpleado;
	}

	public String getCedulaCliente() {
		return CedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		CedulaCliente = cedulaCliente;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public float getIngreso() {
		return ingreso;
	}

	public void setIngreso(float ingreso) {
		this.ingreso = ingreso;
	}

	public float getVuelto() {
		return vuelto;
	}

	public void setVuelto(float vuelto) {
		this.vuelto = vuelto;
	}

}
