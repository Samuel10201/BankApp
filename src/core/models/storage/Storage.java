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
    private ArrayList<Account> accounts;
    private ArrayList<User> users;
    private ArrayList<Transaction> transactions;

    public Storage() {
        this.accounts = new ArrayList<>();
        this.users = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    
    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }
    
    public int addAccount(int idAccount){
        // 0 no hay problema, 1 el id de la cuenta ya existe,  2 el usuario al que se le quiere asignar la cuenta no existe
        Account account = this.getAccount(idAccount);
        for(Account acc : this.accounts){
            if (acc.getId() == account.getId() ) {
                return 1;
            }
        }
        int sw = 0;
        for(User user : this.users){
            if (user.getId() == account.getOwner().getId()) {
                sw = 1;
            }
        }
        if(sw == 0){
            return 2;
        }
        
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
        return this.getAccounts();
    }
}




//Al hacer un solo storage se cumple con SOLID
//Se eliminaron los ArrayList del view, se puede hacer eso? esos arraylist se reemplazaron Storage y se usaron controladores
//Se puede quitar el constructor de la interfaz que solo tiene initComponents?