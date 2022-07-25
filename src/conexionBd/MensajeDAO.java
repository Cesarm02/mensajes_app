package conexionBd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class MensajeDAO {

	public static void crearMensajeBD(Mensaje mensaje) {
		ConexionBd db_connect = new ConexionBd();
		try(Connection conexion = db_connect.get_conecction()){
			PreparedStatement ps = null;
			try{
				String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?, ?)";
				ps = conexion.prepareStatement(query);
				ps.setString(1, mensaje.getMensaje());
				ps.setString(2, mensaje.getAutor_mensaje());
				ps.executeUpdate();
				System.out.println("El mensaje fue creado correctamente");
			}catch(SQLException e) {
				System.out.println(e);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void leerMensaje() {
		
	}
	
	public static void borrarMensaje(int id) {
		
	}
	
	public static void actualizarMensaje(Mensaje mensaje) {
		
	}
	
}
