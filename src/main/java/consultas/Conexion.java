package consultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import errores.ErrorClases;;
public class Conexion {
    
	private String usuario = "root";
	private String contrasena = "";
	private String direccion = "jdbc:mysql://localhost:3306/factura?useSSL=false";
	private static Connection db;
	private static Conexion conecta;
	private ErrorClases error =  ErrorClases.getErrorClases();

	private Conexion() {
		try {
			
			db = DriverManager.getConnection(this.direccion,this.usuario,this.contrasena);
			
		}catch(SQLException e) {
			
			error.servidor();
			System.exit(0);
		}
	}
	
	public static Connection getConexion() {
		 if ( conecta==null) {
			 
			 conecta = new Conexion();
			
		}
			 return conecta.Conexion();
	}
	public Connection Conexion() {
		return db;
	}
}