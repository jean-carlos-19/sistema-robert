package procesos;

public class RetirarDinero implements EmpleadoFabrica {

	private String cedulaEmpleado;
	private String caja;
	private float ingreso;
	private float retiro;
	private String dinero;

	public RetirarDinero(String cedulaEmpleado, String caja, float ingreso, float retiro) {

		this.cedulaEmpleado = cedulaEmpleado;
		this.caja = caja;
		this.ingreso = ingreso;
		this.retiro = retiro;
	}

	public RetirarDinero(String dinero) {
		this.dinero = dinero;
	}

	public RetirarDinero() {

	}

	public boolean validar() {
		if (this.cedulaEmpleado.equalsIgnoreCase("")) {
			if (this.cedulaEmpleado.length() > 10 || this.cedulaEmpleado.length() <= 0) {

			}
			return false;
		}
		if (this.caja.equalsIgnoreCase("")) {

			return false;
		}
		if (this.ingreso <= 0) {
			return false;
		}
		if (this.retiro <= 0) {
			return false;
		}
		return true;
	}

	public boolean validar2() {

		if (this.dinero.length() <= 0) {

			if (this.dinero.equalsIgnoreCase("")) {

			}
			if (this.esNumero(this.dinero)) {

			}
			return false;
		}
		return true;

	}

	public boolean esNumero(String numero) {

		try {
			Double.parseDouble(numero);
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public String getCedulaEmpleado() {
		return cedulaEmpleado;
	}

	public void setCedulaEmpleado(String cedulaEmpleado) {
		this.cedulaEmpleado = cedulaEmpleado;
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

	public float getRetiro() {
		return retiro;
	}

	public void setRetiro(float retiro) {
		this.retiro = retiro;
	}
}