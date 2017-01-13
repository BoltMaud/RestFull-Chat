package fr.univlyon1.mif03.tpspring.beans;

import fr.univlyon1.mif03.tpspring.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by a626200 on 24/11/2016.
 */
@Service
public class UserRepository {

    /**
     * collection of users
     */
    private Collection<User> userCollection;

    /**
     * id increment
     */
    public static int idNewUser=0;

    /**
     * adds a user
     * @param user
     */
    public boolean addUser(User user) {
        idNewUser++;
        user.setId(idNewUser);
        return getUserCollection().add(user);
    }

    /**
     * tests if a user exist
     * @param user
     * @return boolean
     */
    public boolean userExist(String user) {
        for (User u: getUserCollection())
            if(user.equals(u.getName()))
                return true;
        return false;
    }

    /**
     * gets collection
     * @return
     */
    public Collection<User> getUserCollection() {
        if(userCollection == null)
            userCollection = new HashSet<>();
        return userCollection;
    }

    /**
     * updates a user
     * @param user
     */
    public void updateUser(User user, String username){
        for (User u: getUserCollection()) {
            if (u.getId() == user.getId()) {
                u.setName(username);
                return;
            }
        }
    }

    /**
     * get a user by id
     * @param id
     * @return
     */
    public User getUser(int id){
        for (User u: getUserCollection()) {
            if (id == u.getId()) {
                return u;
            }
        }
        return null;
    }

    /**
     * get a user by id
     * @param name
     * @return
     */
    public User getUser(String name){
        for (User u: getUserCollection()) {
            if (name.equals( u.getName())) {
                return u;
            }
        }
        return null;
    }

    /**
     * delete a user
     * @param id
     * @return true if deleted
     */
    public boolean deleteUser(int id){
        for (User u: getUserCollection()) {
            if (id== u.getId()) {
                u.getSaloons().clear();
                getUserCollection().remove(u);
                return true;
            }
        }
        return false;
    }
}
