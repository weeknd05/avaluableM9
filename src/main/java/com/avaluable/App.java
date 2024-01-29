package com.avaluable;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> mapCuentas = Procesador.leerFichero("usuaris.txt");
        int menuOption = 0;
        System.out.println("Bienvenido al programa de ataque al diccionario! A continuación, elije una opción");
        while (menuOption != -1) {
            displayMenu();
            menuOption = scanner.nextInt();
            scanner.nextLine(); // To consume the newline character

            switch (menuOption) {
                case 1:
                    singleThreadAttack(scanner, mapCuentas);
                    break;

                case 2:
                    // Placeholder for multi-threaded attack
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Introduce una opción: ");
        System.out.println("1-Ataque un solo hilo");
        System.out.println("2-Ataque multihilo");
    }

    private static void singleThreadAttack(Scanner scanner, HashMap<String, String> mapCuentas) throws NoSuchAlgorithmException {
        System.out.println("Introduce el nombre del diccionario \nEj: \"es.dic\"");

        try {
            String[] dictionary = Procesador.cargarDiccionario(scanner.nextLine());
            Compte[] accounts = createAccountArray(mapCuentas, 10);
            AtacSimple atacSimple = new AtacSimple(dictionary);

            for (Compte compte : accounts) {
                if (compte != null) {
                    System.out.println(atacSimple.atacar(compte));
                }
            }

            Procesador.escribirFichero(mapCuentas, "resultats.txt");
        } catch (IOException e) {
            System.out.println("El fichero no existe");
        }
    }

    private static Compte[] createAccountArray(HashMap<String, String> mapCuentas, int maxSize) throws NoSuchAlgorithmException {
        Compte[] accounts = new Compte[maxSize];
        int counter = 0;

        for (Map.Entry<String, String> entry : mapCuentas.entrySet()) {
            if (counter < accounts.length) {
                accounts[counter++] = new Compte(entry.getKey(), entry.getValue());
            } else {
                break;
            }
        }

        return accounts;
    }
}
