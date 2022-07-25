package conexionBd;

import java.util.Scanner;

public class MensajeService {

	public static void crearMensaje() {
		Scanner in = new Scanner(System.in);
		System.out.println("Escribe tu mensaje");
		String mensaje = in.nextLine();
		System.out.println("Escribe el autor");
		String autor = in.nextLine();
		
		Mensaje registro = new Mensaje();
		registro.setMensaje(mensaje);
		registro.setAutor_mensaje(autor);

		MensajeDAO.crearMensajeBD(registro);
		
	}
	
	public static void listarMensaje() {
		
	}
	
	public static void borrarMensaje() {
		
	}
	
	public static void editarMensaje() {
		
	}
	
}
