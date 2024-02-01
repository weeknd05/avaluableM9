package com.avaluable;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtacMulti {

    private String[] diccionari;
    private String[] hashContraseñas;

    public AtacMulti(String[] diccionari) throws NoSuchAlgorithmException {
        this.diccionari = diccionari;
        this.hashContraseñas = hashContraseñaArray(diccionari);
    }

    public HashMap<String, String> atacar(Compte compte) throws NoSuchAlgorithmException {
        final HashMap<String, String> userAndPwMap = new HashMap<>();
        final AtomicBoolean found = new AtomicBoolean(false);

        // Partim el diccionari en 2
        int mid = diccionari.length / 2;

        //Primer fil
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                if (compte.getHashPass().equals(hashContraseñas[i])) {
                    synchronized (userAndPwMap) {
                        userAndPwMap.put(compte.getUsername(), diccionari[i]);
                    }
                    found.set(true);
                    break;
                }
                if (found.get()) break;
            }
        });

        // Segond fil
        Thread thread2 = new Thread(() -> {
            for (int i = mid; i < diccionari.length; i++) {
                if (compte.getHashPass().equals(hashContraseñas[i])) {
                    synchronized (userAndPwMap) {
                        userAndPwMap.put(compte.getUsername(), diccionari[i]);
                    }
                    found.set(true);
                    break;
                }
                if (found.get()) break;
            }
        });

        // inciem els dos fils
        thread1.start();
        thread2.start();

        // esperem que acabin
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }

        return userAndPwMap;
    }

    private String[] hashContraseñaArray(String[] contraseñas) throws NoSuchAlgorithmException {
        String[] hashContraseñas = new String[contraseñas.length];
        for (int i = 0; i < contraseñas.length; i++) {
            hashContraseñas[i] = Procesador.hashContraseña(contraseñas[i]);
        }
        return hashContraseñas;
    }
}
