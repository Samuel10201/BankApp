package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author familiavasquezbarragan
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
            if (user.getId() == u.getId()) {
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
    
    public Account getAccount(String id){
        for(Account account : this.accounts){
            if(account.getId().equals(id)){
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




//Al hacer un solo storage se cumple con SOLID
//Se eliminaron los ArrayList del view, se puede hacer eso? esos arraylist se reemplazaron Storage y se usaron controladores
//Se puede quitar el constructor de la interfaz que solo tiene initComponents?