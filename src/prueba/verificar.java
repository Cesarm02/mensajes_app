package prueba;

public class verificar {

	public static void main(String[] args) {
		String s ="(){";
		String[] a = s.split("()");
        String[] b = s.split("{}");
        String[] c = s.split("[]");
		System.out.println(a);
	}

}
