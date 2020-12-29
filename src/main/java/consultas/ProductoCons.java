package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static ProductoCons ProductoCons;

	private ProductoCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static ProductoCons getProductoCons() {

		if (ProductoCons == null)
			ProductoCons = new ProductoCons();
		return ProductoCons;
	}

	public void registar_actualizar(String codigoa, String codigo, String nombre, String peso, String medida,
			String marca, String categoria, String precio,String cantidad, int opcion) {

		try {

			this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_PRODUCTOS(?,?,?,?,?,?,?,?,?,?,?)}");
			this.call.setString(1, codigoa);
			this.call.setString(2, codigo);
			this.call.setString(3, nombre);
			this.call.setString(4, medida);
			this.call.setString(5, peso);
			this.call.setString(6, precio);
			this.call.setString(7, marca);
			this.call.setString(8, categoria);
			this.call.setString(9, cantidad);
			this.call.setInt(10, 2);
			this.call.setInt(11, opcion);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] buscar(int codigo, String buscar) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_PRODUCTO(?,?)}");
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

	public void eliminar(String codigo) {
		try {

			this.call = this.conexion.prepareCall("{call  ELIMINAR_PRODUCTO(?)}");
			this.call.setString(1, codigo);

			this.rs = this.call.executeQuery();
		} catch (Exception e) {

		}
	}
}