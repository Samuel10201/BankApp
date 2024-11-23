package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import core.models.User;
import java.util.ArrayList;
import java.util.Random;

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
    
    public boolean addAccount(String idAccount ,String idUsuario, double initialBalance){      
        // 0 = correcto, 2 = el usuario no existe
        int sw = 0;
        for(User user : this.users){
            if (user.getId() == Integer.parseInt(idUsuario)) {
                sw = 1;
            }
        }
        if(sw == 0){
            return false;
        }
        Account account = new Account(idAccount,this.getUser(Integer.parseInt(idUsuario)),initialBalance);
        this.accounts.add(account);
        return true;
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

    public String createId(){
        
        int sw = 0;
        String accountId = "";
        
        while(sw == 0){
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);

            accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

            if(this.validId(accountId)){
                sw = 1;
            }
        }
        
        return accountId;
        
    }
    
    public boolean validId(String idAccount){
        for (Account account : this.accounts) {
            if(account.getId() == idAccount){
                return false;
            }
        }
        return true;
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