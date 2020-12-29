package procesos;

import errores.ErrorClases;

public class Cliente extends Persona implements EmpleadoFabrica {

	private ErrorClases Error = ErrorClases.getErrorClases();

	public Cliente(String cedula, String nombres, String apellidos, String fecha, String direccion, String genero,String telefono) {
		super(cedula, nombres, apellidos, fecha, direccion, genero,telefono);
	}

	public boolean validar() {

		if (this.getNombres().equalsIgnoreCase("")) {

			if (this.getNombres().length() > 50) {
				Error.LongitudNombre();
			} else {
				Error.Nombre();
			}
			return false;

		}
		if (this.getApellidos().equalsIgnoreCase("")) {

			if (this.getApellidos().length() > 50) {
				Error.Apellido();
			} else {
				Error.LongitudApellido();
			}
			return false;
		}
		if (this.getFecha().equalsIgnoreCase("")) {

			Error.Fecha();

			return false;
		}
		if (this.getDireccion().equalsIgnoreCase("")) {

			if (this.getDireccion().length() > 255) {
				Error.Direccion();
			} else {
				Error.LongitudDireccion();
			}
			return false;
		}

		return true;
	}

}