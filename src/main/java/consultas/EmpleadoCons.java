package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class EmpleadoCons {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private static EmpleadoCons EmpleadoCons;

	private EmpleadoCons() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
	}

	public static EmpleadoCons getEmpleadoCons() {
		if (EmpleadoCons == null) {

			EmpleadoCons = new EmpleadoCons();

		}
		return EmpleadoCons;
	}

	public void registar_actualizar(String cedulaA, String cedula, String nombres, String apellidos, String fecha,
			String sueldo, String telefono, String genero, String direccion, int opcion) {

		try {
			int sexo = 1;
			if (genero.equalsIgnoreCase("f")) {
				sexo = 2;
			} else {
				sexo = 1;
			}
			this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			this.call.setString(1, cedulaA);
			this.call.setString(2, cedula);
			this.call.setString(3, nombres);
			this.call.setString(4, apellidos);
			this.call.setString(5, fecha);
			this.call.setString(6, sueldo);
			this.call.setString(7, telefono);
			this.call.setInt(8, sexo);
			this.call.setString(9, direccion);
			this.call.setString(10, "2");
			this.call.setString(11, "1");
			this.call.setString(12, "1");
			this.call.setInt(13, opcion);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminar(String cedula) {

		try {

			this.call = this.conexion.prepareCall("{call ELIMINAR_EMPLEADO(?)}");
			this.call.setString(1, cedula);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {

		}
	}

	public String[][] buscar(int codigo, String buscar) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_EMPLEADO(?,?)}");
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