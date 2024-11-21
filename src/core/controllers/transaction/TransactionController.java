/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction;

import core.controllers.utils.Response;
import core.models.storage.Storage;

/**
 *
 * @author familiavasquezbarragan
 */
public class TransactionController {
    
    public static Response refreshTransaction(){
        Storage storage = Storage.getInstance();
        return new Response(storage.getTransactions());
    }
}
