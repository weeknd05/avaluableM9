package com.avaluable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import com.avaluable.Compte;

public class Procesador {

    /**
     * Función que dado un nombre de fichero o su correspondiente ruta, te lee y genera un array
     * associativo suponiendo que el formato es el correspondiente
     * Ej: 
     * usuario:contraseña
     * pepito:pepito123
     * @param fileName
     * @return HashMap<String, String>
     */
    public static HashMap<String, String> leerFichero(String fileName){
        File fichero = new File(fileName);
        HashMap<String, String> map = new HashMap<>();

        try {
            Scanner scanner = new Scanner(fichero);
            if(scanner.nextLine().contains(":")){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                }
            }
        }else{
            System.err.println("Formato no correspondiente");
        }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + fileName);
        }

        return map;
    }

    /**
     * Función que dado un hashmap y un nombre, te genera o sobreescribe un fichero, generando este formato:
     * Key:Value
     * 
     * @param map
     * @param fileName
     * @throws IOException 
     */
    public static void escribirFichero(HashMap<String, String> map, String fileName ) throws IOException{
        FileWriter fw = new FileWriter(fileName, true);
        //delimitador
        fw.write("----------------------------------------------------------------------------------\n");

        map.forEach((key, value) -> {
                try {
                    fw.write(key + ":" + value);
                    fw.write("\n");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          
        });
        fw.close();

    }

    /**
     * Función que dado un nombre de un diccionario(el fichero tiene que existir),
     * te devuelve una array con todas las palabras(omitiendo las variaciones)
     * @param fileName
     * @return  String[]
     * @throws FileNotFoundException
     */
    public static String[] cargarDiccionario(String fileName) throws FileNotFoundException{
        File diccionario = new File(fileName);
        String[] arrPalabras;
        Scanner scanner = new Scanner(diccionario, "UTF-8");
        int contador = 0;

        //consumimos el numero de palabras     
        int nPalabras = scanner.nextInt();
        arrPalabras = new String[nPalabras];
        //consumimos la linea de nPalabras
        scanner.nextLine();
        //consumimos el delimitador
        scanner.nextLine();
        //recorremos el fichero
        while(scanner.hasNext()){
            String palabra = scanner.next();
            String [] partes = palabra.split("/");
            arrPalabras[contador] = partes[0];
            contador++;
        }

        return arrPalabras;
    }

    

    /**
     * Función que dada una contraseña, te genera el hash con el algoritmo SHA256 y te lo devuelve en forma de hexadecimal
     * @param contraseña
     * @return String
     * @throws NoSuchAlgorithmException 
     */
    public static String hashContraseña(String contraseña) throws NoSuchAlgorithmException{
    //es necesario pasar de string a un array de bytes para la función digest
    byte[] contraseñaArr = contraseña.getBytes();
    //declaramos el objeto md con el algoritmo sha-256
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    //el algoritmo convierte el string a hash
    byte[] hash = md.digest(contraseñaArr);

    return byteArrToHex(hash);
    }

    /**
     * Función que convierte un array de bytes a hexadecimal, ya que 
     * es la forma mas usada de hacer que un hash sea legible
     * @param hash
     * @return String
     */
     private static String byteArrToHex(byte[] hash){    
    //convertir el array a string con string builder
    StringBuilder hexString = new StringBuilder(2 * hash.length);   //el tamaño inicial del StringBuilder se establece en 2 * hash.length porque                                                             cada byte del hash se convertirá en dos caracteres hexadecimales.
    for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
            hexString.append('0');  //si el byte convertido a hexadecimal resulta en una cadena de un solo carácter (ej de 0 a F), se añade un '0' delante.
        }
        hexString.append(hex);
    }
    return hexString.toString();
    }


    }

