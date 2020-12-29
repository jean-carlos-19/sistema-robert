package errores;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import componentes.Dialogo;

public class ErrorClases {

    private static final String Servidor = "S001";
    private static final long serialVersionUID = 700L;
    private static final String Nombre_Empleado = "E001";
    private static final String Longitud_Nombre = "E002";
    private static final String Apellido_Empleado = "E003";
    private static final String Longitud_Apellido = "E004";
    private static final String Fecha_Empleado = "E005";
    private static final String Genero_Fehca = "E006";
    private static final String Direccion_Empleado = "E007";
    private static final String Longitud_Direccion = "E008";
    private static final String Cedula_Empleado = "E008";
    private static ErrorClases ErrorClases;
    private ImageIcon error = new ImageIcon("src//main//java//img//eliminar.png");
    private ImageIcon comprobar = new ImageIcon("src//main//java//img//comprobar.png");
    private JLabel contenedor;

    private ErrorClases() {

        this.error.setImage(this.error.getImage().getScaledInstance(50, 50, 100));
        this.comprobar.setImage(this.comprobar.getImage().getScaledInstance(50, 50, 100));
    }

    public static ErrorClases getErrorClases() {
        if (ErrorClases == null)
            ErrorClases = new ErrorClases();
        return ErrorClases;
    }

    private void mensaje(int id, String mensaje) {

        if (id == 1)
            JOptionPane.showMessageDialog(null, mensaje.toUpperCase(), "mensaje".toUpperCase(), 2, this.error);
        if (id == 2)
            JOptionPane.showMessageDialog(null, mensaje.toUpperCase(), "mensaje".toUpperCase(), 2, this.comprobar);
    }

    public void servidor() {
        this.mensaje(1,
                "Mensaje de Advertencia: El servidor se encuentra apagado. A continuacion se cerrara el programa codigo de la advertencia ("
                        + Servidor + ")");
    }

    public void Nombre() {
        this.mensaje(1, "Mensaje de Advertencia: El campo nombre esta vacio");
    }

    public void LongitudNombre() {
        this.mensaje(1, "Mensaje de Advertencia: La longitud del campo Nombre solo permite hasta 50 caracteres ("
                + Longitud_Nombre + ")");
    }

    public void Apellido() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Apellido esta vacio (" + Apellido_Empleado + ")");
    }

    public void LongitudApellido() {
        this.mensaje(1, "Mensaje de Advertencia: La longitud del campo Apellido solo permite hasta 50 caracteres ("
                + Longitud_Apellido + ")");
    }

    public void Fecha() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Fecha esta vacio (" + Fecha_Empleado + ")");
    }

    public void Longitud_Fecha() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Fecha esta vacio (" + Fecha_Empleado + ")");
    }

    public void Genero() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Genero esta vacio (" + Genero_Fehca + ")");
    }

    public void Direccion() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Direccion esta vacio (" + Direccion_Empleado + ")");
    }

    public void LongitudDireccion() {
        this.mensaje(1, "Mensaje de Advertencia: La longitud del campo Direccion solo permite hasta 255 caracteres ("
                + Longitud_Direccion + ")");
    }

    public void Sueldo() {
        this.mensaje(1,
                "Mensaje de Advertencia: El Campo Sueldo es un numero negativo 0,-1 (" + Longitud_Direccion + ")");
    }

    public void Telefono() {
        this.mensaje(1, "Mensaje de Advertencia: El Campo Telefono esta vacio");
    }

    public void Longitud_Telefono() {
        this.mensaje(1, "Mensaje de Advertencia: El numero de telefono que acaba de ingresar no es valido");
    }

    public void Cargo() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Cargo esta vacio ");
    }

    public void LongitudCargo() {
        this.mensaje(1, "Mensaje de Advertencia: La longitud del campo Cargo solo permite hasta 50 caracteresa ");
    }

    public void nombreCategoria() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Nombre de la categoria esta vacio ");
    }

    public void LongitudNombreCategoria() {
        this.mensaje(1,
                "Mensaje de Advertencia: La longitud del campo Nombre de la categoria solo permite hasta 50 caracteresa ");
    }

    public void DescripcionCategoria() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Descripcion esta vacio ");
    }

    public void LongitudDescripcionCategoria() {
        this.mensaje(1,
                "Mensaje de Advertencia: La longitud del campo Descripcion solo permite hasta 255 caracteresa ");
    }

    public void NumeroFactura() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Numero Factura esta vacio ");
    }

    public void Producto() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Producto esta vacio ");
    }

    public void LongitudProducto() {
        this.mensaje(1, "Mensaje de Advertencia: La longitud del campo Producto solo permite hasta 255 caracteresa ");
    }

    public void Cantidad() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Cantidad es menor que cero ");
    }

    public void Pvp() {
        this.mensaje(1, "Mensaje de Advertencia: El campo PVP es menor que cero ");
    }

    public void Total() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Total es menor que cero ");
    }

    public void Iva() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Iva es menor que cero ");
    }

    public void Cedula_Empleado() {
        this.mensaje(1, "Mensaje de Advertencia: El campo Cedula esta vacio ");
    }

    public void Longitud_Cedula_Empleado() {
        this.mensaje(1, "Mensaje de Advertencia: La cedula que usted ingreso es incorrecta ");
    }

    public void Ruc() {
        this.mensaje(1, "Mensaje de Advertencia: La cedula que usted ingreso es incorrecta ");
    }

    public void Ruc_Longitud() {
        this.mensaje(1, "Mensaje de Advertencia: La cedula que usted ingreso es incorrecta ");
    }

    public void getSesion() {
        this.mensaje(1, "Mensaje de Advertencia: el usuario no existe. Por favor ingrese bien su usuario y password");
    }

    public void getVenta() {
        this.mensaje(2, "Mensaje de confirmación: la venta se realizo correctamente");
    }

    public void getCategoria() {
        this.mensaje(2, "Mensaje de confirmación: la categoria se registro correctamente");
    }

    public void getMarca() {
        this.mensaje(2, "Mensaje de confirmación: la marca se registro correctamente");
    }

    public void eliminarMarca() {
        this.mensaje(2, "Mensaje de confirmación: la marca se elimino correctamente");
    }

    public void eliminarCategoria() {
        this.mensaje(2, "Mensaje de confirmación: la categoria se elimino correctamente");
    }

    public void actualizarCategoria() {
        this.mensaje(2, "Mensaje de confirmación: la categoria se actualizo correctamente");
    }

    public void actualizaMarca() {
        this.mensaje(2, "Mensaje de confirmación: la marca se actualizo correctamente");
    }

    public void eliminarEmpleado() {
        this.mensaje(2, "Mensaje de confirmación: el empleado se elimino correctamente");
    }

    public void getEmpleado() {
        this.mensaje(2, "Mensaje de confirmación: el empleado se registro correctamente");
    }

    public void actualizarEmpleado() {
        this.mensaje(2, "Mensaje de confirmación: el empleado se actualizo correctamente");
    }

    public void eliminarProveedor() {
        this.mensaje(2, "Mensaje de confirmación: el proveedor se elimino correctamente");
    }

    public void getProveedor() {
        this.mensaje(2, "Mensaje de confirmación: el proveedor se registro correctamente");
    }

    public void actualizarProveedor() {
        this.mensaje(2, "Mensaje de confirmación: el proveedor se actualizo correctamente");
    }

    public void eliminarCuenta() {
        this.mensaje(2, "Mensaje de confirmación: la cuenta se elimino correctamente");
    }

    public void getCuenta() {
        this.mensaje(2, "Mensaje de confirmación: la cuenta se registro correctamente");
    }

    public void actualizarCuenta() {
        this.mensaje(2, "Mensaje de confirmación: la cuenta se actualizo correctamente");
    }

    public void password() {
        this.mensaje(1, "Mensaje de Advertencia: la contraseña es muy larga o esta vacio");
    }

    public void usuario() {
        this.mensaje(1, "Mensaje de Advertencia: el usuario no existe");
    }

    public void ProductoNoExiste() {
        this.mensaje(1, "Mensaje de Advertencia: Producto no existe");
    }

    public void ErrorIngreso() {
        this.mensaje(1, "Mensaje de Advertencia: la cantidad que ingreso no es un numero");
    }

    public void ErrorCantidad() {
        this.mensaje(1, "Mensaje de Advertencia: El ingreso es menor que el total de la compra");
    }

    public void CantidadNegativa() {
        this.mensaje(1, "Mensaje de Advertencia:ingreso una cantidad no valida");
    }

    public void ingresarDinero() {
        this.mensaje(2, "Mensaje de confirmación se realizo correctamente el ingreso de dinero");
    }

    public void retirarDinero() {
        this.mensaje(2, "Mensaje de confirmación se realizo correctamente el retiro de dinero");
    }

    public void actualizarProducto() {
        this.mensaje(2, "Mensaje de confirmación actualizo correctamente el producto");
    }

    public void getProducto() {
        this.mensaje(2, "Mensaje de confirmación se registro el producto correctamnte");
    }

    public void eliminarProducto() {
        this.mensaje(2, "Mensaje de confirmación se elimino el producto correctamnte");
    }

    public void CodigoProducto() {
        this.mensaje(1, "Mensaje de advertencia: el campo codigo esta vacio");
    }

    public void LimiteProducto() {
        this.mensaje(1, "Mensaje de advertencia: el campo codigo supero los 15 caracteres");
    }

    public void NombreProducto() {
        this.mensaje(1, "Mensaje de advertencia: el campo nombre esta vacio");
    }

    public void LimiteNombreProducto() {
        this.mensaje(1, "Mensaje de advertencia: el campo nombre supero los 50 caracteres");
    }

    public void ErrorPesoProducto() {
        this.mensaje(1, "Mensaje de advertencia: el peso que usted ingreso es incorrecto");
    }

    public void ErrorPrecioProducto() {
        this.mensaje(1, "Mensaje de advertencia: el precio que usted ingreso es incorrecto");
    }

    public void NoexisteProveedor() {
        this.mensaje(1, "Mensaje de advertencia: No existe un proveedor con este ruc. :C");
    }

    public void CompraExito() {
        this.mensaje(2, "Mensaje de confirmación: Compra realiazada con exito");
    }

    public void eliminarCompra() {
        this.mensaje(2, "Mensaje de confirmación: se elimino la compra correctamente");
    }

	public void ErrorCantidadProducto() {
		// TODO Auto-generated method stub
		this.mensaje(1, "Mensaje de advertencia: la cantidad que usted ingreso es incorrecto");
	}

	public void NoExisteProductosEnLaTabla() {
		// TODO Auto-generated method stub
		this.mensaje(1, "Mensaje de advertencia: no existen productos en la tabla");
	}
}