package prueba;

public class Sophos {

	public static void main(String[] args) {
		
		for(int x = 0; x <= 5; x++) {
			for (int y = 5 ; y >= 0; y--) {	
				if(x == y) 	
					System.out.print("X");
				else if(x > y)
					System.out.print("X");
				else 
					System.out.print(" ");
			}
			System.out.println("");
		}

	}

}
