package consultas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class MedidasCons {

    private Connection conexion;
    private ResultSet rs;
    private CallableStatement call;
    private String columnas[][];
    private static MedidasCons Medidas;

    private MedidasCons() {
        super();
        this.conexion = Conexion.getConexion();
        this.rs = null;
        this.call = null;
    }

    public static MedidasCons getMedidas() {
        if (Medidas == null) {

            Medidas = new MedidasCons();

        }
        return Medidas;
    }

    public void registar_actualizar(String cedula, String nombres, String apellidos, Date fecha, float sueldo,
            String telefono, int genero, String direccion, int opcion) {

        try {

            this.call = this.conexion.prepareCall("{call INSERTAR_ACTUALIZA_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?)}");
            this.call.setString(1, cedula);
            this.call.setString(2, nombres);
            this.call.setString(3, apellidos);
            this.call.setDate(4, fecha);
            this.call.setFloat(5, sueldo);
            this.call.setString(6, telefono);
            this.call.setInt(7, genero);
            this.call.setString(8, direccion);
            this.call.setInt(9, 1);
            this.call.setInt(10, 1);
            this.call.setInt(11, 1);
            this.call.setInt(12, opcion);
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

            this.call = this.conexion.prepareCall("{call  BUSCAR_MEDIDA(?,?)}");
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