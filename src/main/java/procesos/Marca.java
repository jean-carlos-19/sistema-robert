package procesos;

import errores.ErrorClases;

public class Marca implements EmpleadoFabrica {

	private String nombre;
	private String descripcion;
	private ErrorClases Error = ErrorClases.getErrorClases();

	public Marca(String nombre, String descripcion) {

		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public boolean validar() {

		if (this.nombre.equalsIgnoreCase("")) {
			if (this.nombre.length() > 50) {
				Error.LongitudNombreCategoria();

			} else {
				Error.nombreCategoria();
			}
			return false;
		}
		if (this.descripcion.equalsIgnoreCase("")) {
			if (this.descripcion.length() > 255) {
				Error.DescripcionCategoria();
			} else {
				Error.LongitudDescripcionCategoria();
			}
			return false;
		}
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public Marca() {
		super();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}