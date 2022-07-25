package conexionBd;

import java.sql.Connection;
import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int opcion = 0;
		
		do {
			System.out.println("-------------");
			System.out.println("Aplicación de mensajes");
			System.out.println("1. Crear Mensaje");
			System.out.println("2. Listar Mensajes");
			System.out.println("3. Editar Mensaje");
			System.out.println("4. Eliminar Mensaje");
			System.out.println("5. Salir");
			//leer opción
			opcion = in.nextInt();
			switch (opcion) {
			case 1: {
				MensajeService.crearMensaje();
			}
			case 2:{
				MensajeService.listarMensaje();
			}
			case 3:{
				MensajeService.editarMensaje();
			}
			case 4:{
				MensajeService.borrarMensaje();
			}
			default:
				break;
			}
			
		}while(opcion!=5);
		
		
	}

}
