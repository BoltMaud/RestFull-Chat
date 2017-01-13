package fr.univlyon1.mif03.tpspring.beans;

import fr.univlyon1.mif03.tpspring.domain.Message;
import fr.univlyon1.mif03.tpspring.domain.Saloon;
import fr.univlyon1.mif03.tpspring.domain.User;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by Maud on 11/10/2016.
 */
@Service
public class SalonRepository {

    /**
     * list of saloons
     */
    private ArrayList<Saloon> saloons;

    /**
     * number of messages
     */
    private static int idMessages=0;

    /**
     * gets the list of the saloon
     * @return
     */
    private ArrayList<Saloon> getSaloons() {
        if(saloons == null)
            saloons = new ArrayList<Saloon>();
        return saloons;
    }

    /**
     * gets the list of messages of a saloon
     * @param saloon
     * @return
     */
    public List<Message> getMessagesSaloon(String saloon){
        for (Saloon s:getSaloons()) {
            if(s.getName().equals(saloon))
                return s.getMessages();
        }
        return new ArrayList<>();
    }

    /**
     * get the list of users of a saloon
     * @param saloon
     * @return
     */
    public List<User> getUsersSaloon(String saloon){
        for (Saloon s:getSaloons()) {
            if(s.getName().equals(saloon))
                return s.getUsers();
        }
        return null;
    }

    /**
     * get number of messages of a saloon
     * @param saloon
     * @return
     */
    public int getNbMessagesSaloon(String saloon){
        return getMessagesSaloon(saloon).size();
    }

    /**
     * get the end of list of Messages
     * @param saloon
     * @param id
     * @return
     */
    public List<Message> getMessagesAfterISaloon(String saloon, int id){
        List<Message> messages=new ArrayList<>();
        for (Message m:getMessagesSaloon(saloon)) {
            if(m.getId()>id){
                messages.add(m);
            }
        }
        return messages;
    }

    /**
     * add a message to a saloon
     * @param saloon
     * @param message
     */
    public void addMessage(String saloon, Message message){
        idMessages++;
        message.setId(idMessages);
        getMessagesSaloon(saloon).add(message);
    }

    /**
     * gets the list of name of the saloon
     */
    public List<String> getSaloonsNames(){
        ArrayList<String> list=new ArrayList<>();
        for (Saloon s:getSaloons()) {
            list.add(s.getName());
        }
        return list;
    }

    /**
     * add a saloon to the app if doesn't exist
     * @param name
     */
    public void addSaloon(String name){
        for (Saloon s:getSaloons()) {
            if(s.getName().equals(name))
                return;
        }
        Saloon n=new Saloon();
        n.setName(name);
        getSaloons().add(n);
        return;
    }

    /**
     * delete a saloon to the app
     */
    public void deleteSaloon(String name) {
        getMessagesSaloon(name).clear();
        for (Saloon s : getSaloons()) {
            if (s.getName().equals(name))
                getSaloons().remove(s);
        }
    }

    /**
     * gets salon by name
     */
    public Saloon getSaloon(String name){
        for (Saloon s:getSaloons()) {
            if(s.getName().equals(name))
                return s;
        }
        return null;
    }







}
