package gui;

import java.awt.Dimension;
import componentes.Ventana;

public class Marco extends Ventana {

	private static Marco gui;

	private PanelesGui panel;

	private Marco() {
		super();
		this.panel = PanelesGui.getPanelesGui(this.getTamano());
		this.panel.agregaPanel(1);
		this.setPanel(this.panel);
	}

	public Dimension getResolucion() {
		return this.getTamano();
	}

	public static Marco getGUI() {

		if (gui == null) {
			gui = new Marco();
		}

		return gui;
	}
}