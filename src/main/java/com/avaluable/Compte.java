package com.avaluable;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Compte {

    public String username;
    public String pass;
    public String hashPass;

    public Compte(String username, String pass) throws NoSuchAlgorithmException{
        this.username = username;
        this.pass = pass;
        this.hashPass = Procesador.hashContraseña(pass);
    }

    



    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHashPass() {
        return this.hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }


    
}
