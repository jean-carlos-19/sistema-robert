package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;

public class PestanaTiendaGui extends JTabbedPane {

    private Dimension tamano;

    public PestanaTiendaGui(Dimension d) {
        super(JTabbedPane.BOTTOM);
        this.tamano = d;
        this.pestanas();
    }

    private PestanaTiendaGui pestanas() {
        this.removeAll();
        this.add("registrar".toUpperCase(), this.laminas(1, 0));
        this.add("actulizar".toUpperCase().toUpperCase(), this.laminas(2, 1));
        this.add("eliminar".toUpperCase(), this.laminas(2, 2));
        this.updateUI();
        return this;
    }

    public PestanaTiendaGui pestanasMarca() {
        this.removeAll();
        this.add("registrar".toUpperCase(), this.laminas(5, 0));
        this.add("actulizar".toUpperCase().toUpperCase(), this.laminas(3, 1));
        this.add("eliminar".toUpperCase(), this.laminas(3, 2));
        this.updateUI();
        return this;
    }

    public PestanaTiendaGui pestanasCategoria() {
        this.removeAll();
        this.add("registrar".toUpperCase(), this.laminas(6, 0));
        this.add("actulizar".toUpperCase().toUpperCase(), this.laminas(4, 1));
        this.add("eliminar".toUpperCase(), this.laminas(4, 2));
        this.updateUI();
        return this;
    }

    private JPanel laminas(int id, int opcion) {
        switch (id) {
            case 1:
                return new ProductoInsertarGui(this.tamano, 1);
            case 2:
                return new ProductoAEGui(this.tamano, opcion);
            case 3:
                return new MarcaAEGui(this.tamano, opcion);
            case 4:
                return new CategoriaAEGui(this.tamano, opcion);
            case 5:
                return new MarcaInsertarGui(this.tamano, 1);
            case 6:
                return new CategoriaInsertarGui(this.tamano, 1);

            default:
                break;
        }
        return null;
    }
}