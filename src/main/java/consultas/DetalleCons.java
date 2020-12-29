package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DetalleCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static DetalleCons DetalleCons;

	private DetalleCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static DetalleCons getDetalleCons() {
		if (DetalleCons == null)
			DetalleCons = new DetalleCons();
		return DetalleCons;
	}

	public void registar() {

	}

	public void actualizar() {

	}

	public void eliminar() {

	}

	public void buscar() {

	}

	public void busqueda(String dato) {

	}
}