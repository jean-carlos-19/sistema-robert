
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class PanelesGui extends JPanel {

	private JPanel aux;
	private Dimension resolucion;
	private static PanelesGui PanelesGui;

	private PanelesGui(Dimension resolucion) {

		this.setLayout(new BorderLayout());
		this.resolucion = resolucion;
		this.setSize(this.resolucion);
	}

	public static PanelesGui getPanelesGui(Dimension resolucion) {
		if (PanelesGui == null)
			PanelesGui = new PanelesGui(resolucion);
		return PanelesGui;
	}

	public void agregaPanel(int id) {

		switch (id) {

			case 1:

				this.aux = new LoginGui(this.resolucion, this);
				break;

			case 2:

				this.aux = new AdministradorGui(this.resolucion, this);
				break;

			case 3:
				this.aux = new AdministradorFacturaVentaGui(this.resolucion, this);
				break;

		}

		this.add(this.aux, BorderLayout.CENTER);
		this.updateUI();
	}
}
