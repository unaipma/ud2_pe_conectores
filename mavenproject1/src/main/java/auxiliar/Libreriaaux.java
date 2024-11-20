package auxiliar;

import java.util.Scanner;

public class Libreriaaux {
    /**
     * Método que valida si la entrada es un número válido mayor o igual a 0 y comprueba que sea un int.
     *
     * @return Un número int válido ingresado por el usuario.
     */
    public static int compruebaNumero() {
        int num;
        Scanner t = new Scanner(System.in);
        do {
            while (!t.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                t.nextLine(); // Limpiar el buffer del scanner
            }
            num = t.nextInt();
            t.nextLine();
            if (num < 0) {
                System.out.println("El número debe ser mayor que 0.");
            }
        } while (num < 0);
        return num;
    }
}
