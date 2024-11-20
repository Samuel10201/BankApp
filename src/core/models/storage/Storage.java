/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author bjose
 */
public class Storage {
    private static Storage instance;
    
    // Atributos del Storage
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    
    private Storage() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
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
    
    public User getUser(int id){
        for(User user : this.users){
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public int addAccount(String idAccount ,String idUsuario, double initialBalance){      
        // 0 = correcto, 1 = El id de cuenta ya existe, 2 = el usuario no existe
        int sw = 0;
        for(User user : this.users){
            if (user.getId() == Integer.parseInt(idUsuario)) {
                sw = 1;
            }
        }
        if(sw == 0){
            return 2;
        }
        Account account = new Account(idAccount,this.getUser(Integer.parseInt(idUsuario)),initialBalance);
        this.accounts.add(account);
        return 0;
    }
    
    public Account getAccount(int id){
        for(Account account : this.accounts){
            if(Integer.parseInt(account.getId()) == id){
                return account;
            }
        }
        return null;
    }
    
    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    
    public boolean Deposit(Transaction transaction){
        this.transactions.add(transaction);
        return true;
    }
    
    public boolean Withdraw(Transaction transaction){
        this.transactions.add(transaction);
        return true;
    }
    
    public boolean Transfer(Transaction transaction){
        this.transactions.add(transaction);
        return true;
    }
    
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
