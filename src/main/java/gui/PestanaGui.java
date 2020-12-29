package gui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PestanaGui extends JTabbedPane implements MouseListener{

    private Dimension tamano;

    public PestanaGui(Dimension d, int id, int i) {

        this.tamano = d;
        this.pestanas(id, i);
        this.addMouseListener(this);
    }

    private PestanaGui pestanas(int id, int i) {
        this.removeAll();
        // ,21,20
        this.add("registrar".toUpperCase(), this.laminas(i, 0));
        this.add("actulizar".toUpperCase().toUpperCase(), this.laminas(id, 1));
        this.add("eliminar".toUpperCase(), this.laminas(id, 2));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasCompras(int id, int i) {
        this.removeAll();
        // ,21,20
        this.add("registrar".toUpperCase(), this.laminas(i, 0));
        this.add("eliminar".toUpperCase(), this.laminas(id, 2));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasTienda() {
        this.removeAll();
        this.add("producto".toUpperCase(), new PestanaTiendaGui(this.tamano));
        this.add("categoria".toUpperCase().toUpperCase(), new PestanaTiendaGui(this.tamano).pestanasCategoria());
        this.add("marca".toUpperCase(), new PestanaTiendaGui(this.tamano).pestanasMarca());
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasInicioVenta() {
        this.removeAll();
        this.add("ventas".toUpperCase(), this.laminas(2, 0));
        this.add("historial".toUpperCase(), this.laminas(3, 3));
        this.add("semanal".toUpperCase(), this.laminas(34, 3));
        this.add("estadistica".toUpperCase(), this.laminas(22, 0));
        this.updateUI();
        return this;
    }
    
    public PestanaGui pestanasBodega() {
    	this.removeAll();
        this.add("productos mas vendidos".toUpperCase(), this.laminas(32, 0));
    	this.add("productos".toUpperCase(), this.laminas(1, 0));
        this.add("comprar".toUpperCase(), this.laminas(31, 0));
        this.add("organizar".toUpperCase(), this.laminas(35, 0));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasInicioMovimiento() {
        this.removeAll();
        this.add("movimiento".toUpperCase(), this.laminas(25, 0));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasHistorialMovimiento() {
        this.removeAll();
        this.add("historial".toUpperCase(), this.laminas(24, 3));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasPagoDeuda() {
        this.removeAll();
        this.add("pago".toUpperCase(), this.laminas(27, 3));
        this.add("deuda".toUpperCase(), this.laminas(28, 3));
        this.add("cancelado".toUpperCase(), this.laminas(29, 3));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasHistorialDeuda() {
        this.removeAll();
        this.add("historial".toUpperCase(), this.laminas(26, 3));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasInicioBodega() {
        this.removeAll();
        this.add("bodega".toUpperCase(), this.laminas(1, 0));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasCompras() {
        this.removeAll();
        this.add("compras".toUpperCase(), this.laminas(5, 3));
        this.add("historial - anual- compras".toUpperCase(), this.laminas(6, 3));
        this.add("historial - anual- proveedores".toUpperCase(), this.laminas(30, 3));
        this.add("estadistica".toUpperCase(), this.laminas(23, 0));
        
        this.updateUI();
        return this;
    }
    
    public PestanaGui pestanasCompras2() {
        this.removeAll();
        this.add("compras".toUpperCase(), this.laminas(5, 3));
        
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasCaja() {
        this.removeAll();
        this.add("caja".toUpperCase(), this.laminas(11, 0));
        this.add("historial".toUpperCase(), this.laminas(12, 0));
        this.updateUI();
        return this;
    }

    public PestanaGui pestanasHistorial(int id) {
        this.removeAll();
        this.add("historial".toUpperCase(), this.laminas(id, 0));
        this.updateUI();
        return this;
    }

    public JPanel laminas(int id, int opcion) {
        switch (id) {
            case 1:
                return new HistorialProductoGui(this.tamano,1);
            case 2:
                return new VentasGui(this.tamano);
            case 3:
                return new HistorialVentasGui(this.tamano, opcion);
            case 4:
                return new ReporteVentasGui(this.tamano);
            case 5:
                return new ComprasGui(this.tamano, opcion);
            case 6:
                return new HistorialComprasGui(this.tamano, opcion);
            case 7:
                return new ReporteComprasGui(this.tamano);
            case 8:
                return new HistorialEmpleadoGui(this.tamano);
            case 9:
                return new HistorialProveedorGui(this.tamano);
            case 10:
                return new HistorialCuentaGui(this.tamano);
            case 11:
                return new CajaGui(this.tamano);
            case 12:
                return new HistorialCajaGui(this.tamano);
            case 13:
                return new EmpleadoAEGui(this.tamano, opcion);
            case 14:
                return new ProveedorAEGui(this.tamano, opcion);
            case 15:
                return new CuentaAEGui(this.tamano, opcion);
            case 16:
                return new HistorialCajaGui(this.tamano);
            case 17:
                return new EmpleadoInsertarGui(this.tamano, 1);
            case 18:
                return new ProveedorInsertarGui(this.tamano, 1);

            case 19:
                return new CuentaInsertarGui(this.tamano, 1);
            case 20:
                return new ComprasInsertarGui(this.tamano);
            case 21:
                return new CompraAEGui(this.tamano, opcion);
            case 22:
                return new GraficoVenta(this.tamano);
            case 23:
                return new GraficoCompras(this.tamano);
            case 24:
                return new MovimientoGui(this.tamano, opcion);
            case 25:
                return new MovimientoHistorialGui(this.tamano);
            case 26:
                return new PagoDeudaGui(this.tamano, opcion);
            case 27:
                return new PendienteDeudaGui(this.tamano, opcion);
            case 28:
                return new DeudaCanceladaGui(this.tamano, opcion);
            case 29:
                return new HistorialDeudaGui(this.tamano, opcion);
            case 30:
                return new ComprasAnualesProveedoresGui(this.tamano, opcion);
            case 31:
                return new BodegaGui(this.tamano);
            case 32:
                return new HistorialProductosMasVendidos(this.tamano,opcion);
            case 33:
            	return new ProductosPendientesGui(this.tamano,opcion);
            case 34:
            	return new HistorialSemanalGui(this.tamano);
            case 35:
            	return new OrganizarBodea(this.tamano);
            default:
                break;
        }
        return null;
    }

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}