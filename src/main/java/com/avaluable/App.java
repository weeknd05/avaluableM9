package com.avaluable;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public  class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        //Cargamos un hash map con las cuentas y las contraseñas del fichero
        HashMap<String, String> mapCuentas = Procesador.leerFichero("usuaris.txt");
        int contador = 0;
        Compte[] cuentas = new Compte[10];

        //funcion lambda para recorrer y mostrar la información del hashmap de forma sencilla
        // mapCuentas.forEach((key, value) -> {
        //     System.out.println("Usuario: \"" + key  + "\" Contraseña: \"" + value+"\"");
        // });

        
        for (Map.Entry<String, String> entry : mapCuentas.entrySet()) {
            if (contador < cuentas.length) {
                cuentas[contador] = new Compte(entry.getKey(), entry.getValue());
                contador++;
            } else {
                // Si hay más usuarios que espacios en el array, detén el bucle
                break;
            }
        }

        Procesador.escribirFichero(mapCuentas, "usuarisHash.txt");
        
        String[] diccionari = Procesador.cargarDiccionario("es.dic");
        AtacSimple atacSimple = new AtacSimple(diccionari);

        for(int i = 0; i < cuentas.length; i++){
            System.out.println(atacSimple.atacar(cuentas[i]));
        }
    }
}
