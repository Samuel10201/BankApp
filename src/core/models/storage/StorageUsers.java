/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author bjose
 */
public class StorageUsers {
    private static StorageUsers instance;
    
    // Atributos del Storage
    private ArrayList<User> users;
    
    private StorageUsers() {
        this.users = new ArrayList<>();
        
    }
    
    public static StorageUsers getInstance() {
        if (instance == null) {
            instance = new StorageUsers();
        }
        return instance;
    }
    
    public boolean RegisterUser(User user){
        for (User u : this.users) {
            if (user.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user); 
        return true;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
}
