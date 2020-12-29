package procesos;

public class FacturaDeuda implements EmpleadoFabrica {

	private String cedulaCliente;
	private String cedulaEmpleado;
	private float abono;

	public FacturaDeuda(String cedulaCliente, String cedulaEmpleado, float abono) {
		super();
		this.cedulaCliente = cedulaCliente;
		this.cedulaEmpleado = cedulaEmpleado;
		this.abono = abono;
	}

	public boolean validar() {

		if (this.cedulaCliente.equalsIgnoreCase("")) {

			if (this.cedulaCliente.length() > 10 || this.cedulaCliente.length() == 0) {

			} else {

			}
			return false;
		}
		if (this.cedulaEmpleado.equalsIgnoreCase("")) {

			if (this.cedulaEmpleado.length() > 10 || this.cedulaEmpleado.length() == 0) {

			} else {

			}
			return false;
		}
		if (this.abono < 0) {
			return false;
		}
		return true;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getCedulaEmpleado() {
		return cedulaEmpleado;
	}

	public void setCedulaEmpleado(String cedulaEmpleado) {
		this.cedulaEmpleado = cedulaEmpleado;
	}

	public float getAbono() {
		return abono;
	}

	public void setAbono(float abono) {
		this.abono = abono;
	}

}
