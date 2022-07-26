package conexionBd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				ps.executeUpdate(); //ejecuta la consulta
				System.out.println("El mensaje fue creado correctamente");
			}catch(SQLException e) {
				System.out.println(e);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void leerMensaje() {
		ConexionBd db_connect = new ConexionBd();
		PreparedStatement ps = null; // busquedas 
		ResultSet rs = null; //Permite ordenar los datos
		
		try(Connection conexion = db_connect.get_conecction()){
			String query = "Select * from mensajes";
			ps= conexion.prepareStatement(query);
			rs = ps.executeQuery(); //Ejecuta el query
			
			while(rs.next()) {
				System.out.println("Id: " + rs.getInt("id_mensaje") );
				System.out.println("Mensaje: " + rs.getString("mensaje"));
				System.out.println("Autor: " + rs.getString("autor_mensaje"));
				System.out.println("Fecha: " + rs.getString("fecha_mensaje"));
				System.out.println("\n");
			}
			
		}catch(SQLException e) {
			System.out.println("No se pudo recuperar los mensajes");
			System.out.println(e);
		}
	}
	
	public static void borrarMensaje(int id) {
		ConexionBd db_connect = new ConexionBd();
		try(Connection conexion = db_connect.get_conecction()){
			PreparedStatement ps = null; // busquedas
			try {
				String query = "DELETE FROM mensajes where id_mensaje= ?";
				ps = conexion.prepareStatement(query);
				ps.setInt(1, id);
				ps.executeUpdate(); // Operación  de transacción.
				System.out.println("Mensaje borrado exitosamente");
			}catch(SQLException ex) {
				System.out.println("No se pudo eliminar el mensaje");
				System.out.println(ex);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void actualizarMensaje(Mensaje mensaje) {
		ConexionBd db_connect = new ConexionBd();
		try(Connection conexion = db_connect.get_conecction()){
			PreparedStatement ps = null; // busquedas
			try {
				String query = "UPDATE mensajes SET mensaje = ? where id_mensaje= ?";
				ps = conexion.prepareStatement(query);
				ps.setString(1, mensaje.getMensaje());
				ps.setInt(2, mensaje.getId_mensaje());
				ps.executeUpdate(); // Operación  de transacción.
				System.out.println("Mensaje actualizado exitosamente");
			}catch(SQLException ex) {
				System.out.println("No se pudo actualizar el mensaje");
				System.out.println(ex);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
}
