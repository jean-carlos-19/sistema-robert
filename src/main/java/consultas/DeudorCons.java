package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DeudorCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static DeudorCons DeudorCons;

	private DeudorCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static DeudorCons getDeudorCons() {
		if (DeudorCons == null)
			DeudorCons = new DeudorCons();
		return DeudorCons;
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