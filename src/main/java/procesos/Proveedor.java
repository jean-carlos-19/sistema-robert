package procesos;

import errores.ErrorClases;

public class Proveedor implements EmpleadoFabrica {

	private String ruc;
	private String nombre;
	private String telefono;
	private String direccion;
	private ErrorClases Error = ErrorClases.getErrorClases();

	public Proveedor(String ruc, String nombre, String telefono, String direccion) {

		this.ruc = ruc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public boolean validar() {
		if (this.ruc.length() != 13 || this.ruc.length() == 0) {

			if (this.ruc.equalsIgnoreCase("")) {
				this.Error.Ruc();
			} else {
				this.Error.Ruc_Longitud();
			}
			return false;
		}
		if (this.nombre.length() > 50 || this.nombre.length() == 0) {
			if (this.nombre.equalsIgnoreCase("")) {
				this.Error.Nombre();
			} else {
				this.Error.LongitudNombre();
			}
			return false;
		}
		if (this.telefono.length() != 10 || this.telefono.length() == 0) {
			if (this.telefono.equalsIgnoreCase("")) {
				this.Error.Telefono();
			} else {
				this.Error.Longitud_Telefono();
			}
			return false;
		}
		if (this.direccion.length() > 255 || this.direccion.length() == 0) {
			if (this.direccion.equalsIgnoreCase("")) {
				this.Error.Direccion();
			} else {
				this.Error.LongitudDireccion();
			}
			return false;
		}
		return true;
	}

	public Proveedor() {
		super();
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String gettelefono() {
		return telefono;
	}

	public void settelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}