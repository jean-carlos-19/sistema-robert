package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DetalleCompraCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static DetalleCompraCons DetalleCompraCons;

	public DetalleCompraCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static DetalleCompraCons getDetalleCompraCons() {
		if (DetalleCompraCons == null)
			DetalleCompraCons = new DetalleCompraCons();
		return DetalleCompraCons;
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