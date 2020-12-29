package procesos;

import errores.ErrorClases;

public class Empleado extends Persona implements EmpleadoFabrica {

	private String sueldo;
	private ErrorClases Error = ErrorClases.getErrorClases();
	private String cargo;

	public Empleado(String cedula, String nombres, String apellidos, String fecha, String sueldo, String telefono,
			String genero, String cargo, String direccion) {

		super(cedula, nombres, apellidos, fecha, direccion, genero, telefono);
		this.sueldo = sueldo;
		this.cargo = cargo;
	}

	public Empleado(String cedula, String nombres, String apellidos, String fecha, String direccion, String genero,
			String telefono) {

		super(cedula, nombres, apellidos, fecha, direccion, genero, telefono);

	}

	public boolean validar() {

		if (this.getCedula().length() != 10) {
			if (this.getCedula().equalsIgnoreCase("")) {
				Error.Cedula_Empleado();
			} else {
				Error.Longitud_Cedula_Empleado();
			}
			return false;
		}

		if (this.getNombres().length() > 50 || this.getNombres().length() == 0) {

			if (this.getNombres().equalsIgnoreCase("")) {
				Error.Nombre();
			} else {
				Error.LongitudNombre();
			}
			return false;

		}
		if (this.getApellidos().length() > 50 || this.getApellidos().length() == 0) {

			if (this.getApellidos().equalsIgnoreCase("")) {
				Error.Apellido();
			} else {
				Error.LongitudApellido();

			}
			return false;
		}
		if (this.getFecha().length() != 10 || this.getFecha().length() == 0) {

			if (this.getFecha().equalsIgnoreCase("")) {
				Error.Fecha();
			} else {
				Error.Longitud_Fecha();
			}

			return false;
		}
		if (this.getDireccion().length() > 255 || this.getDireccion().length() == 0) {

			if (this.getDireccion().equalsIgnoreCase("")) {
				Error.Direccion();
			} else {
				Error.LongitudDireccion();
			}
			return false;
		}
		if (this.comprobarNumero(this.getSueldo())) {

			Error.Sueldo();
			return false;
		}
		if (this.getTelefono().length() != 10 || this.getTelefono().length() == 0) {

			if (this.getTelefono().equalsIgnoreCase("")) {
				Error.Telefono();
			} else {
				Error.Longitud_Telefono();
			}
			return false;
		}
		if (this.getcargos().length() > 50 || this.getcargos().length() == 0) {

			if (this.getcargos().equalsIgnoreCase("")) {
				Error.Cargo();
			} else {
				Error.LongitudCargo();
			}
			return false;
		}

		return true;
	}

	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}

	public void setcargos(String cargo) {
		this.cargo = cargo;
	}

	public String getSueldo() {
		return this.sueldo;
	}

	public String getcargos() {
		return this.cargo;
	}

	private boolean comprobarNumero(String numero) {

		try {
			Double.parseDouble(numero);
		} catch (Exception e) {
			return true;
		}
		return false;
	}
}