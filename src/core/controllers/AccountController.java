/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author familiavasquezbarragan
 */
public class AccountController {
    
    public static Response createAccount(String userId, String initialBalance){
        try{
            int userIdInt;
            double initialBalanceDouble;
            try{
                userIdInt = Integer.parseInt(userId);
                if(userIdInt < 0){
                    return new Response("Id most be positive", Status.BAD_REQUEST);
                }
                if(999999999< userIdInt){
                    return new Response("Id most have 9 digits at most", Status.BAD_REQUEST);
                }      
                
            }catch(NumberFormatException ex){
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            try{
                initialBalanceDouble = Double.parseDouble(initialBalance);
                if(initialBalanceDouble < 0){
                    return new Response("Initial balance most be positive", Status.BAD_REQUEST);
                }
                if (initialBalanceDouble == 0) {
                    return new Response("Initial balance most be more than 0", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            if(userId.equals("")){
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }
            
            if(initialBalance.equals("")){
                return new Response("initialBalance must not be empty", Status.BAD_REQUEST);
            }
            
            
            Storage storage = Storage.getInstance();
            if(storage.addAccount(userIdInt) == 1){
                return new Response("An account with that id already exists", Status.BAD_REQUEST);
            }
            if(storage.addAccount(userIdInt) == 2){
                return new Response("The user you want to assign the account do not exists", Status.BAD_REQUEST);
            }
            if(storage.addAccount(userIdInt) == 0){
                Random random = new Random();
                int first = random.nextInt(1000);
                int second = random.nextInt(1000000);
                int third = random.nextInt(100);
                String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
                
                return new Response("Account created succesfully", Status.CREATED);
            }
            return new Response("Account created succesfully", Status.CREATED);
            
        }catch(Exception ex){
            return new Response("", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Account> refreshAccounts(){
        Storage storage = Storage.getInstance();
        ArrayList<Account> accounts = new ArrayList<>();
        accounts = storage.getAccounts();
        return accounts;
    }
}
