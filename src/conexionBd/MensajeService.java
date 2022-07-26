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
		MensajeDAO.leerMensaje();
	}
	
	public static void borrarMensaje() {
		Scanner in = new Scanner(System.in);	
		System.out.println("Escribe el id del mensaje a borrar");
		int id = in.nextInt();
		MensajeDAO.borrarMensaje(id);
	}
	
	public static void editarMensaje() {
		Scanner in = new Scanner(System.in);	
		System.out.println("Escribe el nuevo mensaje");
		String mensaje = in.nextLine();
		System.out.println("Escribe el id del mensaje");
		int id = in.nextInt();
		Mensaje actualizacion = new Mensaje();
		actualizacion.setId_mensaje(id);
		actualizacion.setMensaje(mensaje);
		MensajeDAO.actualizarMensaje(actualizacion);
		
	}
	
}
