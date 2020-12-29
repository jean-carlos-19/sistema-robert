package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static MarcaCons MarcaCons;

	private MarcaCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static MarcaCons getMarcaCons() {

		if (MarcaCons == null)
			MarcaCons = new MarcaCons();
		return MarcaCons;
	}

	public void registar_actualizar(String codigo, String nombre, String descripcion, int eliminado, int opcion) {
		try {

			this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_MARCA(?,?,?,?,?)}");
			this.call.setString(1, codigo);
			this.call.setString(2, nombre);
			this.call.setString(3, descripcion);
			this.call.setInt(4, eliminado);
			this.call.setInt(5, opcion);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminar(String marca) {
		try {

			this.call = this.conexion.prepareCall("{call ELIMINAR_MARCA(?)}");
			this.call.setString(1, marca);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] buscar(int codigo, String buscar) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_MARCA(?,?)}");
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