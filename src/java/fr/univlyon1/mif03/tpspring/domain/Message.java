package fr.univlyon1.mif03.tpspring.domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Maud on 12/10/2016.
 */
public class Message implements Serializable{

    private int id;

    private User author;

    private String text;

    public Message(){}
    /**
     * sets the new message
     * @param message
     * @param user
     */
    public Message(String message, User user) {
        text = message;
        author = user;
    }

    /**
     * gets the author
     * @return
     */
    public User getAuthor() {
        return this.author;
    }

    /**
     * sets the author
     * @param author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * gets the text
     */
    public String getText() {
        return this.text;
    }

    /**
     * sets the text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * get id of the message
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * sets the id
     * @param id
     */
    public void setId(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author.getName() + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

