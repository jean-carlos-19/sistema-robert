package procesos;

public abstract class Persona {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String fecha;
    private String genero;
    private String telefono;

    public Persona(String cedula, String nombres, String apellidos, String fecha, String direccion, String genero,
            String telefono) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getTelefono() {
        return this.telefono;
    }
}