/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author familiavasquezbarragan
 */
public class UserController {
    public static Response RegisterUser(String id, String firstName, String lastName, String age){
        try{
            int idInt, ageInt;
            
            if(id.equals("")){
                return new Response("Id must not be empty",Status.BAD_REQUEST);
            }
            
            try{
                idInt=Integer.parseInt(id);
                if(idInt<0 ){
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if ( idInt>999999999) {
                    return new Response("Id must be no longer than 9 digits", Status.BAD_REQUEST);
                }
                
            }catch(NumberFormatException ex){
                return new Response("Id must be numeric", Status .BAD_REQUEST);
            }
            
            if(firstName.equals("")){
                return new Response("Firstname must not be empty",Status.BAD_REQUEST);
            }
            
            if(lastName.equals("")){
                return new Response("Lastname must not be empty",Status.BAD_REQUEST);
            }
            
            if(age.equals("")){
                return new Response("Age must not be empty",Status.BAD_REQUEST);
            }
            
            try{
                ageInt=Integer.parseInt(age);
                if(ageInt<18){
                    return new Response("User must be 18 years or older", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Age must be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            if(!storage.RegisterUser(new User(idInt,firstName,lastName,ageInt))){
                return new Response("A user with that id already exists",Status.BAD_REQUEST);
                
            }
            return new Response ("User registered successfully",Status.OK);
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    public static ArrayList<User> RefreshUsers(){
        Storage storage = Storage.getInstance();
        ArrayList<User> users = new ArrayList<>();
        users = storage.getUsers();
        return users;
    }
}
