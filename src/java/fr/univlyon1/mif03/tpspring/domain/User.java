package fr.univlyon1.mif03.tpspring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by A626200 on 26/11/2016.
 */
public class User implements Serializable {

    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Collection<String> saloons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Collection<String> getSaloons(){
        if(saloons == null)
            saloons = new HashSet<>();
        return saloons;
    }

    public void addSaloons(String name){
        getSaloons().add(name);
    }

}
