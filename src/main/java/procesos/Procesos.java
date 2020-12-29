package procesos;

public class Procesos {

    private Categoria Categoria;
    private Cliente Cliente;
    private Detalle Detalle;
    private DetalleCompra DetalleCompra;
    private Empleado Empleado;
    private FacturaCompra FacturaCompra;
    private FacturaDeuda FacturaDeuda;
    private FacturaVenta FacturaVenta;
    private Marca Marca;
    private Proveedor Proveedor;
    private RetirarDinero RetirarDinero;
    private Usuario Usuario;
    private static Procesos Procesos;

    private Procesos() {

    }

    public static Procesos getProcesos() {
        if (Procesos == null)
            Procesos = new Procesos();
        return Procesos;
    }

    public Categoria getCategoria(String nombre, String descripcion) {
        return new Categoria(nombre, descripcion);
    }

    public Cliente getCliente(String cedula, String nombres, String apellidos, String fecha, String direccion,
            String genero, String telefono) {
        return new Cliente(cedula, nombres, apellidos, fecha, direccion, genero, telefono);
    }

    public Detalle getDetalle(String numFactura, String producto, int cantidad, double pvp, double total) {
        return new Detalle(numFactura, producto, cantidad, pvp, total);
    }

    public DetalleCompra getDetalleCompra(String producto, int cantidad, int unidad, double pvp, double total,
            String iva, double descuento) {
        return new DetalleCompra(producto, cantidad, unidad, pvp, total, iva, descuento);
    }

    public Empleado getEmpleado(String cedula, String nombres, String apellidos, String fecha, String sueldo,
            String telefono, String genero, String cargo, String direccion) {
        return new Empleado(cedula, nombres, apellidos, fecha, sueldo, telefono, genero, cargo, direccion);
    }

    public FacturaCompra getFacturaCompra(String ruc) {
        return new FacturaCompra(ruc);
    }

    public FacturaDeuda getFacturaDeuda(String cedulaCliente, String cedulaEmpleado, float abono) {
        return new FacturaDeuda(cedulaCliente, cedulaEmpleado, abono);
    }

    public FacturaVenta getFacturaVenta(String cedulaEmpleado, String cedulaCliente, String caja, float ingreso,
            float vuelto) {
        return new FacturaVenta(cedulaEmpleado, cedulaCliente, caja, ingreso, vuelto);
    }

    public Marca getMarca(String nombre, String descripcion) {
        return new Marca(nombre, descripcion);
    }

    public Proveedor getProveedor(String ruc, String nombre, String telefono, String direccion) {
        return new Proveedor(ruc, nombre, telefono, direccion);
    }

    public RetirarDinero getRetirarDinero(String cedulaEmpleado, String caja, float ingreso, float retiro) {
        return new RetirarDinero(cedulaEmpleado, caja, ingreso, retiro);
    }

    public Usuario getUsuario(String cedulaEmpleado, String password, String usuario) {
        return new Usuario(cedulaEmpleado, password, usuario);
    }

    public Producto getProductos(String codigo, String nombre, String peso, String medida, String marca,
            String categoria, String precio,String cantidad) {
        return new Producto(codigo, nombre, peso, medida, marca, categoria, precio,cantidad);
    }

    public Login getLogin() {
        return Login.getLogin();
    }
}
