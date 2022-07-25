package conexionBd;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexionBd {

	public Connection get_conecction() {
		Connection conection = null;
		try{
			conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javasepersistencia", "root", "");
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return conection;
	}

}
