import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Juego {
	public static String A;
	public static String B;
	public static String ganadorTirada;
	public static boolean ganadorJuego;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tablero = { "_", "_", "_", "_", "_", "_", "_", "_", "_" };
		Scanner sc = new Scanner(System.in);
		int tirada = 0;
		System.out.println("Introduce nombre de jugador A:");
		A = sc.nextLine();
		System.out.println("Introduce nombre de jugador B:");
		B = sc.nextLine();
		boolean iguales = true;
		while (iguales == true) {
			System.out.println(A + " va a tirar un dado...");
			Random rndA = new Random();
			int numA = rndA.nextInt(6) + 1;
			System.out.println(A + " ha sacado un " + numA);
			System.out.println(B + " va a tirar un dado...");
			Random rndB = new Random();
			int numB = rndB.nextInt(6) + 1;
			System.out.println(B + " ha sacado un " + numB);
			if (numA != numB) {
				if (numA > numB) {
					System.out.println(A + " ha ganado. " + A + " es X, empieza!!");
					iguales = false;
					ganadorTirada = A;
				} else {
					System.out.println(B + " ha ganado. " + B + " es X, empieza!!");
					ganadorTirada = B;
					iguales = false;
				}
			} else {
				System.out.println("Empate, volvemos a tirar...");
			}
		}
		do {
			mostrarTablero(tablero);
			String ficha = tirada % 2 == 0 ? "X" : "O";
			if (juego(ficha, tablero) == false) {
				ganadorJuego = compruebaGanador(tablero);
				if (ganadorJuego == true) {
					mostrarTablero(tablero);
					System.out.println("Ganan " + ficha + "!!!");
				}
				tirada++;
			}
		} while (!ganadorJuego && tirada < 9);
		if (tirada == 9) {
			System.out.println("Empate!!!");
		}
	}

	public static void mostrarTablero(String[] tablero) {
		System.out.println(tablero[0] + "|" + tablero[1] + "|" + tablero[2]);
		System.out.println(tablero[3] + "|" + tablero[4] + "|" + tablero[5]);
		System.out.println(tablero[6] + "|" + tablero[7] + "|" + tablero[8]);
	}

	public static boolean juego(String ficha, String[] tablero) {
		Scanner sc = new Scanner(System.in);
		boolean numeroCogido = true;
		boolean hayError = false;
		int posicion = 0;
		while (numeroCogido == true) {
			System.out.println("Indica posicion donde mover " + ficha + ": ");
			try {
				posicion = sc.nextInt();
				if (posicion > -1 && posicion < 9) {
					if (tablero[posicion].equals("_")) {
						tablero[posicion] = ficha;
						numeroCogido = false;
					} else {
						System.out.println("Esa posicion esta cogida!! Prueba otra vez");
						mostrarTablero(tablero);
					}
				} else {
					System.out.println("Introduce un numero valido!!");
				}
			} catch (InputMismatchException letra) {
				System.out.println("Introduce un numero no una letra");
				numeroCogido = false;
				hayError = true;
			}
		}
		return hayError;
	}

	public static boolean compruebaGanador(String tablero[]) {
		if (tablero[0].equals(tablero[1]) && tablero[0].equals(tablero[2]) && !tablero[0].equals("_")) {
			return true;
		} else if (tablero[3].equals(tablero[4]) && tablero[3].equals(tablero[5]) && !tablero[3].equals("_")) {
			return true;
		} else if (tablero[6].equals(tablero[7]) && tablero[6].equals(tablero[8]) && !tablero[6].equals("_")) {
			return true;
		} else if (tablero[0].equals(tablero[3]) && tablero[0].equals(tablero[6]) && !tablero[0].equals("_")) {
			return true;
		} else if (tablero[1].equals(tablero[4]) && tablero[1].equals(tablero[7]) && !tablero[1].equals("_")) {
			return true;
		} else if (tablero[2].equals(tablero[5]) && tablero[2].equals(tablero[8]) && !tablero[2].equals("_")) {
			return true;
		} else if (tablero[0].equals(tablero[4]) && tablero[0].equals(tablero[8]) && !tablero[0].equals("_")) {
			return true;
		} else if (tablero[2].equals(tablero[4]) && tablero[2].equals(tablero[6]) && !tablero[2].equals("_")) {
			return true;
		}
		return false;
	}
}
