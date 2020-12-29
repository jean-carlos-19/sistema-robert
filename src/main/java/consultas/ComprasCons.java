package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import procesos.Fecha;

public class ComprasCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private static ComprasCons ComprasCons;
	private String columnas[][];
	private Fecha Fecha;

	private ComprasCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
		this.Fecha = new Fecha();
	}

	public static ComprasCons getComprasCons() {
		if (ComprasCons == null)
			ComprasCons = new ComprasCons();
		return ComprasCons;
	}

	public String factura(String proveedor) {
		try {

			this.call = this.conexion.prepareCall("{call  INSERTARR_FACTURA_COMPRA(?)}");

			this.call.setString(1, proveedor);
			this.rs = this.call.executeQuery();

			if (this.rs.next()) {
				return String.valueOf(this.rs.getInt(1));
			}
		} catch (SQLException e) {

			return null;
		}
		return null;
	}

	public void detalle(String factura, String pruducto, int cantidad, int unidad, double precio, int iva,
			int descuento) {

		try {

			this.call = this.conexion.prepareCall("{call  INSERTAR_DETALLE_COMPRA(?,?,?,?,?,?,?)}");
			this.call.setString(1, factura);
			this.call.setString(2, pruducto);
			this.call.setInt(3, cantidad);
			this.call.setDouble(4, unidad);
			this.call.setDouble(5, precio);
			this.call.setDouble(6, iva);
			this.call.setDouble(7, descuento);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			return;
		}
	}

	public void eliminar(String codigo) {
		try {

			this.call = this.conexion.prepareCall("{call ELIMINAR_FACTURA_COMPRA(?)}");
			this.call.setString(1, codigo);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {

		}
	}

	public String[][] buscar(int codigo, String dato) {
		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_TOTAL_COMPRAS(?,?)}");
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
			
			System.out.println(e.getMessage());
			return null;
		}
		return this.columnas;
	}

	public String[][] busqueda(int codigo, String dato) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_TOTAL_COMPRAS(?,?)}");
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
			
			System.out.println(e.getMessage());
			return null;
		}
		return this.columnas;
	}

}