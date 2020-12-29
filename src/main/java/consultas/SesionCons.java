package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class SesionCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static SesionCons SesionCons;

	private SesionCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static SesionCons getSesionCons() {
		if (SesionCons == null)
			SesionCons = new SesionCons();
		return SesionCons;
	}

	public void entrar() {

	}

	public void Salir() {

	}
}
