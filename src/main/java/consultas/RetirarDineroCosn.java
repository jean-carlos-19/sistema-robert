package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import procesos.Fecha;

public class RetirarDineroCosn {

	private Connection conexion;
	private ResultSet rs;
	private CallableStatement call;
	private String columnas[][];
	private Fecha Fecha;
	private static RetirarDineroCosn RetirarDineroCosn;

	private RetirarDineroCosn() {
		super();
		this.conexion = Conexion.getConexion();
		this.rs = null;
		this.call = null;
		this.columnas = null;
		this.Fecha = new Fecha();
	}

	public static RetirarDineroCosn getRetirarDineroCosn() {

		if (RetirarDineroCosn == null)
			RetirarDineroCosn = new RetirarDineroCosn();
		return RetirarDineroCosn;
	}

	public void registar(String empleado, int caja, String retirar, String ingreso) {
		try {

			this.call = this.conexion.prepareCall("{call INSERTAR_RETIRARDINERO(?,?,?,?)}");
			this.call.setString(1, empleado);
			this.call.setInt(2, caja);
			this.call.setString(3, retirar);
			this.call.setString(4, ingreso);
			this.rs = this.call.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] buscar(int codigo, String buscar, String mes) {

		ArrayList<String> nombreArrayList = new ArrayList<String>();

		try {

			this.call = this.conexion.prepareCall("{call  BUSCAR_RETIRO_DINERO(?,?,?)}");
			this.call.setInt(1, codigo);
			this.call.setString(2, buscar);
			this.call.setString(3, mes);
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

}