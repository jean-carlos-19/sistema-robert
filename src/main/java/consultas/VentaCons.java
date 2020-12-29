package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import procesos.Detalle;
import procesos.ListaVentas;
import procesos.Fecha;
public class VentaCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static VentaCons VentaCons;
	private Fecha Fecha;

	private VentaCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
		this.Fecha = new Fecha();
	}

	public static VentaCons getVentaCons() {
		if (VentaCons == null)
			VentaCons = new VentaCons();
		return VentaCons;
	}

	public String[][] buscar(int codigo, String dato) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_TOTAL_VENTAS(?,?)}");
			this.call.setInt(1, codigo);
			this.call.setString(2, dato);
			this.rs = this.call.executeQuery();

			int fila = 0, columna = this.rs.getMetaData().getColumnCount();

			while (this.rs.next()) {

				int j = 0;
				while (j < columna) {
					
					if(j==1) {
						nombreArrayList.add(this.Fecha.mes(this.rs.getInt(j + 1)));
					}else {
						nombreArrayList.add(this.rs.getString(j + 1));
					}
					j++;
				}
				fila++;

			}

			this.columnas = new String[fila][columna];
			int k = 0;
			for (int i = 0; i < columnas.length; i++) {
				for (int j = 0; j < columnas[0].length; j++) {
					this.columnas[i][j] = nombreArrayList.get(k);
					k++;
				}
			}

		} catch (SQLException e) {
			return null;
		}
		return this.columnas;
	}

	public String factura(String empleado, double ingreso, double vuelto) {
		try {

			this.call = this.conexion.prepareCall("{call  INSRTAR_FACTURA_VENTA(?,?,?)}");

			this.call.setString(1, empleado);
			this.call.setDouble(2, ingreso);
			this.call.setDouble(3, vuelto);
			this.rs = this.call.executeQuery();

			if (this.rs.next()) {
				return String.valueOf(this.rs.getInt(1));
			}
		} catch (SQLException e) {

			return null;
		}
		return null;
	}

	public void detalle(String factura, ListaVentas ListarVenta) {

		try {

			ListarVenta.inicilizaAux();
			while (ListarVenta.getAux() != null) {

				Detalle d = ListarVenta.getCompras();
				this.call = this.conexion.prepareCall("{call  INSRTAR_DETALLE_VENTA(?,?,?,?,?)}");
				this.call.setString(1, factura);
				this.call.setString(2, d.getNumFactura());
				this.call.setInt(3, d.getCantidad());
				this.call.setDouble(4, d.getPvp());
				this.call.setDouble(5, d.getTotal());
				this.rs = this.call.executeQuery();
			}

		} catch (SQLException e) {
			return;
		}
	}
	public String[][] busqueda(int codigo, String dato) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_TOTAL_VENTAS(?,?)}");
			this.call.setInt(1, codigo);
			this.call.setString(2, dato);
			this.rs = this.call.executeQuery();

			int fila = 0, columna = this.rs.getMetaData().getColumnCount();

			while (this.rs.next()) {

				int j = 0;
				while (j < columna) {
					
					nombreArrayList.add(this.rs.getString(j + 1));
					j++;
				}
				fila++;

			}

			this.columnas = new String[fila][columna];
			int k = 0;
			for (int i = 0; i < columnas.length; i++) {
				for (int j = 0; j < columnas[0].length; j++) {
					this.columnas[i][j] = nombreArrayList.get(k);
					k++;
				}
			}

		} catch (SQLException e) {
			return null;
		}
		return this.columnas;
	}
	
	public String[][] historial(int opcion,String fechaInicial, String fechaFinal) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  historialSemanal(?,?,?)}");
			this.call.setInt(1, opcion);
			this.call.setString(2, fechaInicial);
			this.call.setString(3, fechaFinal);
			this.rs = this.call.executeQuery();

			int fila = 0, columna = this.rs.getMetaData().getColumnCount();

			while (this.rs.next()) {

				int j = 0;
				while (j < columna) {
					
					nombreArrayList.add(this.rs.getString(j + 1));
					j++;
				}
				fila++;

			}

			this.columnas = new String[fila][columna];
			int k = 0;
			for (int i = 0; i < columnas.length; i++) {
				for (int j = 0; j < columnas[0].length; j++) {
					this.columnas[i][j] = nombreArrayList.get(k);
					k++;
				}
			}

		} catch (SQLException e) {
			return null;
		}
		return this.columnas;
	}

}