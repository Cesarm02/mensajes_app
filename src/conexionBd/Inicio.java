package conexionBd;

import java.sql.Connection;

public class Inicio {

	public static void main(String[] args) {

		ConexionBd conexion = new ConexionBd();
		try (Connection cnx = conexion.get_conecction()){
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
