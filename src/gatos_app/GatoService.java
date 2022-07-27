package gatos_app;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GatoService {

	public static void verGatos() {
		// 1. Ver gatos api
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
		try {
			Response response = client.newCall(request).execute();
			// Convertir en string
			String json = response.body().string();

			// Cortar corchetes innecesarios
			json = json.substring(1, json.length());
			json = json.substring(0, json.length() - 1);

			// Objeto Gson
			Gson gson = new Gson();
			Gato gato = gson.fromJson(json, Gato.class);

			// Dimensionar imagen 800px largo
			Image img = null;
			try {
				URL url = new URL(gato.getUrl());
				img = ImageIO.read(url);

				ImageIcon fondoGato = new ImageIcon(img);

				if (fondoGato.getIconWidth() > 800) {
					// Redimensionar
					Image fondo = fondoGato.getImage();
					// Guia renderizado java
					Image render = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
					fondoGato = new ImageIcon(render);
				}

				String menu = "Opciones: \n " + "1. Ver otra imagen " + "\n 2. Marcar gato como favorito "
						+ "\n 3. Volver Menú";

				String[] botones = { " 1. Ver otra imagen", " 2. Favorito", " 3. Salir" };

				String id_gato = String.valueOf(gato.getUrl());
				String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,
						JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

				int seleccion = -1;

				// Validar la opcion tomada
				for (int i = 0; i < botones.length; i++) {
					if (opcion.equals(botones[i])) {
						seleccion = i;
					}
				}

				switch (seleccion) {
				case 0:
					verGatos();
					break;
				case 1:
					favoritoGato(gato);
					break;
				default:
					break;
				}

			} catch (IOException ex) {
				System.out.println(ex);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void favoritoGato(Gato gato) {
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\": \"" + gato.getId() + "\"\r\n}");
			Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites").method("POST", body)
					.addHeader("Content-Type", "application/json").addHeader("x-api-key", gato.getApikey()).build();
			Response response = client.newCall(request).execute();

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void verFavoritos(String api_key) throws IOException {

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites").get()
				.addHeader("Content-Type", "application/json").addHeader("x-api-key", api_key).build();
		Response response = client.newCall(request).execute();

		String json = response.body().string();
		Gson gson = new Gson();
		GatoFav[] gato_array = gson.fromJson(json, GatoFav[].class);

		if (gato_array.length > 0) {
			int min = 1;
			int max = gato_array.length;
			int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
			int indice = aleatorio - 1;

			GatoFav gatoFav = gato_array[indice];

			// Dimensionar imagen 800px largo
			Image img = null;
			try {
				URL url = new URL(gatoFav.image.getUrl());
				img = ImageIO.read(url);

				ImageIcon fondoGato = new ImageIcon(img);

				if (fondoGato.getIconWidth() > 800) {
					// Redimensionar
					Image fondo = fondoGato.getImage();
					// Guia renderizado java
					Image render = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
					fondoGato = new ImageIcon(render);
				}

				String menu = "Opciones: \n " + "1. Ver otra imagen " + "\n 2. Eliminar como favorito "
						+ "\n 3. Volver Menú";

				String[] botones = { " 1. Ver otra imagen", " 2. Eliminar", " 3. Salir" };

				String id_gato = String.valueOf(gatoFav.getId());
				String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato,
						JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

				int seleccion = -1;

				// Validar la opcion tomada
				for (int i = 0; i < botones.length; i++) {
					if (opcion.equals(botones[i])) {
						seleccion = i;
					}
				}

				switch (seleccion) {
				case 0:
					verFavoritos(api_key);
					break;
				case 1:
					eliminarFavorito(gatoFav);
					break;
				default:
					break;
				}

			} catch (IOException ex) {
				System.out.println(ex);
			}

		}

	}

	public static void eliminarFavorito(GatoFav gato) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder().url("https://api.thecatapi.com/v1/favourites/" + gato.getId())
				.method("DELETE", body).addHeader("Content-Type", "application/json")
				.addHeader("x-api-key", gato.getApi_key()).build();
		Response response = client.newCall(request).execute();
	}

}
