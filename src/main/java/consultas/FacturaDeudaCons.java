package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class FacturaDeudaCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private Object columnas[];
	private static FacturaDeudaCons FacturaDeudaCons;

	public FacturaDeudaCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static FacturaDeudaCons getFacturaDeudaCons() {
		if (FacturaDeudaCons == null)
			FacturaDeudaCons = new FacturaDeudaCons();
		return FacturaDeudaCons;
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