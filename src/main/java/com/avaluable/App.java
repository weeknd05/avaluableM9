package com.avaluable;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println("Hello World!");
        HashMap<String, String> map = Procesador.leerFichero("usuaris.txt");
        int contador = 0;
        Compte[] cuentas = new Compte[10];
        //funcion lambda para recorrer y mostrar la información del hashmap de forma sencilla
        map.forEach((key, value) -> {
            System.out.println("Clave: " + key  + " Valor: " + value);
        });

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (contador < cuentas.length) {
                cuentas[contador] = new Compte(entry.getKey(), entry.getValue());
                contador++;
            } else {
                // Si hay más usuarios que espacios en el array, detén el bucle
                break;
            }
        }

        Procesador.escribirFichero(map, "usuarisHash.txt");
        
        String[] diccionari = Procesador.cargarDiccionario("es.dic");
        System.out.println(cuentas[0].getPass());
        AtacSimple atacSimple = new AtacSimple(diccionari);

        for(int i = 0; i < cuentas.length; i++){
            System.out.println(atacSimple.atacar(cuentas[i]));
        }
    }
}
