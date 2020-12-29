package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static UsuarioCons UsuarioCons;

	public UsuarioCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
	}

	public static UsuarioCons getUsuarioCons() {

		if (UsuarioCons == null)
			UsuarioCons = new UsuarioCons();
		return UsuarioCons;
	}

	public void registar_actualizar(String usuario, String contrasena, String empleado, int habilitado, int opcion) {
		try {

			this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_USUARIO(?,?,?,?,?)}");
			this.call.setString(1, usuario);
			this.call.setString(2, contrasena);
			this.call.setString(3, empleado);
			this.call.setInt(4, habilitado);
			this.call.setInt(5, opcion);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminar(String usuario) {
		try {

			this.call = this.conexion.prepareCall("{call ELIMINAR_USUARIO(?)}");
			this.call.setString(1, usuario);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] buscar(int codigo, String buscar) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_USUARIO(?,?)}");
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

	public String[][] sesion(String usuario, String contrasena) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_SESION(?,?)}");
			this.call.setString(1, usuario);
			this.call.setString(2, contrasena);
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

	public void Cerrar_sesion(String cedula) {
		try {

			this.call = this.conexion.prepareCall("{call  CERRAR_SESION(?)}");
			this.call.setString(1, cedula);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {

		}

	}

}