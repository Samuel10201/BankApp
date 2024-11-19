/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;

/**
 *
 * @author familiavasquezbarragan
 */
public class StorageAccounts {
    private static StorageAccounts instance;
    private ArrayList<Account> accounts;

    public StorageAccounts() {
        this.accounts = new ArrayList<>();
    }
    
    
    public static StorageAccounts getInstance(){
        if(instance == null){
            instance = new StorageAccounts();
        }
        return instance;
    }
}
