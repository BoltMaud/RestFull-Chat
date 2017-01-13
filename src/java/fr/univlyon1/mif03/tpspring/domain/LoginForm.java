package fr.univlyon1.mif03.tpspring.domain;

import java.io.Serializable;

/**
 * Created by A626200 on 26/11/2016.
 */
public class LoginForm implements Serializable {
    private String pseudo;

    private String saloon;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSaloon() {
        return saloon;
    }

    public void setSaloon(String saloon) {
        this.saloon = saloon;
    }
}
