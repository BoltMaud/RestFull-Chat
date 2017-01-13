package fr.univlyon1.mif03.tpspring.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by A626200 on 30/11/2016.
 */
public class Saloon {

    /**
     * name of the saloon
     * is unique
     */
    private String name;

    /**
     * list of the messages of the saloon
     * @see class Message
     */
    private List<Message> messages;

    /**
     * list of the users of the saloon
     * @see class User
     */
    private List<User> users;

    /**
     * version of messages
     */
    private int version;

    public Saloon(){
        messages=new ArrayList<>();
        users=new ArrayList<>();
        version=0;
    }
    /**
     * get a message from id
     * @param id
     * @return Message
     */
    public Message getMessage(int id) {
        for (Message m:getMessages()) {
            if(m.getId()==id)
                return m;
        }
        return null;
    }

    /**
     * gets the version
     */
    public int getVersion(){
        return this.version;
    }

    /**
     * set to version++
     */
    public void setNewVersion(){
        this.version++;
    }

    /**
     * gets the list of messages
     * @return  List<Message>
     */
    public List<Message> getMessages() {
        if(messages == null)
            messages = new ArrayList<>();
        return messages;
    }

    /**
     *  gets list of users
     * @return List<User>
     */
    public List<User> getUsers() {
        if(users == null)
            users = new ArrayList<>();
        return users;
    }

    /**
     * get Saloon name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets saloon name
     * @param name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * delete a message
     * @param m
     */
    public void deleteMessage(Message m){
         getMessages().remove(m);
    }
}
