package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ClienteCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];

	public ClienteCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
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