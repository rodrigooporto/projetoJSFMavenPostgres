package rodrigooporto.util;

import java.util.Scanner;

public class UtilScanner {
	public static Scanner entrada = new Scanner(System.in);
	
	public static void saidaTexto(String msn) {
		System.out.println("------------------------------------------\n"+msn+"\n");
	}
	
	public static void saidaInterior(int numero) {
		System.out.println(numero);
	}
}
