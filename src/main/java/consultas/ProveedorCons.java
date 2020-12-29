package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static ProveedorCons ProveedorCons;

	private ProveedorCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
	}

	public static ProveedorCons getProveedorCons() {

		if (ProveedorCons == null)
			ProveedorCons = new ProveedorCons();
		return ProveedorCons;
	}

	public void registar_actualizar(String rucA, String ruc, String nombre, String telefono, String direccion,
			int eliminado, int opcion) {
		try {

			this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_PROVEEDOR(?,?,?,?,?,?,?)}");
			this.call.setString(1, rucA);
			this.call.setString(2, ruc);
			this.call.setString(3, nombre);
			this.call.setString(4, telefono);
			this.call.setString(5, direccion);
			this.call.setInt(6, eliminado);
			this.call.setInt(7, opcion);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void eliminar(String ruc) {
		try {

			this.call = this.conexion.prepareCall("{call ELIMINAR_PROVEEDOR(?)}");
			this.call.setString(1, ruc);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public String[][] buscar(int codigo, String buscar) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_PROVEEDOR(?,?)}");
			this.call.setInt(1, codigo);
			this.call.setString(2, buscar);
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