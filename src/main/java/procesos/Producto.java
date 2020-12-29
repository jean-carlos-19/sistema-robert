package procesos;

import errores.ErrorClases;

public class Producto {
    private String codigo;
    private String nombre;
    private String peso;
    private String medida;
    private String marca;
    private String categoria;
    private String precio;
    private String cantidad;

    public Producto(String codigo, String nombre, String peso, String medida, String marca, String categoria,
            String precio,String cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.peso = peso;
        this.medida = medida;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public boolean validar() {

        System.out.println(this.getNombre().length() > 50 || this.getNombre().length() == 0);
        if (this.getCodigo().length() > 15 || this.getCodigo().length() == 0) {
            if (this.getCodigo().equalsIgnoreCase("")) {
                ErrorClases.getErrorClases().CodigoProducto();
            } else {
                ErrorClases.getErrorClases().LimiteProducto();
            }
            return false;
        }
        if (this.getNombre().length() > 50 || this.getNombre().length() == 0) {
            if (this.getNombre().equalsIgnoreCase("")) {
                ErrorClases.getErrorClases().NombreProducto();
            } else {
                ErrorClases.getErrorClases().LimiteNombreProducto();
            }
            return false;
        }
        if (comprobarNumero2(this.getPeso())) {

            ErrorClases.getErrorClases().ErrorPesoProducto();
            return false;
        }
        if (comprobarNumero(this.getCantidad())) {

            ErrorClases.getErrorClases().ErrorCantidadProducto();
            return false;
        }
        if (comprobarNumero2(this.getPrecio())) {
            ErrorClases.getErrorClases().ErrorPrecioProducto();
            return false;
        }

        return true;
    }

    private boolean comprobarNumero(String numero) {

        try {
            Integer.valueOf(numero);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private boolean comprobarNumero2(String numero) {

        try {
            Double.valueOf(numero);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
