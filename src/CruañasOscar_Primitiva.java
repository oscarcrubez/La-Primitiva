import java.util.Scanner;
import java.util.Random;

// LINK GITHUB:

/**
 * Programa de simulació de La Primitiva
 * @auhor Oscar Cruañas Gonzalbez
 * @version 1.0
 * @date 18/02/2024
 */

public class CruañasOscar_Primitiva {
    /**
     * Mètode main executable
     * @param args
     * @since 1.0
     */
    public static void main(String[] args) {
        menuPrincipal();
    }

    /**
     * A partir d'aquest mètode s'executen els altres mètodes per poder jugar a La Primitva
     * @since 1.0
     */
    private static void menuPrincipal(){
        Scanner scanner = new Scanner(System.in);
        int item;
        System.out.println("***** PRIMITIVA ******");

        int[] aposta = introduirAposta();
        int[] combinacioGuanyadora = calcularCombinacioGuanyadora();

        int premi;

        if (combinacioGuanyadora != null) {
            System.out.println("La combinació ganadora és: ");

            for (int i = 0; i < combinacioGuanyadora.length - 1; i++) {
                System.out.print(combinacioGuanyadora[i] + " ");
            }

            System.out.println("Reintegrament " + combinacioGuanyadora[combinacioGuanyadora.length - 1]);
        }

        premi = comprovarEncerts(aposta, combinacioGuanyadora);
        System.out.println("El teu premi és: "+premi+" €");
    }

    /**
     * Aquest mètode capta els números d'aposta introduïts per l'usuari.
     * @return l'array amb els números introduïts per l'usuari i el reintegrament.
     * @since 1.0
     */
    private static int[] introduirAposta(){
        Scanner scanner = new Scanner(System.in);

        int x = 0;
        boolean valorCorrecte = true;
        boolean repetit = false;

        int[] aposta= new int[7];

        do {
            valorCorrecte = true;
            for (int i = 0; i < aposta.length - 1; i++) {
                System.out.println("Introdueix el #" + (i+1) + " número de la primitiva (1-49): ");
                valorCorrecte = scanner.hasNextInt();

                if (!valorCorrecte) {
                    System.out.println("ERROR. Valor no enter.");
                    scanner.nextLine();
                    break;

                } else {
                    x = scanner.nextInt();
                    scanner.nextLine();

                    if (x < 1 || x > 49) {
                        System.out.println("Opció fora de rang.");
                        valorCorrecte = false;
                        break;

                    } else {

                        for (int j = 0; j < i; j++) {
                            if (aposta[j] == x) {
                                System.out.println("ERROR. Valor repetit.");
                                valorCorrecte = false;
                                repetit = true;
                                break;

                            }
                        }
                        if (!repetit) {
                            aposta[i] = x;
                        }
                    }
                }
            }

        } while (!valorCorrecte);

        do {
            System.out.println("Introdueix el reintegrament (0-9)");
            valorCorrecte = scanner.hasNextInt();

            if (!valorCorrecte) {
                System.out.println("ERROR. Valor no enter.");
                scanner.nextLine();
                break;

            } else {
                x = scanner.nextInt();
                scanner.nextLine();

                if (x < 0 || x > 9) {
                    System.out.println("Valor fora de rang.");
                    valorCorrecte = false;
                    break;

                }else {
                    aposta[6] = x;
                }

            }
        } while (!valorCorrecte);

        System.out.println("El teu número: ");
        for (int i = 0; i < aposta.length - 1; i++) {
            System.out.print(aposta[i] + " ");
        }
        System.out.println("Reintegrament: " + aposta[6]);
        System.out.println("");

        return aposta;
    }

    /**
     * Aquest mètode genera els números guanyadors i el reintegrament de forma aleatòria.
     * @return l'array amb els números guanyadors i el reintegrament.
     * @since 1.0
     */
    private static int[] calcularCombinacioGuanyadora(){
        Random random = new Random();
        int[] combinacio = new int[7];
        boolean valorCorrecte = true;

        for (int i = 0; i < 6; i++) {
            combinacio[i] = (int) (Math.random() * 49);
            do {
                valorCorrecte = true;

                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        if (combinacio[j] == combinacio[i]) {
                            valorCorrecte = false;
                            break;
                        } else {
                            valorCorrecte = true;
                        }
                    }
                }
            } while (!valorCorrecte);

            combinacio[6] = (int) (Math.random() * 10);

        }

        return combinacio;
    }

    /**
     * Aquest mètode compara els arrays de números de l'usuari el dels números guanyadors, definint el premi corresponent.
     * @param aposta array amb els números apostats per l'usuari
     * @param combinacioGuanyadora array amb els números guanyadors
     * @return el premi segons els encerts de l'usuari
     * @since 1.0
     */
    private static int comprovarEncerts(int[] aposta, int[] combinacioGuanyadora){
        int premi = 0;
        int encerts = 0;
        boolean reintregrament = false;

        //Comprobar encerts a la combinació
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (aposta[i] == combinacioGuanyadora[j]) {
                    encerts++;
                }
            }
        }

        //Comprobar reintegrament
        if (aposta[6] == combinacioGuanyadora[6]) {
            reintregrament = true;
        }

        //Calcular premi
        premi = encerts * 20;

        if (reintregrament) {
            premi += 6;
        }

        return premi;
    }
}