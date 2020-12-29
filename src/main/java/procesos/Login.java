package procesos;

import consultas.Consultas;

public class Login {

    private String cedula;
    private static Login Login;

    private Login() {
    	String dato[][] = this.getSesion();
    	if(dato.length > 0) {
    		 this.cedula = this.getSesion()[0][0];
    	}
       
    }

    public static Login getLogin() {
        if (Login == null)
            Login = new Login();
        return Login;
    }

    public static void setLogin() {
        Login = null;
    }

    public String getCedula() {
        return this.cedula;
    }

    private String[][] getSesion() {
        return Consultas.getConsultas().getUsuarioCons().buscar(6, null);
    }

}
