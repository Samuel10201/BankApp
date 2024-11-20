/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author bjose
 */
public class TransactionController {
    public static Response Deposit(TransactionType type, String sourceAccountid, String destinationAccountid, String amount){
        try{
            double amountDouble;
            
            try{
                amountDouble=Double.parseDouble(amount);
                if(amountDouble<0){
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount must be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Account destinationAccount = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(destinationAccountid)) {
                    destinationAccount = account;
                }
            }
            if(!sourceAccountid.equals("")){
                return new Response("To make a deposit, source account must be empty",Status.BAD_REQUEST);
            }
            if(destinationAccount==null){
                return new Response("Destination account does not exists",Status.BAD_REQUEST);
            }
            
            destinationAccount.setBalance(destinationAccount.getBalance()+amountDouble);
            storage.Deposit(new Transaction(type,null,destinationAccount,amountDouble));
            return new Response ("Successfull deposit",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response Withdraw(TransactionType type, String sourceAccountid, String destinationAccountid, String amount){
        try{
            double amountDouble;
            
            try{
                amountDouble=Double.parseDouble(amount);
                if(amountDouble<0){
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount must be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Account sourceAccount = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(sourceAccountid)) {
                    sourceAccount = account;
                }
            }
            if(!destinationAccountid.equals("")){
                return new Response("To make a withdraw, destination account must be empty",Status.BAD_REQUEST);
            }
            if(sourceAccount==null){
                return new Response("Source account does not exists",Status.BAD_REQUEST);
            }
            if(sourceAccount.getBalance()-amountDouble<0){
                return new Response("You can not withdraw that amount from the source account",Status.BAD_REQUEST);
            }
            
            
            sourceAccount.setBalance(sourceAccount.getBalance()-amountDouble);
            storage.Deposit(new Transaction(type,sourceAccount,null,amountDouble));
            return new Response ("Successfull withdraw",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response Transfer(TransactionType type, String sourceAccountid, String destinationAccountid, String amount){
        try{
            double amountDouble;
            
            try{
                amountDouble=Double.parseDouble(amount);
                if(amountDouble<0){
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount must be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Account sourceAccount = null;
            Account destinationAccount = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(sourceAccountid)) {
                    sourceAccount = account;
                }
                if (account.getId().equals(destinationAccountid)) {
                    destinationAccount = account;
                }
            }
            if(sourceAccount==null){
                return new Response("Source account does not exists",Status.BAD_REQUEST);
            }
            if(destinationAccount==null){
                return new Response("Destination account does not exists",Status.BAD_REQUEST);
            }
            if(sourceAccount.getBalance()-amountDouble<0){
                return new Response("You can not withdraw that amount from the source account",Status.BAD_REQUEST);
            }
            
            destinationAccount.setBalance(destinationAccount.getBalance()+amountDouble);
            sourceAccount.setBalance(sourceAccount.getBalance()-amountDouble);
            storage.Transfer(new Transaction(type,sourceAccount,destinationAccount,amountDouble));
            return new Response ("Successfull Transfer",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Transaction> RefreshTransaction(){
        Storage storage = Storage.getInstance();
        ArrayList<Transaction> transaction = new ArrayList<>();
        transaction = storage.getTransactions();
        return transaction;
    }
    
    
}
