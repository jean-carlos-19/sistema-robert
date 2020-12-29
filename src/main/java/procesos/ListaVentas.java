package procesos;

public class ListaVentas {

    private nodo inicio, ultimo, aux;

    private double total;
    private int tamano;

    public ListaVentas() {

        this.inicio = null;
        this.ultimo = null;
        this.aux = null;
        this.tamano = 0;
    }

    public Double total() {
        this.aux = this.inicio;

        this.total = 0;
        while (this.aux != null) {
            this.total += this.aux.getCompas().getTotal();
            this.aux = this.aux.getApuntador();
        }
        return this.total;
    }

    public int getTamano() {
        return this.tamano;
    }

    public boolean esVacia() {
        return this.inicio == null;
    }

    public boolean existeProducto(String codigo) {
        this.aux = this.inicio;

        while (this.aux != null) {

            if (this.aux.getCompas().getNumFactura().equalsIgnoreCase(codigo)) {

                this.aux.getCompas().incrementarCantidad();
                return false;
            }
            this.aux = this.aux.getApuntador();

        }
        return true;
    }

    public void insertar(Detalle compras) {

        this.aux = new nodo(compras);

        if (this.existeProducto(compras.getNumFactura())) {

            compras.calcularTotal();

            this.aux = new nodo(compras);
            if (this.esVacia()) {
                this.inicio = this.aux;
                this.ultimo = this.aux;

            } else {
                this.ultimo.setApuntador(this.aux);
                this.ultimo = this.aux;
            }
            this.tamano++;
        }

    }

    public int tamano() {
        return this.tamano;
    }

    public void vaciar() {
        this.inicio = null;
        this.ultimo = null;
        this.tamano = 0;
    }

    public void eliminar(String codigo) {

        this.aux = this.inicio;
        nodo ayuda = null;
        while (this.aux != null) {

            if (this.inicio.getCompas().getNumFactura().equalsIgnoreCase(codigo)) {
                this.inicio = this.inicio.getApuntador();
                this.tamano--;
                return;
            }
            if (this.aux.getCompas().getNumFactura().equalsIgnoreCase(codigo)) {
                ayuda.setApuntador(this.aux.getApuntador());
                this.tamano--;
                return;
            }
            ayuda = this.aux;
            this.aux = this.aux.getApuntador();
        }
        
    }

    public void inicilizaAux() {
        this.aux = this.inicio;
    }

    public nodo getAux() {
        return this.aux;
    }

    public Detalle getCompras() {
        Detalle c = null;
        if (this.aux != null) {

            c = this.aux.getCompas();
            this.aux = this.aux.getApuntador();
            return c;
        }
        return null;
    }

    private class nodo {

        private nodo apuntador;
        private Detalle compas;

        public nodo(Detalle c) {

            this.apuntador = null;
            this.compas = c;
        }

        public nodo getApuntador() {
            return apuntador;
        }

        public void setApuntador(nodo apuntador) {
            this.apuntador = apuntador;
        }

        public Detalle getCompas() {
            return compas;
        }

        public void setCompas(Detalle compas) {
            this.compas = compas;
        }
    }

}
