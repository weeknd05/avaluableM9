package com.avaluable;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
/**
 * Clase para atacar una cuenta y obtener la contraseña
 * 
 * @String[] diccionari Array de palabras que hace la función de diccionario
 * 
 */
public class AtacSimple {

    public  String[] diccionari;
    private String[] hashContraseñas;
    /**
     * 
     * @param diccionari Array de palabras que seran verificadas como posibles contraseñas 
     * @throws NoSuchAlgorithmException
     */
    public AtacSimple(String[] diccionari) throws NoSuchAlgorithmException{
        this.diccionari = diccionari;
        //construimos el array de las contraseñas hasheadas, asi lo podemos utilizar para todos los usuarios que hagan falta
        this.hashContraseñas = hashContraseñaArray(diccionari);

    }

    /**
     * Función que ataca una cuenta, encontrando la contraseña en el diccionario
     * @return HashMap<String, String>
     * @throws NoSuchAlgorithmException
     */
    public HashMap<String, String> atacar(Compte compte) throws NoSuchAlgorithmException{
        HashMap<String, String> userAndPwMap = new HashMap<>();
            for(int i = 0; i < diccionari.length; i++){
                if(compte.getHashPass().equals(hashContraseñas[i])){
                    userAndPwMap.put(compte.getUsername(), diccionari[i]);
                }
            }
            return userAndPwMap;
    }

    /**
     * Función que devuelve un array con todas las contraseñas de un diccionario hasheadas, para optimizar 
     * el programa y no tener que llamar la función que devuelve un hash mas de lo necesario
     * @param contraseñas
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String [] hashContraseñaArray (String[] contraseñas) throws NoSuchAlgorithmException{
       String[] hashContraseñas = new String[contraseñas.length];
    //   System.out.println("Empieza el proceso de hash");
        for(int i = 0; i < contraseñas.length; i++){
        //    System.out.println("Estoy hasheando la contraseña:" + contraseñas[i]);
            hashContraseñas[i] = Procesador.hashContraseña(contraseñas[i]);
        }
        return hashContraseñas;

    }

    
}
