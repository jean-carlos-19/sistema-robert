package jeancarlos.ProyectoFactura;

import gui.Marco;
import java.net.ServerSocket;

public class App {

	private static ServerSocket server_socket;

	public static void main(String[] args) {

		try {
			server_socket = new ServerSocket(3008);
			Marco grafica = Marco.getGUI();
			grafica.setVisible(true);

		} catch (Exception e) {

			System.out.print(e.getMessage());
			System.exit(0);
		}
	}
}
