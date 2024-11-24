/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction;

import core.controllers.utils.Response;
import core.models.Account;
import core.models.Transaction;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author familiavasquezbarragan
 */
public class TransactionController {
    
    public static Response refreshTransaction(){
        Storage storage = Storage.getInstance();
        ArrayList<Transaction> transactions = storage.getTransactions();
        Collections.reverse(transactions);
        return new Response(transactions);
    }
}
