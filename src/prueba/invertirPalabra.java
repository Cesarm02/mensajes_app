package prueba;

import java.util.Scanner;

public class invertirPalabra {

	public static void main(String[] args) {
		//Palabra a revertir
		System.out.println("Escribe la palabra");
		Scanner in = new Scanner(System.in);
		String palabra = in.nextLine(); 
		//Separar letra por letra
		char [] palabraArreglo = palabra.toCharArray();
		//Palabra invertida
		String inversa ="";
		//Ciclo reversivo, desde la ultima letra, hasta la primera
		for(int x = palabraArreglo.length -1 ; x >= 0; x--) {
			//Formación de la palabra invertida
			inversa = inversa + palabraArreglo[x]; 
			System.out.println(inversa);
		}
		
	}

}
