package gatos_app;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GatoService {

	public static void verGatos() {
		//1. Ver gatos api
		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url("https://api.thecatapi.com/v1/images/search")
				.get()
				.build();
		try {
			Response response = client.newCall(request).execute();
			//Convertir en string
			String json = response.body().string();
			
			//Cortar corchetes innecesarios
			json = json.substring(1, json.length());
			json = json.substring(0, json.length()-1);
			
			//Objeto Gson
			Gson gson = new Gson();
			Gato gato = gson.fromJson(json, Gato.class);
			
			//Dimensionar imagen 800px largo
			Image img = null;
			try {
				URL url = new URL(gato.getUrl());
				img = ImageIO.read(url);
				
				ImageIcon fondoGato = new ImageIcon(img);
				
				if(fondoGato.getIconWidth() > 800) {
					//Redimensionar
					Image fondo = fondoGato.getImage();
					//Guia renderizado java
					Image render = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
					fondoGato = new ImageIcon(render);
				}
			
			String menu = "Opciones: \n "
					+ "1. Ver otra imagen "
					+ "\n 2. Marcar gato como favorito "
					+ "\n 3. Volver Menú";
			
			String[] botones = {
					" 1. Ver otra imagen",
					" 2. Favorito",
					" 3. Salir"
			};
			
			String id_gato = String.valueOf(gato.getUrl());
			String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, 
					fondoGato, botones, botones[0]);
			
			int seleccion = -1;
			
			//Validar la opcion tomada
			for(int i = 0; i < botones.length; i++) {
				if(opcion.equals(botones[i])) {
					seleccion = i;
				}
			}
			
			switch(seleccion) {
				case 0:
					verGatos();
					break;
				case 1:
					favoritoGato(gato);
					break;
				default:
					break;
			}
			
			
			}catch(IOException ex) {
				System.out.println(ex);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void favoritoGato(Gato gato) {
		
	}
	
}
