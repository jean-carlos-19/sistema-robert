package procesos;

import errores.ErrorClases;

public class Usuario implements EmpleadoFabrica {

	private String cedulaEmpleado;
	private String password;
	private String usuario;

	public Usuario(String cedulaEmpleado, String password, String usuario) {

		this.cedulaEmpleado = cedulaEmpleado;
		this.password = password;
		this.usuario = usuario;
	}

	public boolean validar() {

		if (this.password.equalsIgnoreCase("")) {
			if (this.password.length() > 10 || this.password.length() <= 0) {
				ErrorClases.getErrorClases().password();
			}
			return false;
		}
		if (this.usuario.equalsIgnoreCase("")) {
			if (this.cedulaEmpleado.length() > 10 || this.cedulaEmpleado.length() <= 0) {
				ErrorClases.getErrorClases().usuario();
			}
			return false;
		}
		return true;
	}

	public String getCedulaEmpleado() {
		return cedulaEmpleado;
	}

	public void setCedulaEmpleado(String cedulaEmpleado) {
		this.cedulaEmpleado = cedulaEmpleado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}