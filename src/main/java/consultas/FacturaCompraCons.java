package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class FacturaCompraCons  {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static FacturaCompraCons FacturaCompraCons;

	private FacturaCompraCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static FacturaCompraCons getFacturaCompraCons() {
		if (FacturaCompraCons == null)
			FacturaCompraCons = new FacturaCompraCons();
		return FacturaCompraCons;
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