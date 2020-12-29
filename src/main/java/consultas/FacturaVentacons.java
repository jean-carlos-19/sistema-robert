package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class FacturaVentacons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static FacturaVentacons FacturaVentacons;

	private FacturaVentacons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static FacturaVentacons getFacturaVentacons() {
		if (FacturaVentacons == null)
			FacturaVentacons = new FacturaVentacons();
		return FacturaVentacons;
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